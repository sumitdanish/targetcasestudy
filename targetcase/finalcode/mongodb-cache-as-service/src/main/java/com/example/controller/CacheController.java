package com.example.controller;

import com.example.entity.ProductDetails;
import com.example.service.GetService;
import com.example.service.GetServiceDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.ribbon.proxy.annotation.Http;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by summit on 10/7/17.
 */

@RestController
@RequestMapping(value = "product/cache")
public class CacheController {

    @Autowired
    private GetServiceDB getProduct;


    @GetMapping(value = "product/{product_name}")
    public ResponseEntity<List<ProductDetails>> get(@PathVariable("product_name") String product_name) {
        return new ResponseEntity<List<ProductDetails>>(getProduct.getProduct(product_name),HttpStatus.MULTI_STATUS);
    }


    /*Need to think*/


    @PutMapping(value = "update/{product_name}")
    public ResponseEntity<JSONObject> updateCahce(@PathVariable("product_name") String productName){
        JSONObject jsonObject = new JSONObject();
        return null;
    }
}
