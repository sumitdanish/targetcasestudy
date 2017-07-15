package com.example.daokartserviceImpl;

import com.example.daokartservice.DAOKartService;
import com.example.entity.CancelPaymentOfItems;
import com.example.entity.CheckoutProduct;
import com.example.entity.Product;
import com.example.entity.UserProductIdetifier;
import com.example.repository.CancelRepository;
import com.example.repository.CheckoutRepository;
import com.example.repository.KartRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoCursorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by summit on 12/7/17.
 */

@Service
public class DAOKartServiceImpl implements DAOKartService {

    @Autowired
    private KartRepository kartRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private CancelRepository cancelRepository;


    @Override
    public void addOrUpdateItemToKart(Product product) {
        kartRepository.save(product);
    }

    @Override
    public void updateKart(Product product) {

    }


    @Override
    public List<Product> getItemFromKart(String userId){

        return kartRepository.findByUserId(userId);
    }

    @Override
    public void deletItemFromKart(Product product) {
        kartRepository.delete(product);
    }

    @Override
    public void checkoutProduct(List<Product> product, List<CheckoutProduct> checkoutProduct) {
        for(int i =0;i<checkoutProduct.size();i++){
            checkoutRepository.save(checkoutProduct.get(i));
            kartRepository.delete(product.get(i));
        }
    }

    @Override
    public List<CheckoutProduct> getCheckoutProductList(String userId) {
        return checkoutRepository.findByUserId(userId);
    }

    @Override
    public void cancelItem(CancelPaymentOfItems cancelPaymentOfItems, CheckoutProduct checkoutProduct) {
        checkoutRepository.delete(checkoutProduct);
        cancelRepository.save(cancelPaymentOfItems);
    }

    @Override
    public List<CancelPaymentOfItems> getCancelItemList(String userId) {
        return cancelRepository.findByUserId(userId);
    }
}
