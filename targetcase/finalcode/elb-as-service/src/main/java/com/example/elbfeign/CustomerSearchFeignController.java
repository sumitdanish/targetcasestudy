package com.example.elbfeign;

import com.example.elbservice.CustomerSearchFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * Created by summit on 14/7/17.
 */

@RestController
@RequestMapping(value = "product/v1/search1")
public class CustomerSearchFeignController {

    @Autowired
    private CustomerSearchFeign customerSearchFeign;

    @GetMapping(path = "search/{product_id}")
    @HystrixCommand(fallbackMethod = "searchFallBackCall")
    public ResponseEntity<List<Map<String,Object>>> search(@PathVariable("product_id") String id){
        return customerSearchFeign.search(id);
    }

    public ResponseEntity<List<Map<String,Object>>> searchFallBackCall(String id){
        return customerSearchFeign.search(id);
    }

}
