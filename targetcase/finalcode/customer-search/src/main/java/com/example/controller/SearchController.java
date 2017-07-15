package com.example.controller;

import com.example.service.SearchService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by summit on 10/7/17.
 */

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;


    @GetMapping(value = "search/{product_name}")
    public ResponseEntity<List<Map<String,Object>>> search(@PathVariable ("product_name") String id){
        return new ResponseEntity<List<Map<String,Object>>>(searchService.productList(id), HttpStatus.MULTI_STATUS);
    }

}
