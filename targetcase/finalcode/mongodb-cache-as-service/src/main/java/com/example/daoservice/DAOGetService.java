package com.example.daoservice;

import com.example.mongoentity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
public interface DAOGetService {

    public List<Product> getProductDetails(String productName) throws JsonProcessingException, ParseException;
    public void saveProduct(List<Product> product);
    public boolean update(List<Product> productList);
}
