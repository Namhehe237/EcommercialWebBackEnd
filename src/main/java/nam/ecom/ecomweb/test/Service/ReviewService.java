package nam.ecom.ecomweb.test.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nam.ecom.ecomweb.test.Dao.ReviewRepository;
import nam.ecom.ecomweb.test.Entity.Review;

import java.util.List;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review addNewReview(Review review) {
        return reviewRepository.save(review);
    }
    
}
