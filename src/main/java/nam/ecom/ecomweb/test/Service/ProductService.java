package nam.ecom.ecomweb.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    // public Product
}
