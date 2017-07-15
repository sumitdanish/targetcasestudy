package com.example.entity;// default package
// Generated 9 Jul, 2017 10:23:33 PM by Hibernate Tools 5.2.1.Final

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * PurchaseDetails generated by hbm2java
 */
@Entity
@Table(name = "purchase_details", catalog = "seller_product")
public class PurchaseDetails implements Serializable {

	private String orderId;
	private String productIsbnId;
	private String customerId;
	private byte isOrderConfirmed;
	private byte isOrderCancel;
	private byte isPaymentDone;
	private String paymentMode;
	private Date purchaseDate;
	private String sellerId;
	private Integer quantity;
	private double pricePerItem;
	public PurchaseDetails() {
	}

	public PurchaseDetails(String orderId, String productIsbnId, String customerId, byte isOrderConfirmed,
						   byte isOrderCancel, byte isPaymentDone, String paymentMode, Date purchaseDate, double pricePerItem) {
		this.orderId = orderId;
		this.productIsbnId = productIsbnId;
		this.customerId = customerId;
		this.isOrderConfirmed = isOrderConfirmed;
		this.isOrderCancel = isOrderCancel;
		this.isPaymentDone = isPaymentDone;
		this.paymentMode = paymentMode;
		this.purchaseDate = purchaseDate;
		this.pricePerItem = pricePerItem;
	}

	public PurchaseDetails(String orderId, String productIsbnId, String customerId, byte isOrderConfirmed,
						   byte isOrderCancel, byte isPaymentDone, String paymentMode, Date purchaseDate, String sellerId,
						   Integer quantity, double pricePerItem) {
		this.orderId = orderId;
		this.productIsbnId = productIsbnId;
		this.customerId = customerId;
		this.isOrderConfirmed = isOrderConfirmed;
		this.isOrderCancel = isOrderCancel;
		this.isPaymentDone = isPaymentDone;
		this.paymentMode = paymentMode;
		this.purchaseDate = purchaseDate;
		this.sellerId = sellerId;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
	}

	@Id

	@Column(name = "order_id", unique = true, nullable = false, length = 256)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "product_isbn_id", nullable = false)
	public String getProductIsbnId() {
		return this.productIsbnId;
	}

	public void setProductIsbnId(String productIsbnId) {
		this.productIsbnId = productIsbnId;
	}

	@Column(name = "customer_id", nullable = false, length = 256)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "is_order_confirmed", nullable = false)
	public byte getIsOrderConfirmed() {
		return this.isOrderConfirmed;
	}

	public void setIsOrderConfirmed(byte isOrderConfirmed) {
		this.isOrderConfirmed = isOrderConfirmed;
	}

	@Column(name = "is_order_cancel", nullable = false)
	public byte getIsOrderCancel() {
		return this.isOrderCancel;
	}

	public void setIsOrderCancel(byte isOrderCancel) {
		this.isOrderCancel = isOrderCancel;
	}

	@Column(name = "is_payment_done", nullable = false)
	public byte getIsPaymentDone() {
		return this.isPaymentDone;
	}

	public void setIsPaymentDone(byte isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	@Column(name = "payment_mode", nullable = false, length = 30)
	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "purchase_date", nullable = false, length = 0)
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Column(name = "seller_id", length = 256)
	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price_per_item", nullable = false, precision = 22, scale = 0)
	public double getPricePerItem() {
		return this.pricePerItem;
	}

	public void setPricePerItem(double pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
}
