package com.example.daoserviceimpl;

import com.example.daoservice.DAOService;
import com.example.entity.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by summit on 9/7/17.
 */

@Service
public class DAOServiceImpl implements DAOService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void addProduct(ProductDetails productDetails) throws ConstraintViolationException {
        try{
            sessionFactory.getCurrentSession().save(productDetails);
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
    }


    @Override
    @Transactional
    public void changeState(ProductDetails productDetails) throws HibernateException,Exception {
        try{
            sessionFactory.getCurrentSession().update(productDetails);
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
    }

    /*
    *
    *String orderId, String productIsbnId, String customerId, byte isOrderConfirmed,
			byte isOrderCancel, byte isPaymentDone, String paymentMode, Date purchaseDate, String sellerId,
			Integer quantity
    * */


    @Override
    @Transactional
    public String consumerPurchaseDetails(PurchaseDetails purchaseDetails, ProductDetails productDetails){
        try{
            System.out.println("calling....");
            sessionFactory.getCurrentSession().update(productDetails);
            sessionFactory.getCurrentSession().flush();
            String pd = (String) sessionFactory.getCurrentSession().save(purchaseDetails);
            return pd;
        }catch (HibernateException ex){
            ex.printStackTrace();
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
        return null;
    }

    @Override
    @Transactional
    public PurchaseDetails getItemBySellerIdAndProductId(String sellerId,String productId,String userId) {


        return null;
    }

    @Override
    @Transactional
    public void updatePurchaseDetails(ProductDetails productDetails,PurchaseDetails purchaseDetails) throws HibernateException{
        try{
            sessionFactory.getCurrentSession().update(productDetails);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().update(purchaseDetails);
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
    }

    @Override
    @Transactional
    public List<PurchaseDetails> getPurchaseDetailsBySeller(String sellerId,String stmtType) {
        List<PurchaseDetails> purchaseDetails = null;
       try{
           Query query = sessionFactory.getCurrentSession().createQuery("From PurchaseDetails as P Where P.sellerId =:sellerId and P.isOrderCancel=:isOrderCancel and P.isOrderConfirmed=:isOrderConfirmed");
           query.setParameter("sellerId",sellerId);
           if(stmtType.equals("PURCHASED")){
               query.setParameter("isOrderCancel",(byte)0);
               query.setParameter("isOrderConfirmed",(byte)1);
           }else{
               query.setParameter("isOrderCancel",(byte)1);
               query.setParameter("isOrderConfirmed",(byte)1);
           }
           purchaseDetails = (List<PurchaseDetails>)query.list();
       }finally {
           sessionFactory.getCurrentSession().flush();
           sessionFactory.getCurrentSession().clear();
       }
        return purchaseDetails;
    }

    @Transactional
    @Override
    public ProductDetails getProductById(ProductDetailsId pid) throws HibernateException {
        ProductDetails product = null;
        try{
            product = (ProductDetails) sessionFactory.getCurrentSession().get(ProductDetails.class,pid);
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
        return product;
    }

    @Transactional
    @Override
    public PurchaseDetails getPurchaseDetailsByOrderId(String orderId) {
        PurchaseDetails purchaseDetails = null;
        try{
            purchaseDetails = (PurchaseDetails)sessionFactory.getCurrentSession().get(PurchaseDetails.class,orderId);
        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
        return purchaseDetails;
    }

    @Transactional
    @Override
    public List<ProductDetails> getSelletItemList(String sellerId,String type) throws HibernateException{
        List<ProductDetails> list = null;
        try{
            System.out.println("TYPE : "+type);
            Query query = sessionFactory.getCurrentSession().createQuery("From ProductDetails as P Where P.id.sellerId=:sellerId and P.isProductActive=:isProductActive");
            query.setParameter("sellerId",sellerId);
            if(type.equals("ACTIVE")){
                query.setParameter("isProductActive",(byte)1);
            }else{
                query.setParameter("isProductActive",(byte)0);
            }
            list = (List<ProductDetails>) query.list();

        }finally {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
        }
        return list;
    }
}
