package com.example.serviceImpl;

import com.example.daoservice.DAOGetService;
import com.example.entity.ProductDetails;
import com.example.mongoentity.Product;
import com.example.mongoentity.ProductIndentifier;
import com.example.service.GetService;
import com.example.service.GetServiceDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class GetServiceImpl implements GetService {

    @Autowired
    private DAOGetService daoGetService;

    @Autowired
    private GetServiceDB getServiceDB;

    @Override
    public JSONArray getProductDetails(String productName) throws JsonProcessingException, ParseException {
        List<Product> productList = daoGetService.getProductDetails(productName);
        if(productList.isEmpty() || productList == null || productList.size() == 0 ){
            List<ProductDetails> productDetailsList = getServiceDB.getProduct(productName);
            if(!productDetailsList.isEmpty() || productDetailsList != null || productDetailsList.size() != 0) {
                for (ProductDetails productDetails : productDetailsList) {
                    ProductIndentifier pid = new ProductIndentifier(productDetails.getId().getProductIsbnId(),productDetails.getId().getSellerId());
                    Product product = new Product(pid, productDetails.getProductName(), productDetails.getProductPrice(), productDetails.getProductQuantity(),"In Stock",false);
                    productList.add(product);
                }
                daoGetService.saveProduct(productList);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(productList);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonObject = (JSONArray) jsonParser.parse(jsonString);
        return jsonObject;
    }

    @Override
    public boolean update(String productName){
       /* List<Product> upateList = null;
        try {
            List<Product> productList = daoGetService.getProductDetails(productName);
            if(productList.isEmpty() || productList.size() == 0){
                return false;
            }
            upateList = new ArrayList<>();
            for(Product p : productList){
                p.setProductUpdateEvent(true);
                upateList.add(p);
            }
            return daoGetService.update(upateList);
        }catch (JsonProcessingException | ParseException  e){
            return false;
        }*/
       return false;
    }


}
