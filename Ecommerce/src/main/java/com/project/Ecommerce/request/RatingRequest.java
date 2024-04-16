package com.project.Ecommerce.request;

public class RatingRequest {

    private Long productId;
    private double reating;

    public RatingRequest(Long productId, double reating) {
        this.productId = productId;
        this.reating = reating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getReating() {
        return reating;
    }

    public void setReating(double reating) {
        this.reating = reating;
    }
}
