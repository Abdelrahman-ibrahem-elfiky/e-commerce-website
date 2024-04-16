package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.exception.UserException;
import com.project.Ecommerce.model.Rating;
import com.project.Ecommerce.model.User;
import com.project.Ecommerce.request.RatingRequest;
import com.project.Ecommerce.service.basicService.RatingService;
import com.project.Ecommerce.service.basicService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(req, user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productld) throws UserException, ProductException {

        List<Rating> ratings = ratingService.getProductsRating(productld);
        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }

}
