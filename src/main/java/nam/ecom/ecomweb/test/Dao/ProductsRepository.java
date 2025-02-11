package nam.ecom.ecomweb.test.Dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import nam.ecom.ecomweb.test.Entity.Product;

public interface ProductsRepository extends JpaRepository<Product,Integer>{
    Page<Product> findAll(Pageable pageable);

    List<Product> findByCategory(String category);
}