package com.example.service;

import com.example.entity.ProductDetails;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
public interface GetServiceDB {

    public List<ProductDetails> getProduct(String productName);
}
