package com.example.daoserviceimpl;

import com.example.daoservice.DAOGetService;
import com.example.mongoentity.Product;
import com.example.repository.MongoDBRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class DAOServiceImpl implements DAOGetService {


    @Autowired
    public MongoDBRepository mongoDBRepository;


    @Override
    public List<Product> getProductDetails(String productName) throws JsonProcessingException, ParseException {
        List<Product> productList = mongoDBRepository.findByProductNameLike(productName);
        return productList;
    }

    @Override
    public void saveProduct(List<Product> product) {
        mongoDBRepository.save(product);
    }

    @Override
    public boolean update(List<Product> productList) {
        return false;
    }


}
