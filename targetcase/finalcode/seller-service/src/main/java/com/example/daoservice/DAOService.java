package com.example.daoservice;

import com.example.entity.ConsumerPurchase;
import com.example.entity.ProductDetails;
import com.example.entity.ProductDetailsId;
import com.example.entity.PurchaseDetails;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by summit on 9/7/17.
 */
public interface DAOService {
    public void addProduct(ProductDetails productDetails) throws ConstraintViolationException;
    public void changeState(ProductDetails productDetails) throws HibernateException,Exception;
    public String consumerPurchaseDetails(PurchaseDetails purchaseDetails, ProductDetails productDetails) throws Exception;
    public PurchaseDetails getItemBySellerIdAndProductId(String sellerId,String productId,String userId);
    public void updatePurchaseDetails(ProductDetails productDetails,PurchaseDetails purchaseDetails) throws HibernateException;
    public List<PurchaseDetails> getPurchaseDetailsBySeller(String sellerId,String stmtType);
    public ProductDetails getProductById(ProductDetailsId pid) throws HibernateException;
    public PurchaseDetails getPurchaseDetailsByOrderId(String orderId) throws HibernateException;
    public List<ProductDetails> getSelletItemList(String sellerId,String type) throws HibernateException;

}
