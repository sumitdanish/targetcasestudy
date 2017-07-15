package com.example.kartserviceImpl;

import com.example.entity.UserConformationCheckout;
import com.example.kartservice.APIService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by summit on 13/7/17.
 */

@Service
public class APIServiceImpl implements APIService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JSONArray checkoutAPI(UserConformationCheckout userConformationCheckout) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserConformationCheckout> entity = new HttpEntity<>(userConformationCheckout,headers);
        JSONArray responseArray = restTemplate.postForObject("http://localhost:8080/product/v1/seller/checkout",entity,JSONArray.class);
        return responseArray;
    }

    @Override
    public JSONObject cancelOrder(String orderId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/product/v1/seller/updateAndCancel/"+orderId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.PUT,entity,JSONObject.class);
        return responseEntity.getBody();
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
