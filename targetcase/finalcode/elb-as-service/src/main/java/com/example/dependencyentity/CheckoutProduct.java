package com.example.dependencyentity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by summit on 12/7/17.
 */

@Document(collection = "checkout_list")
public class CheckoutProduct {

    @Field
    private UserProductIdetifier _id;

    @Field
    private String orderId;

    @Field
    private String productName;

    @Field
    private int quantity;

    @Field
    private Double price;

    @Field
    private Date checkooutDate;

    @Field
    private double paybleAmount;

    @Field
    private String checkoutStatus;

    public CheckoutProduct(){}

    public CheckoutProduct(UserProductIdetifier _id){
        this._id = _id;
    }

    public CheckoutProduct(UserProductIdetifier _id, String orderId,String productName,int quantity, Double price, Date checkooutDate,double paybleAmount,String checkoutStatus) {
        this._id = _id;
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.checkooutDate = checkooutDate;
        this.paybleAmount = paybleAmount;
        this.checkoutStatus = checkoutStatus;
    }

    public UserProductIdetifier get_id() {
        return _id;
    }

    public void set_id(UserProductIdetifier _id) {
        this._id = _id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCheckooutDate() {
        return checkooutDate;
    }

    public void setCheckooutDate(Date checkooutDate) {
        this.checkooutDate = checkooutDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPaybleAmount() {
        return paybleAmount;
    }

    public void setPaybleAmount(double paybleAmount) {
        this.paybleAmount = paybleAmount;
    }

    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
}
