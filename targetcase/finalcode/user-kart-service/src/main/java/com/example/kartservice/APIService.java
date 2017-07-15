package com.example.kartservice;

import com.example.entity.UserConformationCheckout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by summit on 13/7/17.
 */
public interface APIService {


    public JSONArray checkoutAPI(UserConformationCheckout userConformationCheckout);
    public JSONObject cancelOrder(String orderId);
}
