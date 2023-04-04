package com.app.shop.orderservice.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedProductRequest {	
	
	private long customerId;
	
	private List<OrderedProducts> productList;
	
	private String  orderedTimeStamp;
	
	private PaymentMode paymentMode;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<OrderedProducts> getListOfProducts() {
		return productList;
	}

	public void setListOfProducts(List<OrderedProducts> productList) {
		this.productList = productList;
	}

	public String getOrderedTimeStamp() {
		return orderedTimeStamp;
	}

	public void setOrderedTimeStamp(String orderedTimeStamp) {
		this.orderedTimeStamp = orderedTimeStamp;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	
	
}
