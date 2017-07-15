package com.example.dependencyentity;

/**
 * Created by summit on 12/7/17.
 */
public class UserCheckOutList {

    private CheckoutProduct checkoutProduct;
    private boolean isPaymentDone;
    private boolean isCanceld;

    public UserCheckOutList(){

    }

    public UserCheckOutList(CheckoutProduct checkoutProduct, boolean isPaymentDone, boolean isCanceld) {
        this.checkoutProduct = checkoutProduct;
        this.isPaymentDone = isPaymentDone;
        this.isCanceld = isCanceld;
    }

    public CheckoutProduct getCheckoutProduct() {
        return checkoutProduct;
    }

    public void setCheckoutProduct(CheckoutProduct checkoutProduct) {
        this.checkoutProduct = checkoutProduct;
    }

    public boolean isPaymentDone() {
        return isPaymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        isPaymentDone = paymentDone;
    }

    public boolean isCanceld() {
        return isCanceld;
    }

    public void setCanceld(boolean canceld) {
        isCanceld = canceld;
    }
}
