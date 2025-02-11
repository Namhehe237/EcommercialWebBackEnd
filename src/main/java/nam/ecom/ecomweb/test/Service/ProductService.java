package nam.ecom.ecomweb.test.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.transaction.Transactional;
import nam.ecom.ecomweb.test.Dao.ProductsRepository;
import nam.ecom.ecomweb.test.Entity.Product;

@Service
@Transactional
public class ProductService {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }

    public List<Product> findByCategory(String category){
        return productsRepository.findByCategory(category);
    }

    
    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    // public Product
    public Product findById(int id) {
        Optional<Product> result = productsRepository.findById(id);
        return result.orElse(null);
    }

    // Save or update a product
    public void save(Product product) {
        productsRepository.save(product);
    }
}
