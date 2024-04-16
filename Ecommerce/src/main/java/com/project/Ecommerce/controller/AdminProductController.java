package com.project.Ecommerce.controller;

import com.project.Ecommerce.exception.OrderException;
import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.request.CreateProductRequest;
import com.project.Ecommerce.response.ApiResponse;
import com.project.Ecommerce.service.basicService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) throws ProductException {
        Product product=productService.createProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);

    }
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<String>DeleteProduct(@PathVariable Long productId) throws ProductException
    {
        String state=productService.deleteProduct(productId);
        return new ResponseEntity<String>(state,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>>findAllProduct()
    {
        List<Product> products=productService.findAllProduct();
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);

    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product>updateProduct(@RequestBody Product request, @PathVariable Long productId) throws ProductException
    {
        Product product=productService.updateProduct(productId,request);

        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse>createMultipleProduct(@RequestBody CreateProductRequest [] requests) throws ProductException
    {
        for (CreateProductRequest product:requests)
        {
           productService.createProduct(product);
        }
        ApiResponse response=new ApiResponse();
        response.setMessage("Product Created successfully");
        response.setStatus(true);

        return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);

    }
}
