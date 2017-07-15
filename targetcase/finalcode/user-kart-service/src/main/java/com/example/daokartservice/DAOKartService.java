package com.example.daokartservice;

import com.example.entity.CancelPaymentOfItems;
import com.example.entity.CheckoutProduct;
import com.example.entity.Product;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoCursorNotFoundException;

import java.util.List;

/**
 * Created by summit on 12/7/17.
 */
public interface DAOKartService {

    public void addOrUpdateItemToKart(Product product);
    public void updateKart(Product product);
    public List<Product> getItemFromKart(String userId);
    public void deletItemFromKart(Product product);
    public void checkoutProduct(List<Product> product, List<CheckoutProduct> checkoutProduct);
    public List<CheckoutProduct> getCheckoutProductList(String userId);
    public void cancelItem(CancelPaymentOfItems cancelPaymentOfItems,CheckoutProduct checkoutProduct);
    public List<CancelPaymentOfItems> getCancelItemList(String userId);
}
