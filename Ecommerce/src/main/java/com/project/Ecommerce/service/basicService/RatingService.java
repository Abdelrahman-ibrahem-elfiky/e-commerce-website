package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Rating;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest request, User user)throws ProductException;

    public List<Rating> getProductsRating(Long productId);


}
