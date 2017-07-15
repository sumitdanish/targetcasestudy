package com.example.mongoentity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by summit on 11/7/17.
 */
public class ProductIndentifier implements Serializable{

    @Field
    private String productId;

    @Field
    private String selletName;

    public ProductIndentifier(String productId, String selletName) {
        this.productId = productId;
        this.selletName = selletName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSelletName() {
        return selletName;
    }

    public void setSelletName(String selletName) {
        this.selletName = selletName;
    }
}
