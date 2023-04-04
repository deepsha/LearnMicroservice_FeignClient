package com.app.shop.orderservice.entity;


import com.app.shop.orderservice.model.OrderedProducts;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ORDERED_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderedProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private long transactionId;
	
	@Column(name = "CUSTOMER_ID")
	private long customerId;
	
	
	@Embedded
	@Column(name = "ORDERED_PRODUCTS")
	private List<OrderedProducts> productList;
	
	@Column(name = "CUSTOMER_FIRST_NAME")
	private String firstName;
	
	@Column(name = "CUSTOMER_LAST_NAME") 
	private String lastName;
	
    @Column(name = "CUSTOMER_ADDRESS")
    private String emailAddress;
    
    @Column(name = "CUSTOMER_CONTACT")
    private String phoneNumber;
    
    @Column(name = "CUSTOMER_CITY")
    private String city;
    
    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddress;
    
    @Column(name = "CUSTOMER_BILL")
    private String orderedAmount;
    
    @Column(name = "ORDERED_STATUS")
    private String orderedStatus;

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

	public List<OrderedProducts> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderedProducts> productList) {
		this.productList = productList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getOrderedAmount() {
		return orderedAmount;
	}

	public void setOrderedAmount(String orderedAmount) {
		this.orderedAmount = orderedAmount;
	}

	public String getOrderedStatus() {
		return orderedStatus;
	}

	public void setOrderedStatus(String orderedStatus) {
		this.orderedStatus = orderedStatus;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
    
}
