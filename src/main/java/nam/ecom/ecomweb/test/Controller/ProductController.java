package nam.ecom.ecomweb.test.Controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import nam.ecom.ecomweb.test.Entity.Product;
import nam.ecom.ecomweb.test.Service.ProductService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAll(pageable);
    }

    @GetMapping("/products/{category}")
    public List<Product> getProductByCategories(@PathVariable String category){
        return productService.findByCategory(category);
    }

    // @GetMapping("/products")
    // public List<Product> findAll() {
    //     return productService.findAll();
    // }

    @PostMapping("/products/checkout")
    public ResponseEntity<String> processCheckout(@RequestBody List<Product> cart) {
        // Log received data
        System.out.println("Received cart data:");
        cart.forEach(item -> System.out.println(item.toString()));

        StringBuilder response = new StringBuilder("Checkout Success! Updated items:\n");

        // Process each product in the cart
        for (Product cartItem : cart) {
            // Fetch the product from the database by ID
            Product productInDatabase = productService.findById(cartItem.getId());
            if (productInDatabase == null) {
                response.append("Product ID ")
                        .append(cartItem.getId())
                        .append(" not found in database.\n");
                continue;
            }

            // Check if there is sufficient stock
            if (productInDatabase.getQuantity() < cartItem.getQuantity()) {
                response.append("Insufficient stock for product ID ")
                        .append(cartItem.getId())
                        .append(". Available: ")
                        .append(productInDatabase.getQuantity())
                        .append(", Requested: ")
                        .append(cartItem.getQuantity())
                        .append("\n");
                continue;
            }

            // Subtract the quantity
            productInDatabase.setQuantity(productInDatabase.getQuantity() - cartItem.getQuantity());

            // Save the updated product back to the database
            productService.save(productInDatabase);

            // Append to response
            response.append("Updated product ID ")
                    .append(productInDatabase.getId())
                    .append(": New quantity: ")
                    .append(productInDatabase.getQuantity())
                    .append("\n");
        }
        System.out.println(response.toString().trim());

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}
