package com.example.mongoentity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by summit on 10/7/17.
 */

@Document(collection = "product_cache")
public class Product {



    @Field
    private ProductIndentifier _id;

    @Field
    private String productName;

    @Field
    private double productPrice;

    @Field
    private Integer productQuantity;

    @Field
    private String status;

    @Field
    private boolean productUpdateEvent;

    public Product(ProductIndentifier _id, String productName, double productPrice, Integer productQuantity, String status,boolean productUpdateEvent) {
        this._id = _id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.status = status;
        this.productUpdateEvent = productUpdateEvent;
    }

    public ProductIndentifier get_id() {
        return _id;
    }

    public void set_id(ProductIndentifier _id) {
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

    public boolean isProductUpdateEvent() {
        return productUpdateEvent;
    }

    public void setProductUpdateEvent(boolean productUpdateEvent) {
        this.productUpdateEvent = productUpdateEvent;
    }
}
