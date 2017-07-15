package com.example.dependencyentity;

/**
 * Created by summit on 12/7/17.
 */
public class ModeOfPayment {


    private boolean isCash;

    public ModeOfPayment(){

    }

    public ModeOfPayment(boolean isCash) {
        this.isCash = isCash;
    }

    public boolean isCash() {
        return isCash;
    }

    public void setCash(boolean cash) {
        isCash = cash;
    }
}
