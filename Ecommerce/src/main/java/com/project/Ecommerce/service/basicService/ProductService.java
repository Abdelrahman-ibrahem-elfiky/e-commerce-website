package com.project.Ecommerce.service.basicService;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    public Product createProduct(CreateProductRequest req) throws ProductException;
    public String deleteProduct(Long productid) throws ProductException;
    public Product updateProduct(Long productld, Product product) throws ProductException;
    public Product findProductById (Long id) throws ProductException;
    public List<Product> findProductByCategory(String category);
    public List<Product> findAllProduct();
    public Page<Product> getAllProduct(String category, List<String>colors, List<String>sizes, Integer minPrice,
                                       Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

}
