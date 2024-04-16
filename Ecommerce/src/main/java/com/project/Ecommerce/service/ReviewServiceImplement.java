package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.model.Rating;
import com.project.Ecommerce.model.Review;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.RatingRepository;
import com.project.Ecommerce.repository.ReviewRepository;
import com.project.Ecommerce.request.RatingRequest;
import com.project.Ecommerce.request.ReviewRequest;
import com.project.Ecommerce.service.basicService.ProductService;
import com.project.Ecommerce.service.basicService.RatingService;
import com.project.Ecommerce.service.basicService.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplement implements ReviewService {

    private ReviewRepository reviewRepository;
    private ProductService productService;

    public ReviewServiceImplement(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
       Product product=productService.findProductById(request.getProductId());

       Review review=new Review();
       review.setProduct(product);
       review.setUser(user);
       review.setReview(request.getReview());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllsReview(Long productId) {
        return reviewRepository.getAllProductReview(productId);
    }
}

