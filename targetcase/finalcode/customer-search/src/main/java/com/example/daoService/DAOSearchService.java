package com.example.daoService;

import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
public interface DAOSearchService {
    public JSONArray productList(String productName);
}
