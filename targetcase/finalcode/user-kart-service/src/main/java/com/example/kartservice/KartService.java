package com.example.kartservice;

import com.example.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by summit on 11/7/17.
 */
public interface KartService {

    public JSONObject addItemToKart(JSONObject productJSON);
    public UserConformationCheckout getItemFromKart(String userId);
    public JSONObject updateItemToKart(JSONObject productJSON,String userId);
    public JSONObject deleteItemFromKart(JSONObject jsonObject,String productId);
    public JSONArray checkoutProduct(UserConformationCheckout userConformationCheckout);
    public Object getCheckoutItemList(String userId);
    public JSONObject cancelItem(String orderId);
    public Object getCancelItemList(String userId);

}
