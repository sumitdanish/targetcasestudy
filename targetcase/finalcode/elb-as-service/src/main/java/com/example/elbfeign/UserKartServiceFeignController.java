package com.example.elbfeign;

import com.example.dependencyentity.UserConformationCheckout;
import com.example.elbservice.UserKartServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by summit on 14/7/17.
 */


@RestController
@RequestMapping(value = "product/v1/consumer")
public class UserKartServiceFeignController {

    @Autowired
    private UserKartServiceFeign userKartServiceFeign;

    @PostMapping(value = "addItem")
    @HystrixCommand(fallbackMethod = "addItemToKartFallback")
    public ResponseEntity<JSONObject> addItemToKart(@RequestBody JSONObject jsonObject){
        return userKartServiceFeign.addItemToKart(jsonObject);
    }


    public ResponseEntity<JSONObject> addItemToKartFallback(JSONObject jsonObject){

        return userKartServiceFeign.addItemToKart(jsonObject);
    }


    @GetMapping(value = "kartItemList/{user_id}")
    @HystrixCommand(fallbackMethod = "getItemFromKart")
    public ResponseEntity<UserConformationCheckout> getItemFromKart(@PathVariable("user_id") String userId){
        return userKartServiceFeign.getItemFromKart(userId);
    }


    public ResponseEntity<UserConformationCheckout> getItemFromKartFallback(@PathVariable("user_id") String userId){
        return userKartServiceFeign.getItemFromKart(userId);
    }


    @DeleteMapping(value = "delete/{user_id}")
    @HystrixCommand(fallbackMethod = "deleteItemFallback")
    public ResponseEntity<JSONObject> deleteItem(@PathVariable("user_id") String userId, @RequestBody JSONObject jsonObject){
        return  userKartServiceFeign.deleteItem(userId,jsonObject);
    }

    public ResponseEntity<JSONObject> deleteItemFallback(@PathVariable("user_id") String userId, @RequestBody JSONObject jsonObject){
        return  userKartServiceFeign.deleteItem(userId,jsonObject);
    }

    @PutMapping(value = "update/{user_id}")
    @HystrixCommand(fallbackMethod = "updateItemToKartFallback")
    public ResponseEntity<JSONObject> updateItemToKart(@RequestBody JSONObject jsonObject,@PathVariable("user_id") String userId){
        return userKartServiceFeign.updateItemToKart(jsonObject,userId);
    }

    public ResponseEntity<JSONObject> updateItemToKartFallback(@RequestBody JSONObject jsonObject,@PathVariable("user_id") String userId){
        return userKartServiceFeign.updateItemToKart(jsonObject,userId);
    }


    @PostMapping(value = "checkout")
    @HystrixCommand(fallbackMethod = "checkoutProductFallback")
    public ResponseEntity<JSONArray> checkoutProduct(@RequestBody UserConformationCheckout userConformationCheckout){
        return userKartServiceFeign.checkoutProduct(userConformationCheckout);
    }

    public ResponseEntity<JSONArray> checkoutProductFallback(@RequestBody UserConformationCheckout userConformationCheckout){
        return userKartServiceFeign.checkoutProduct(userConformationCheckout);
    }


    @GetMapping(value = "checkoutlist/{user_id}")
    @HystrixCommand(fallbackMethod = "checkOutItemListFallback")
    public ResponseEntity<Object> checkOutItemList(@PathVariable("user_id") String userId){
        return userKartServiceFeign.checkOutItemList(userId);
    }

    public ResponseEntity<Object> checkOutItemListFallback(@PathVariable("user_id") String userId){
        return userKartServiceFeign.checkOutItemList(userId);
    }

    @PutMapping(value = "cancel/{order_id}")
    @HystrixCommand(fallbackMethod = "cancelItemFallback")
    public ResponseEntity<JSONObject> cancelItem(@PathVariable("order_id") String orderId){
        return userKartServiceFeign.cancelItem(orderId);
    }

    public ResponseEntity<JSONObject> cancelItemFallback(@PathVariable("order_id") String orderId){
        return userKartServiceFeign.cancelItem(orderId);
    }

    @GetMapping(value = "cancelList/{user_id}")
    @HystrixCommand(fallbackMethod = "getCancelItemListFallback")
    public ResponseEntity<Object> getCancelItemList(@PathVariable("user_id") String userId){
        return userKartServiceFeign.getCancelItemList(userId);
    }

    public ResponseEntity<Object> getCancelItemListFallback(@PathVariable("user_id") String userId){
        return userKartServiceFeign.getCancelItemList(userId);
    }
}
