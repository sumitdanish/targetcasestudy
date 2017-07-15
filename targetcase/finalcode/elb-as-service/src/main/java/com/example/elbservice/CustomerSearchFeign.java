package com.example.elbservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created by summit on 14/7/17.
 */

@FeignClient("customer-search")
public interface CustomerSearchFeign {

    @GetMapping("search/{product_id}")
    public ResponseEntity<List<Map<String,Object>>> search(@PathVariable("product_id") String id);
}
