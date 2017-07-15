package com.example.elbservice;

import com.example.dependencyentity.ProductDetails;
import com.example.dependencyentity.SellerStatement;
import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by summit on 14/7/17.
 */
@FeignClient("seller-service")
public interface SellerServiceFeign {

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject product);

    @PutMapping(value = "update/{product_id}")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject, @PathVariable("product_id") String id);

    @PostMapping(value = "checkout")
    public ResponseEntity<List<JSONObject>> consumerUpdate(@RequestBody JSONObject jsonObject);

    @PutMapping(value = "updateAndCancel/{order_id}")
    public ResponseEntity<JSONObject> updatePurchaseDetails(@PathVariable("order_id") String userId);

    @GetMapping(value = "statement/{seller_id}")
    public ResponseEntity<SellerStatement> getSellerStatement(@PathVariable("seller_id") String sellerId, @RequestParam("type") String stmtType);

    @GetMapping(value = "selleritemlist/{seller_id}")
    public ResponseEntity<List<ProductDetails>> getSelletItemList(@PathVariable("seller_id") String sellerId,@RequestParam("type") String type);
}
