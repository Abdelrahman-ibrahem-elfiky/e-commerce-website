package com.project.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentld;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceld;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentld;


    public  PaymentDetails()
    {

    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentld() {
        return paymentld;
    }

    public void setPaymentld(String paymentld) {
        this.paymentld = paymentld;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentLinkReferenceld() {
        return razorpayPaymentLinkReferenceld;
    }

    public void setRazorpayPaymentLinkReferenceld(String razorpayPaymentLinkReferenceld) {
        this.razorpayPaymentLinkReferenceld = razorpayPaymentLinkReferenceld;
    }

    public String getRazorpayPaymentLinkStatus() {
        return razorpayPaymentLinkStatus;
    }

    public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
    }

    public String getRazorpayPaymentld() {
        return razorpayPaymentld;
    }

    public void setRazorpayPaymentld(String razorpayPaymentld) {
        this.razorpayPaymentld = razorpayPaymentld;
    }
}
