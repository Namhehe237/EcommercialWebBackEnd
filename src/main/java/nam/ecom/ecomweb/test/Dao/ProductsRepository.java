package nam.ecom.ecomweb.test.Dao;



import org.springframework.data.jpa.repository.JpaRepository;

import nam.ecom.ecomweb.test.Entity.Product;

public interface ProductsRepository extends JpaRepository<Product,Integer>{

}