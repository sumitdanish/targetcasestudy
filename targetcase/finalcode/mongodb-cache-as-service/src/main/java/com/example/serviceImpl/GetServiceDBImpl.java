package com.example.serviceImpl;

import com.example.daoservice.DAORDBMSGetService;
import com.example.entity.ProductDetails;
import com.example.service.GetServiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class GetServiceDBImpl implements GetServiceDB {


    @Autowired
    private DAORDBMSGetService daordbmsGetService;


    @Override
    public List<ProductDetails> getProduct(String productName){
        List<ProductDetails> productDetailsList = daordbmsGetService.getProduct(productName);
        return productDetailsList;
    }
}
