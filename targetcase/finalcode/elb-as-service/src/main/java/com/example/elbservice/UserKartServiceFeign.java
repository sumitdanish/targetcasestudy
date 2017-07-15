package com.example.elbservice;

import com.example.dependencyentity.UserConformationCheckout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by summit on 14/7/17.
 */
@FeignClient("user-kart-service")
public interface UserKartServiceFeign {

    @PostMapping(value = "addItem")
    public ResponseEntity<JSONObject> addItemToKart(@RequestBody JSONObject jsonObject);

    @GetMapping(value = "kartItemList/{user_id}")
    public ResponseEntity<UserConformationCheckout> getItemFromKart(@PathVariable("user_id") String userId);

    @DeleteMapping(value = "delete/{user_id}")
    public ResponseEntity<JSONObject> deleteItem(@PathVariable("user_id") String userId, @RequestBody JSONObject jsonObject);

    @PutMapping(value = "update/{user_id}")
    public ResponseEntity<JSONObject> updateItemToKart(@RequestBody JSONObject jsonObject,@PathVariable("user_id") String userId);

    @PostMapping(value = "checkout")
    public ResponseEntity<JSONArray> checkoutProduct(@RequestBody UserConformationCheckout userConformationCheckout);

    @GetMapping(value = "checkoutlist/{user_id}")
    public ResponseEntity<Object> checkOutItemList(@PathVariable("user_id") String userId);

    @PutMapping(value = "cancel/{order_id}")
    public ResponseEntity<JSONObject> cancelItem(@PathVariable("order_id") String orderId);

    @GetMapping(value = "cancelList/{user_id}")
    public ResponseEntity<Object> getCancelItemList(@PathVariable("user_id") String userId);

}
