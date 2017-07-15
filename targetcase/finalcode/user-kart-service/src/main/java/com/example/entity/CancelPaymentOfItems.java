package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by summit on 12/7/17.
 */

@Document(collection = "cancel_payment")
public class CancelPaymentOfItems {

        @Field
        private UserProductIdetifier _id;

        @Field
        private String Status;

        @Field
        private double refundableMoney;

        @Field
        private Date cancelDate;

        @Field
        private String orderId;


        public CancelPaymentOfItems(){

        }

        public CancelPaymentOfItems(UserProductIdetifier _id, String orderId, String status, double refundableMoney, Date cancelDate) {
                this._id = _id;
                Status = status;
                this.refundableMoney = refundableMoney;
                this.cancelDate = cancelDate;
                this.orderId = orderId;
        }

        public UserProductIdetifier get_id() {
                return _id;
        }

        public void set_id(UserProductIdetifier _id) {
                this._id = _id;
        }

        public String getStatus() {
                return Status;
        }

        public void setStatus(String status) {
                Status = status;
        }

        public double getRefundableMoney() {
                return refundableMoney;
        }

        public void setRefundableMoney(double refundableMoney) {
                this.refundableMoney = refundableMoney;
        }

        public Date getCancelDate() {
                return cancelDate;
        }

        public void setCancelDate(Date cancelDate) {
                this.cancelDate = cancelDate;
        }

        public String getOrderId() {
                return orderId;
        }

        public void setOrderId(String orderId) {
                this.orderId = orderId;
        }
}
