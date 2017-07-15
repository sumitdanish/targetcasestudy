package com.example.kartserviceImpl;

import com.example.daokartservice.DAOKartService;
import com.example.entity.*;
import com.example.kartservice.APIService;
import com.example.kartservice.KartService;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoCursorNotFoundException;
import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by summit on 11/7/17.
 */

@Service
    public class KartServiceImpl implements KartService {

    @Autowired
    private DAOKartService daoKartService;

    @Autowired
    private APIService apiService;

    @Override
    public JSONObject addItemToKart(JSONObject productJSON) {

        JSONObject jsonObject = new JSONObject();
        try{
            UserProductIdetifier uid = new UserProductIdetifier(String.valueOf(productJSON.get("sellerName")),String.valueOf(productJSON.get("productId")),String.valueOf(productJSON.get("userId")));
            Product product =new Product(uid,String.valueOf(productJSON.get("productName")),(Double)productJSON.get("productPrice"),(Integer)productJSON.get("productQuantity"), String.valueOf(productJSON.get("status")),new Date());
            daoKartService.addOrUpdateItemToKart(product);
            jsonObject.put("success",String.valueOf(productJSON.get("productName")));
        }catch (DuplicateKeyException ex){
            jsonObject.put("Warning","Product name already in to kart");
            jsonObject.put("cause",ex.getMessage());
        }catch (Exception ex){
            jsonObject.put("Error",ex.getMessage());
        }
        return jsonObject;
    }

    @Override
    public UserConformationCheckout getItemFromKart(String userId) {
        List<Product> productList = daoKartService.getItemFromKart(userId);
        List<Product> checkoutProductList = new ArrayList<>();
        JSONObject responseJSON = new JSONObject();
        double price = 0;
        for(Product p : productList){
            price+=p.getProductPrice() * p.getProductQuantity();
            checkoutProductList.add(p);
        }
        ModeOfPayment modPay = new ModeOfPayment(false);
        UserConformationCheckout uck = new UserConformationCheckout(userId,checkoutProductList,false,price,modPay);
        return uck;
    }

    @Override
    public JSONObject updateItemToKart(JSONObject productJSON,String userId) {
        UserProductIdetifier uid =new UserProductIdetifier(String.valueOf(productJSON.get("sellerName")),String.valueOf(productJSON.get("productId")),userId.trim());
        Product product =new Product(uid,String.valueOf(productJSON.get("productName")),(Double)productJSON.get("productPrice"),(Integer)productJSON.get("productQuantity"), String.valueOf(productJSON.get("status")),new Date());
        daoKartService.addOrUpdateItemToKart(product);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",String.valueOf(productJSON.get("productName"))+" Is Updated Successfully");
        return jsonObject;
    }

    @Override
    public JSONObject deleteItemFromKart(JSONObject productJSON,String userId) {
        UserProductIdetifier uid = new UserProductIdetifier(String.valueOf(productJSON.get("sellerName")),String.valueOf(productJSON.get("productId")),userId);
        Product product =new Product(uid,String.valueOf(productJSON.get("productName")),(Double)productJSON.get("productPrice"),(Integer)productJSON.get("productQuantity"), String.valueOf(productJSON.get("status")),new Date());
        daoKartService.deletItemFromKart(product);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",String.valueOf(productJSON.get("productName"))+" Is Deleted");
        return jsonObject;
    }

    @Override
    public JSONArray checkoutProduct(UserConformationCheckout userConformationCheckout) {
        JSONArray jsonObject = new JSONArray();
        if(!userConformationCheckout.isPaymetConfirm() || !userConformationCheckout.getModeOfPayment().isCash() || userConformationCheckout.getTotalAmountTobePay() <= 0){
            JSONObject failJSON = new JSONObject();
            failJSON.put("Status"," Checkout Is Fail , Entered details is Not Sufficient !!!");
            jsonObject.add(failJSON);
            return jsonObject;
        }
        JSONArray response = apiService.checkoutAPI(userConformationCheckout);
        /*Need to call resttemplate*/
        int re = 0;
        List<Product> productJSONList = userConformationCheckout.getCheckout();
        List<CheckoutProduct> checkoutProducts = new ArrayList<>();
        Iterator it = productJSONList.iterator();
        while(it.hasNext() && re < response.size()){
            Map<String,Object> responseJSON = (LinkedHashMap<String,Object>) response.get(re);
            Product p = (Product) it.next();
            UserProductIdetifier uid = p.get_id();
            uid.setUserId((String)responseJSON.get("user_id"));
            CheckoutProduct checkoutProduct = new CheckoutProduct(
                    uid,
                    (String)responseJSON.get("order_id"),
                    p.getProductName(),
                    p.getProductQuantity(),
                    p.getProductPrice(),
                    new Date(),
                    (double)responseJSON.get("payble_amount"),
                    (String)responseJSON.get("Status"));
            checkoutProducts.add(checkoutProduct);
            re++;
        }
        daoKartService.checkoutProduct(productJSONList,checkoutProducts);
        return response;
    }

    @Override
    public Object getCheckoutItemList(String userId) {
        JSONObject jsonObject = new JSONObject();
        List<CheckoutProduct> checkoutProductList = daoKartService.getCheckoutProductList(userId);
        if(checkoutProductList.isEmpty() || checkoutProductList.size() ==0){
            jsonObject.put("Status","Kart is empty");
            return jsonObject;
        }
        return checkoutProductList;
    }

    @Override
    public JSONObject cancelItem(String orderId) {
       /* daoKartService.cancelItem(cancelPaymentOfItems,cp);*/
        JSONObject cancelResponse = apiService.cancelOrder(orderId);
        UserProductIdetifier uid = new UserProductIdetifier((String)cancelResponse.get("seller_id"),(String)cancelResponse.get("product_id"),(String)cancelResponse.get("user_id"));
        CancelPaymentOfItems cancelPaymentOfItems = new CancelPaymentOfItems(uid,(String)cancelResponse.get("order_id"),(String)cancelResponse.get("Status"),(double)cancelResponse.get("refundable_money"),new Date());
        CheckoutProduct cp = new CheckoutProduct(uid);
        daoKartService.cancelItem(cancelPaymentOfItems,cp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status","Cancelation Success !!!");
        return jsonObject;
    }

    @Override
    public Object getCancelItemList(String userId) {
        List<CancelPaymentOfItems> cancelPaymentOfItems = daoKartService.getCancelItemList(userId);
        JSONObject jsonObject = new JSONObject();
        if(cancelPaymentOfItems.isEmpty() || cancelPaymentOfItems.size() == 0){
            jsonObject.put("Status","No Item Is Canceld");
            return jsonObject;
        }
        return cancelPaymentOfItems;
    }

}
