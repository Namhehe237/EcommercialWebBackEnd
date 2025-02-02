package nam.ecom.ecomweb.test.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nam.ecom.ecomweb.test.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review , Long>{
    List<Review> findByProductId(Long productId);
}
