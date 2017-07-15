package com.example.dependencyentity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by summit on 12/7/17.
 */

public class UserProductIdetifier implements Serializable{


    @Field
    private String userId;

    @Field
    private String productId;

    @Field
    private String sellerId;

    public UserProductIdetifier(){

    }

    public UserProductIdetifier(String sellerId, String productId,String userId) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
