package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Review;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest request, User user)throws ProductException;

    public List<Review> getAllsReview(Long productId);


}
