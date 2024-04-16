package com.project.Ecommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderld;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderltems=new ArrayList<>();
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address shippingAddress;
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();
    private double totalPrice;
    private Integer discounte;

    private String orderStatus;
    private int totalItem;
    private LocalDateTime createAt;

    public Orders() {
    }

    public Orders(Long id, String orderld, User user, List<OrderItem> orderltems, LocalDateTime orderDate, LocalDateTime deliveryDate, Address shippingAddress, PaymentDetails paymentDetails, double totalPrice, Integer discounte, String orderStatus, int totalItem, LocalDateTime createAt) {
        this.id = id;
        this.orderld = orderld;
        this.user = user;
        this.orderltems = orderltems;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.paymentDetails = paymentDetails;
        this.totalPrice = totalPrice;
        this.discounte = discounte;
        this.orderStatus = orderStatus;
        this.totalItem = totalItem;
        this.createAt = createAt;
    }

    public String getOrderld() {
        return orderld;
    }

    public void setOrderld(String orderld) {
        this.orderld = orderld;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderltems() {
        return orderltems;
    }

    public void setOrderltems(List<OrderItem> orderltems) {
        this.orderltems = orderltems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getDiscounte() {
        return discounte;
    }

    public void setDiscounte(Integer discounte) {
        this.discounte = discounte;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
