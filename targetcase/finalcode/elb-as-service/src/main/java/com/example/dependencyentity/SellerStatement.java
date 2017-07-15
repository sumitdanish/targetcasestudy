package com.example.dependencyentity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by summit on 13/7/17.
 */
public class SellerStatement implements Serializable {

    private List<PurchaseDetails> purchaseDetails;
    private double paybleAmount;

    public SellerStatement(){

    }

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    public double getPaybleAmount() {
        return paybleAmount;
    }

    public void setPaybleAmount(double paybleAmount) {
        this.paybleAmount = paybleAmount;
    }
}
