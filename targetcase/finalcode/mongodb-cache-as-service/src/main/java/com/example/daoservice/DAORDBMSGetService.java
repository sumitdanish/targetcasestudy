package com.example.daoservice;

import com.example.entity.ProductDetails;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */
public interface DAORDBMSGetService {
    public List<ProductDetails> getProduct(String productName) throws HibernateException;
}
