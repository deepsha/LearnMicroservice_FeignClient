package com.app.shop.orderservice.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderedProductsResponse {
	
	private long transactionId;

	private long customerId;
	
	private List<OrderedProducts> listOfOrderedProducts;
	
	private String orderedTimeStamp;
	
	private String orderedLocation;
	
	private Double totalBill;
	
	private Double tax;
	
	private String paymentTimeStamp;
	
	private PaymentMode paymentMode;
	
	private PaymentStatus paymentStatus;

	public OrderedProductsResponse(long transactionId, long customerId, List<OrderedProducts> listOfOrderedProducts,
			String orderedTimeStamp, String orderedLocation, Double totalBill, Double tax, String paymentTimeStamp,
			PaymentMode paymentMode, PaymentStatus paymentStatus) {
		this.transactionId=transactionId;
		this.customerId=customerId;
		this.listOfOrderedProducts=listOfOrderedProducts;
		this.orderedTimeStamp=orderedTimeStamp;
		this.orderedLocation=orderedLocation;
		this.totalBill=totalBill;
		this.tax=tax;
		this.paymentTimeStamp=paymentTimeStamp;
		this.paymentMode=paymentMode;
		this.paymentStatus=paymentStatus;
		
		
		
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<OrderedProducts> getListOfProducts() {
		return listOfOrderedProducts;
	}

	public void setListOfProducts(List<OrderedProducts> listOfOrderedProducts) {
		this.listOfOrderedProducts = listOfOrderedProducts;
	}

	public String getOrderedTimeStamp() {
		return orderedTimeStamp;
	}

	public void setOrderedTimeStamp(String orderedTimeStamp) {
		this.orderedTimeStamp = orderedTimeStamp;
	}

	public String getOrderedLocation() {
		return orderedLocation;
	}

	public void setOrderedLocation(String orderedLocation) {
		this.orderedLocation = orderedLocation;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getPaymentTimeStamp() {
		return paymentTimeStamp;
	}

	public void setPaymentTimeStamp(String paymentTimeStamp) {
		this.paymentTimeStamp = paymentTimeStamp;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
