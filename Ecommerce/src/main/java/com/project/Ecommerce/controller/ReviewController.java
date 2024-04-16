package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Review;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.ReviewRequest;
import com.project.Ecommerce.service.basicService.ReviewService;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req, user);

        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productld) throws UserException, ProductException {

        List<Review> reviews = reviewService.getAllsReview(productld);
        return new ResponseEntity<>(reviews,HttpStatus.ACCEPTED);
    }


}
