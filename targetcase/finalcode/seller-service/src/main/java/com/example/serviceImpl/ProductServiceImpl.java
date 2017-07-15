package com.example.serviceImpl;

import com.example.constants.Constants;
import com.example.daoservice.DAOService;
import com.example.entity.*;
import com.example.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by summit on 9/7/17.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private DAOService daoService;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JSONObject addProduct(JSONObject jsonObject) {
        JSONObject responseJSON = new JSONObject();
       try{
           ProductDetails productDetails = new ProductDetails();
           ProductDetailsId productDetailsId = new ProductDetailsId();
           productDetailsId.setSellerId(String.valueOf(jsonObject.get(Constants.SELLER_ID.getValue())));
           productDetailsId.setProductIsbnId(String.valueOf(jsonObject.get(Constants.PRODUCT_ISBN.getValue())));
           productDetails.setId(productDetailsId);
           productDetails.setProductName(String.valueOf(jsonObject.get(Constants.PRODUCT_NAME.getValue())));
           productDetails.setProductPrice(Double.parseDouble(String.valueOf(jsonObject.get(Constants.PRODUCT_PRICE.getValue()))));
           productDetails.setProductQuantity(Integer.parseInt(String.valueOf(jsonObject.get(Constants.PRODUCT_QUANTITY.getValue()))));
           productDetails.setProductSellQuantity(Integer.parseInt(String.valueOf(jsonObject.get(Constants.PRODUCT_SELL_QUANTITY.getValue()))));
           productDetails.setIsProductActive((byte)1);
           daoService.addProduct(productDetails);
           responseJSON.put("success","product added successfully");

       }catch (ConstraintViolationException dex){
            responseJSON.put("Error1",dex.getSQLException().getMessage());
       }catch (Exception ex){
            responseJSON.put("Error2",ex.getMessage());
       }
       return responseJSON;
    }

    @Override
    public JSONObject changeState(JSONObject jsonObject, String id) {
        JSONObject responseJSON = new JSONObject();
        try{
            ProductDetails productDetails = new ProductDetails();
            ProductDetailsId productDetailsId = new ProductDetailsId();
            productDetailsId.setSellerId(String.valueOf(jsonObject.get(Constants.SELLER_ID.getValue())));
            productDetailsId.setProductIsbnId(id.trim());
            productDetails.setId(productDetailsId);
            productDetails.setProductName(String.valueOf(jsonObject.get(Constants.PRODUCT_NAME.getValue())));
            productDetails.setProductPrice(Double.parseDouble(String.valueOf(jsonObject.get(Constants.PRODUCT_PRICE.getValue()))));
            productDetails.setProductQuantity(Integer.parseInt(String.valueOf(jsonObject.get(Constants.PRODUCT_QUANTITY.getValue()))));
            //Integer.parseInt(String.valueOf(jsonObject.get(Constants.PRODUCT_QUANTITY.getValue()))) //Constants.PRODUCT_SELL_QUANTITY.getValue()
            productDetails.setProductSellQuantity(Integer.parseInt(String.valueOf(jsonObject.get(Constants.PRODUCT_SELL_QUANTITY.getValue()))));
            productDetails.setIsProductActive((byte)Integer.parseInt(String.valueOf(jsonObject.get(Constants.IS_PRODUCT_ACTIVE.getValue()))));
            daoService.changeState(productDetails);
            responseJSON.put("success","product Updated successfully");
        }catch (HibernateException ex){
            responseJSON.put("Error1",ex.getMessage());
        }catch (Exception ex){
            responseJSON.put("Error1",ex.getMessage());
        }
        return responseJSON;
    }


    @Override
    public List<JSONObject> consumerPurchaseDetails(JSONObject jsonObject) {
        List<JSONObject> responseJSON = new ArrayList<>();
        try{
            List<LinkedHashMap<String,Object>> pro = (ArrayList<LinkedHashMap<String,Object>>)jsonObject.get("checkout");
            String userId = (String)jsonObject.get("userId");
            double paybleAmount=0.0;
            for(LinkedHashMap<String,Object> m : pro){
                Map<String,Object> m1 = (LinkedHashMap<String,Object>)m.get("_id");
                Map<String,Object> modeOfPayment = (LinkedHashMap<String,Object>)jsonObject.get("modeOfPayment");
                JSONObject successJSON = new JSONObject();
                UUID u = UUID.randomUUID();
                String oid = u.toString();
                oid = oid.substring(0,8);
                ProductDetailsId pid = new ProductDetailsId(String.valueOf(m1.get("sellerId")).trim(), String.valueOf(m1.get("productId")).trim());
                ProductDetails pd = getProductById(pid);
                if(pd == null){
                    JSONObject noPro = new JSONObject();
                    noPro.put("Status","Sorry No Product with this details");
                    responseJSON.add(noPro);
                    return responseJSON;
                }
                int requestedQuantity = (Integer) m.get("productQuantity");
                paybleAmount = requestedQuantity * pd.getProductPrice();
                pd.setProductQuantity((pd.getProductQuantity() - requestedQuantity));
                pd.setProductSellQuantity(requestedQuantity+pd.getProductSellQuantity());
                PurchaseDetails purchaseDetails = new PurchaseDetails(
                        oid,
                        pid.getProductIsbnId(),
                        userId.trim(),
                        (byte) 1,
                        (byte) 0,
                        (byte) 1,
                        (Boolean)modeOfPayment.get("cash")?"CASH":"Not Defined",
                        new Date(),
                        pid.getSellerId(),
                        (Integer) m.get("productQuantity"),
                        pd.getProductPrice());
                String orderId = daoService.consumerPurchaseDetails(purchaseDetails,pd);
                successJSON.put("user_id",userId);
                successJSON.put("product_id",String.valueOf(m1.get("productId")).trim());
                successJSON.put("seller_id",String.valueOf(m1.get("sellerId")).trim());
                successJSON.put("order_id",orderId);
                successJSON.put("payble_amount",paybleAmount);
                successJSON.put("Status","Order Placed !!!");
                responseJSON.add(successJSON);
            }
        }catch (HibernateException ex){
            ex.printStackTrace();
            JSONObject failJSON = new JSONObject();
            failJSON.put("Error1",ex.getMessage());

            responseJSON.add(failJSON);
        }catch (Exception ex){
            ex.printStackTrace();
            JSONObject failJSON = new JSONObject();
            failJSON.put("Error2",ex.getMessage());
        }
        return responseJSON;
    }

    @Override
    public JSONObject updatePurchaseDetails(String orderId) {
        JSONObject responseJSON = new JSONObject();
        try{
            PurchaseDetails purchaseDetails = getPurchaseDetailsByOrderId(orderId);
            ProductDetailsId proid = new ProductDetailsId(purchaseDetails.getSellerId(),purchaseDetails.getProductIsbnId());
            ProductDetails productDetails = getProductById(proid);
            if(purchaseDetails == null){
                responseJSON.put("Status","Order Already Updated or Doesn't Exist");
                return responseJSON;
            }
            productDetails.setProductQuantity(productDetails.getProductQuantity()+purchaseDetails.getQuantity());
            productDetails.setProductSellQuantity(Math.abs(productDetails.getProductSellQuantity()-purchaseDetails.getQuantity()));
            purchaseDetails.setIsOrderCancel((byte)1);
            responseJSON.put("order_id",orderId);
            responseJSON.put("product_id",purchaseDetails.getProductIsbnId());
            responseJSON.put("seller_id",purchaseDetails.getSellerId());
            responseJSON.put("user_id",purchaseDetails.getCustomerId());
            responseJSON.put("refundable_money",(purchaseDetails.getQuantity()*purchaseDetails.getPricePerItem()));
            responseJSON.put("Status","Cancellation is done !!!");
            daoService.updatePurchaseDetails(productDetails,purchaseDetails);
        }catch (HibernateException ex){
            ex.printStackTrace();
            responseJSON.put("Error1",ex.getMessage());
        }catch (Exception ex){
            responseJSON.put("Error2",ex.getMessage());
        }
        //ProductDetails productDetails =
        return responseJSON;
    }

    @Override
    public SellerStatement getPurchaseDetailsBySeller(String sellerId,String stmtType) {
        double amount = 0.0;
        SellerStatement smt = new SellerStatement();
        List<PurchaseDetails> purchaseDetails = daoService.getPurchaseDetailsBySeller(sellerId,stmtType);
        for(PurchaseDetails pd : purchaseDetails){
            amount+=pd.getQuantity() * pd.getPricePerItem();
        }
        smt.setPurchaseDetails(purchaseDetails);
        smt.setPaybleAmount(stmtType.equals("PURCHASED") ? amount : 0.0);
        return smt;
    }

    @Override
    public PurchaseDetails getPurchaseDetailsByOrderId(String orderId) {
        return daoService.getPurchaseDetailsByOrderId(orderId);
    }

    @Override
    public ProductDetails getProductById(ProductDetailsId pid) {
        ProductDetails productDetails = null;
        try{
            productDetails = daoService.getProductById(pid);
        }catch (HibernateException ex){
            //logeer
        }catch (Exception ex){
            //logger
        }
        return productDetails;
    }

    @Override
    public List<ProductDetails> getSelletItemList(String sellerId,String type) throws HibernateException {
        try{
            return daoService.getSelletItemList(sellerId,type);
        }catch (HibernateException ex){
            //logger
            System.out.println("In HIbernate");
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public PurchaseDetails getItemBySellerIdAndProductId(String sellerId, String productId,String userId) {
        return daoService.getItemBySellerIdAndProductId(sellerId,productId,userId);
    }



    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
