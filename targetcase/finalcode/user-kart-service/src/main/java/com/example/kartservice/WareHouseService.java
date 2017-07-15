package com.example.kartservice;

import org.json.simple.JSONObject;

/**
 * Created by summit on 12/7/17.
 */
public interface WareHouseService {

    public JSONObject update(JSONObject jsonObject,String userId,String updateEvent);


}
