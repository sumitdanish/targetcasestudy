package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by summit on 12/7/17.
 */
@Document(collection = "user_kart")
public class Product {

    @Field
    private UserProductIdetifier _id;

    @Field
    private String productName;

    @Field
    private double productPrice;

    @Field
    private Integer productQuantity;

    @Field
    private String status;

    @Field
    private Date addedDate;

    public Product(){

    }

    public Product(UserProductIdetifier _id, String productName, double productPrice, Integer productQuantity, String status, Date addedDate) {
        this._id = _id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.status = status;
        this.addedDate = addedDate;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
