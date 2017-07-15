package com.example.kartserviceImpl;

import com.example.kartservice.WareHouseService;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by summit on 12/7/17.
 */

@Service
public class WareHouseImpl implements WareHouseService {


    @Override
    public JSONObject update(JSONObject jsonObject, String userId, String updateEvent) {
        return null;
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
