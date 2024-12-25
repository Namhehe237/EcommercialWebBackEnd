package nam.ecom.ecomweb.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import nam.ecom.ecomweb.test.Dao.ProductsRepository;

@Service
@Transactional
public class ProductService {
    private ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // public Product
}
