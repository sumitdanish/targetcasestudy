package com.example.dependencyentity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by summit on 12/7/17.
 */
public class UserConformationCheckout implements Serializable {



    private List<Product> checkout;

    private boolean isPaymetConfirm;

    private double totalAmountTobePay;

    private ModeOfPayment modeOfPayment;

    private String userId;

    public UserConformationCheckout(){

    }

    public UserConformationCheckout(String userId,List<Product> checkout, boolean isPaymetConfirm, double totalAmountTobePay, ModeOfPayment modeOfPayment) {
        this.checkout = checkout;
        this.isPaymetConfirm = isPaymetConfirm;
        this.totalAmountTobePay = totalAmountTobePay;
        this.modeOfPayment = modeOfPayment;
        this.userId = userId;
    }

    public List<Product> getCheckout() {
        return checkout;
    }

    public void setCheckout(List<Product> checkout) {
        this.checkout = checkout;
    }

    public boolean isPaymetConfirm() {
        return isPaymetConfirm;
    }

    public void setPaymetConfirm(boolean paymetConfirm) {
        isPaymetConfirm = paymetConfirm;
    }

    public double getTotalAmountTobePay() {
        return totalAmountTobePay;
    }

    public void setTotalAmountTobePay(double totalAmountTobePay) {
        this.totalAmountTobePay = totalAmountTobePay;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
