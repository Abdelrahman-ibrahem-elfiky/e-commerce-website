package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.model.Rating;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.repository.RatingRepository;
import com.project.Ecommerce.request.RatingRequest;
import com.project.Ecommerce.service.basicService.ProductService;
import com.project.Ecommerce.service.basicService.RatingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplement implements RatingService {

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplement(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product=productService.findProductById(request.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getReating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductRating(productId);
    }
}
