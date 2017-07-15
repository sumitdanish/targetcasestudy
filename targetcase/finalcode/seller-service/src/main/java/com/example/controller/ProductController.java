package com.example.controller;

import com.example.entity.ProductDetails;
import com.example.entity.PurchaseDetails;
import com.example.entity.SellerStatement;
import com.example.service.ProductService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by summit on 9/7/17.
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject product){
        return new ResponseEntity<JSONObject>(productService.addProduct(product), HttpStatus.MULTI_STATUS);
    }

    @PutMapping(value = "update/{product_id}")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject, @PathVariable ("product_id") String id){
        return new ResponseEntity<JSONObject>(productService.changeState(jsonObject,id),HttpStatus.MULTI_STATUS);
    }

    @PostMapping(value = "checkout")
    public ResponseEntity<List<JSONObject>> consumerUpdate(@RequestBody JSONObject jsonObject){
        return new ResponseEntity<List<JSONObject>>(productService.consumerPurchaseDetails(jsonObject),HttpStatus.MULTI_STATUS);
    }

    @PutMapping(value = "updateAndCancel/{order_id}")
    public ResponseEntity<JSONObject> updatePurchaseDetails(@PathVariable("order_id") String userId){
        return new ResponseEntity<JSONObject>(productService.updatePurchaseDetails(userId),HttpStatus.MULTI_STATUS);
    }

    @GetMapping(value = "statement/{seller_id}")
    public ResponseEntity<SellerStatement> getSellerStatement(@PathVariable("seller_id") String sellerId,@RequestParam("type") String stmtType){
        return new ResponseEntity<SellerStatement>(productService.getPurchaseDetailsBySeller(sellerId,stmtType),HttpStatus.MULTI_STATUS);
    }

    @GetMapping(value = "selleritemlist/{seller_id}")
    public ResponseEntity<List<ProductDetails>> getSelletItemList(@PathVariable("seller_id") String sellerId,@RequestParam("type") String type){
        return new ResponseEntity<List<ProductDetails>>(productService.getSelletItemList(sellerId,type),HttpStatus.MULTI_STATUS);
    }

}
