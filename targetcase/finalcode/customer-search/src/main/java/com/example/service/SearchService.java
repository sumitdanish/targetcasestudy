package com.example.service;

import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by summit on 10/7/17.
 */
public interface SearchService {

    public  List<Map<String,Object>> productList(String productName);
}
