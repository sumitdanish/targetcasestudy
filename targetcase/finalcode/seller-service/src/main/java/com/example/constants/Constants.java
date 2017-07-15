package com.example.constants;


/**
 * Created by summit on 9/7/17.
 */
public enum Constants {

    SELLER_ID("seller_id"),
    PRODUCT_ISBN("product_isbn"),
    PRODUCT_PRICE("product_price"),
    PRODUCT_QUANTITY("product_quantity"),
    PRODUCT_NAME("product_name"),
    PRODUCT_SELL_QUANTITY("product_sell_quantity"),
    IS_PRODUCT_ACTIVE("is_product_active")
    ;

    private String value;

    public String getValue() {
        return value;
    }

   private Constants(String value){
        this.value = value;
   }
}
