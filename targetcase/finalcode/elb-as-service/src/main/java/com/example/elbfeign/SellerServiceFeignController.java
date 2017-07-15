package com.example.elbfeign;

import com.example.dependencyentity.ProductDetails;
import com.example.dependencyentity.SellerStatement;
import com.example.elbservice.SellerServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by summit on 14/7/17.
 */

@RestController
@RequestMapping(value = "product/v1/seller")
public class SellerServiceFeignController {

    @Autowired
    private SellerServiceFeign sellerServiceFeign;


    @PostMapping(value = "save")
    @HystrixCommand(fallbackMethod = "saveFallback")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject product){
        return sellerServiceFeign.save(product);
    }

    public ResponseEntity<JSONObject> saveFallback(@RequestBody JSONObject product){
        return sellerServiceFeign.save(product);
    }

    @PutMapping(value = "update/{product_id}")
    @HystrixCommand(fallbackMethod = "updateFallback")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject, @PathVariable("product_id") String id){
        return sellerServiceFeign.update(jsonObject,id);
    }

    public ResponseEntity<JSONObject> updateFallback(@RequestBody JSONObject jsonObject, @PathVariable("product_id") String id){
        return sellerServiceFeign.update(jsonObject,id);
    }

    @PostMapping(value = "checkout")
    @HystrixCommand(fallbackMethod = "consumerUpdateFallback")
    public ResponseEntity<List<JSONObject>> consumerUpdate(@RequestBody JSONObject jsonObject){
        return sellerServiceFeign.consumerUpdate(jsonObject);
    }

    public ResponseEntity<List<JSONObject>> consumerUpdateFallback(@RequestBody JSONObject jsonObject){
        return sellerServiceFeign.consumerUpdate(jsonObject);
    }

    @PutMapping(value = "updateAndCancel/{order_id}")
    @HystrixCommand(fallbackMethod = "updatePurchaseDetailsFallback")
    public ResponseEntity<JSONObject> updatePurchaseDetails(@PathVariable("order_id") String userId){
        return sellerServiceFeign.updatePurchaseDetails(userId);
    }

    public ResponseEntity<JSONObject> updatePurchaseDetailsFallback(@PathVariable("order_id") String userId){
        return sellerServiceFeign.updatePurchaseDetails(userId);
    }

    @GetMapping(value = "statement/{seller_id}")
    @HystrixCommand(fallbackMethod = "getSellerStatementFallback")
    public ResponseEntity<SellerStatement> getSellerStatement(@PathVariable("seller_id") String sellerId, @RequestParam("type") String stmtType){
        return sellerServiceFeign.getSellerStatement(sellerId,stmtType);
    }

    public ResponseEntity<SellerStatement> getSellerStatementFallback(@PathVariable("seller_id") String sellerId, @RequestParam("type") String stmtType){
        return sellerServiceFeign.getSellerStatement(sellerId,stmtType);
    }

    @GetMapping(value = "selleritemlist/{seller_id}")
    @HystrixCommand(fallbackMethod = "getSelletItemListFallback")
    public ResponseEntity<List<ProductDetails>> getSelletItemList(@PathVariable("seller_id") String sellerId,@RequestParam("type") String type){
        return sellerServiceFeign.getSelletItemList(sellerId,type);
    }


    public ResponseEntity<List<ProductDetails>> getSelletItemListFallback(@PathVariable("seller_id") String sellerId,@RequestParam("type") String type){
        return sellerServiceFeign.getSelletItemList(sellerId,type);
    }
}
