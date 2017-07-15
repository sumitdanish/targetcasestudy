package com.example.service;

import com.example.mongoentity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
public interface GetService {

    public JSONArray getProductDetails(String productName) throws JsonProcessingException, ParseException;
    public boolean update(String productName) throws JsonProcessingException, ParseException;
}
