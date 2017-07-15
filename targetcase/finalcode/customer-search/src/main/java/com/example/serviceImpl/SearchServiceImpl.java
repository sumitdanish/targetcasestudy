package com.example.serviceImpl;

import com.example.daoService.DAOSearchService;
import com.example.service.SearchService;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private DAOSearchService daoSearchService;

    @Override
    public List<Map<String,Object>> productList(String productName) {
        JSONArray jsonArray = daoSearchService.productList(productName);
        List<Map<String,Object>> listJSON = new ArrayList<>();
        if(jsonArray.isEmpty() || jsonArray.size() == 0){
            Map<String,Object> warningMap = new LinkedHashMap<>();
            warningMap.put("Warning","Product not found !");
            listJSON.add(warningMap);
            return listJSON;
        }
        for(int i = 0 ;i<jsonArray.size();i++){
            LinkedHashMap<String,Object> jsonObject = (LinkedHashMap) jsonArray.get(i);
            jsonObject.put("isAdded",false);
            jsonObject.put("quantity",0);
            listJSON.add(jsonObject);
        }
        return listJSON;
    }
}
