package com.project.Ecommerce.service;

import com.project.Ecommerce.exception.ProductException;
import com.project.Ecommerce.model.Category;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.repository.CategoryRepository;
import com.project.Ecommerce.repository.ProductRepository;
import com.project.Ecommerce.request.CreateProductRequest;
import com.project.Ecommerce.service.basicService.ProductService;
import com.project.Ecommerce.service.basicService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;

/*    public ProductServiceImplement(ProductRepository productRepository,
                                   UserService userService,
                                   CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }*/
    @Override
    public Product createProduct(CreateProductRequest req) throws ProductException {

        if (req.gettoplevelcategory()==null ||req.gettoplevelcategory().isEmpty())
        {
            throw new ProductException("name is null ");
        }
        else {

            Category topLevel = categoryRepository.findByName(req.gettoplevelcategory());

            if (topLevel == null) {
                Category topLevelCategory = new Category();
                topLevelCategory.setName(req.gettoplevelcategory());
                topLevelCategory.setLevel(1);

                //store the new topLevelCategory in topLevel
                topLevel = categoryRepository.save(topLevelCategory);
            }

            Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), req.gettoplevelcategory());

            if (secondLevel == null) {
                Category secondLevelCategory = new Category();
                secondLevelCategory.setName(req.getSecondLevelCategory());
                secondLevelCategory.setParentCategory(topLevel);
                secondLevelCategory.setLevel(2);

                secondLevel = categoryRepository.save(secondLevelCategory);
            }

            Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), req.getSecondLevelCategory());

            if (thirdLevel == null) {
                Category thirdLevelCategory = new Category();
                thirdLevelCategory.setName(req.getThirdLevelCategory());
                thirdLevelCategory.setParentCategory(secondLevel);
                thirdLevelCategory.setLevel(3);

                thirdLevel = categoryRepository.save(thirdLevelCategory);
            }

            Product product = new Product();
            product.setTitle(req.getTitle());
            product.setColor(req.getColor());
            product.setDescription(req.getDescription());
            product.setDiscountedPrice(req.getDiscountedPrice());
            product.setDiscountPersent(req.getDiscountPersent());
            product.setImageUrl(req.getImageUrl());
            product.setBrand(req.getBrand());
            product.setPrice(req.getPrice());
            product.setSizes(req.getSize());
            product.setQuantity(req.getQuantity());
            product.setCreatedAt(LocalDateTime.now());

            product.setCategory(thirdLevel);

            Product saveProduct = productRepository.save(product);
            return saveProduct;
        }
    }

    @Override
    public String deleteProduct(Long productid) throws ProductException {

        Product product = productRepository.findProductById(productid);

        if (product == null) {
            throw new ProductException("this product not found already ");
        }
        product.getSizes().clear();
        productRepository.deleteById(productid);

        return "product Deleted Success ";
    }

    @Override
    public Product updateProduct(Long productld, Product req) throws ProductException {

        Product product = productRepository.findProductById(productld);

        if (req.getQuantity() != 0) {
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        throw new ProductException("Product Not Found With id-->" + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {

        return null;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAllProducts();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products
                    .stream()
                    .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {

                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {

                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }

        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContant = products.subList(startIndex, endIndex);

        Page<Product> filteredProduct = new PageImpl<>(pageContant, pageable, products.size());


        return filteredProduct;
    }
}
