package com.example.daoServiceImpl;

import com.example.daoService.DAOSearchService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class DAOSearchServiceImpl implements DAOSearchService {


    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JSONArray productList(String productName) {
        ResponseEntity<JSONArray> responseEntity = restTemplate.getForEntity("http://localhost:8383/product/cache/product/"+productName,JSONArray.class);
        return responseEntity.getBody();
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
