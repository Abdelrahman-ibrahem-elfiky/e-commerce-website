package com.project.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Orders orders;
    @ManyToOne
    private Product product;
    private String size;
    private int quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userld;
    private LocalDateTime deliveryDate;

    public OrderItem() {
    }

    public OrderItem(Long id, Orders order, Product product, String size, int quantity, Integer price, Integer discountedPrice, Long userld, LocalDateTime deliveryDate) {
        this.id = id;
        this.orders = order;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.userld = userld;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders order) {
        this.orders = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Long getUserld() {
        return userld;
    }

    public void setUserld(Long userld) {
        this.userld = userld;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
