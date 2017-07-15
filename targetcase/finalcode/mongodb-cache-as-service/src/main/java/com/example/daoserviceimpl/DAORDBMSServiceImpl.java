package com.example.daoserviceimpl;

import com.example.daoservice.DAORDBMSGetService;
import com.example.entity.ProductDetails;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by summit on 10/7/17.
 */

@Service
public class DAORDBMSServiceImpl implements DAORDBMSGetService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<ProductDetails> getProduct(String productName) throws HibernateException{
        String query = "From ProductDetails as prod where prod.productName like :productName and prod.isProductActive = :isProductActive";
        Query prodQuery = sessionFactory.getCurrentSession().createQuery(query);
        prodQuery.setParameter("productName","%"+productName+"%");
        prodQuery.setParameter("isProductActive",(byte)1);
        List<ProductDetails> productList = prodQuery.list();
        return productList;
    }
}
