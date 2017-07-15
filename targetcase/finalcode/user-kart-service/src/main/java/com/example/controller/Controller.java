package com.example.controller;

import com.example.entity.Product;
import com.example.entity.UserCheckOutList;
import com.example.entity.UserConformationCheckout;
import com.example.kartservice.KartService;
import com.mongodb.util.JSON;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by summit on 12/7/17.
 */

@RestController
public class Controller {

    @Autowired
    private KartService kartService;

    @PostMapping(value = "addItem")
    public ResponseEntity<JSONObject> addItemToKart(@RequestBody JSONObject jsonObject){
        return new ResponseEntity<JSONObject>(kartService.addItemToKart(jsonObject), HttpStatus.MULTI_STATUS);
    }

    @GetMapping(value = "kartItemList/{user_id}")
    public ResponseEntity<UserConformationCheckout> getItemFromKart(@PathVariable("user_id") String userId){
        return new ResponseEntity<UserConformationCheckout>(kartService.getItemFromKart(userId.trim()),HttpStatus.MULTI_STATUS);
    }

    @DeleteMapping(value = "delete/{user_id}")
    public ResponseEntity<JSONObject> deleteItem(@PathVariable("user_id") String userId, @RequestBody JSONObject jsonObject){
        return new ResponseEntity<JSONObject>(kartService.deleteItemFromKart(jsonObject,userId),HttpStatus.MULTI_STATUS);
    }

    @PutMapping(value = "update/{user_id}")
    public ResponseEntity<JSONObject> updateItemToKart(@RequestBody JSONObject jsonObject,@PathVariable("user_id") String userId){
        return new ResponseEntity<JSONObject>(kartService.updateItemToKart(jsonObject,userId),HttpStatus.MULTI_STATUS);
    }

    @PostMapping(value = "checkout")
    public ResponseEntity<JSONArray> checkoutProduct(@RequestBody UserConformationCheckout userConformationCheckout){
        return new ResponseEntity<JSONArray>(kartService.checkoutProduct(userConformationCheckout),HttpStatus.MULTI_STATUS);
    }


    @GetMapping(value = "checkoutlist/{user_id}")
    public ResponseEntity<Object> checkOutItemList(@PathVariable("user_id") String userId){
        return new ResponseEntity<Object>(kartService.getCheckoutItemList(userId),HttpStatus.MULTI_STATUS);
    }

    @PutMapping(value = "cancel/{order_id}")
    public ResponseEntity<JSONObject> cancelItem(@PathVariable("order_id") String orderId){
        return new ResponseEntity<JSONObject>(kartService.cancelItem(orderId),HttpStatus.MULTI_STATUS);
    }

    @GetMapping(value = "cancelList/{user_id}")
    public ResponseEntity<Object> getCancelItemList(@PathVariable("user_id") String userId){
        return new ResponseEntity<Object>(kartService.getCancelItemList(userId),HttpStatus.MULTI_STATUS);
    }

}
