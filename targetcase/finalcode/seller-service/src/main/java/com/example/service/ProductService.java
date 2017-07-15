package com.example.service;

import com.example.entity.ProductDetails;
import com.example.entity.ProductDetailsId;
import com.example.entity.PurchaseDetails;
import com.example.entity.SellerStatement;
import org.hibernate.HibernateException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by summit on 9/7/17.
 */
public interface ProductService {

    public JSONObject addProduct(JSONObject jsonObject);
    public JSONObject changeState(JSONObject productJSON,String id);
    public PurchaseDetails getItemBySellerIdAndProductId(String sellerId, String productId,String userId);
    public List<JSONObject> consumerPurchaseDetails(JSONObject jsonObject);
    public JSONObject updatePurchaseDetails(String orderId);
    public SellerStatement getPurchaseDetailsBySeller(String sellerId,String stmtType);
    public PurchaseDetails getPurchaseDetailsByOrderId(String orderId);
    public ProductDetails getProductById(ProductDetailsId pid);
    public List<ProductDetails> getSelletItemList(String sellerId,String type) throws HibernateException;

}
