package nam.ecom.ecomweb.test.Controller;

import java.util.ArrayList;
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
    public List<Product> getProductByCategories(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    @GetMapping("/products/details/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }

    // @GetMapping("/products")
    // public List<Product> findAll() {
    // return productService.findAll();
    // }

    @PostMapping("/products/checkout")
    public ResponseEntity<String> processCheckout(@RequestBody List<Product> cart) {
        List<String> responseList = new ArrayList<>();

        for (Product cartItem : cart) {
           
            Product productInDatabase = productService.findById(cartItem.getId());
            if (productInDatabase == null) {
                responseList.add("Product ID " + cartItem.getId() + " not found");
                continue;
            }

            if (productInDatabase.getQuantity() < cartItem.getQuantity()) {
                responseList.add(productInDatabase.getName() + " - Not enough stock");
                continue;
            }

       
            productInDatabase.setQuantity(productInDatabase.getQuantity() - cartItem.getQuantity());
            productService.save(productInDatabase);

            responseList.add(productInDatabase.getName() + " - Quantity : " + cartItem.getQuantity());
        }


        String responseText = String.join("\n", responseList);

        return ResponseEntity.ok(responseText);
    }

}
