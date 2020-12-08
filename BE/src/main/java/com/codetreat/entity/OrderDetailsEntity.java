package com.codetreat.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderdetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetailsEntity {
    @Id
    @Column(name = "OD_DT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderDetailsId;
    
    @NotNull
    @Column(name = "OD_ID")
    Long orderId;
    
	@JsonIgnoreProperties(value = {"orderDetails"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FD_ID")
    FoodEntity foodEntity;
    
    @NotNull
    @Column(name = "AMOUNT")
    Long amount;
    
    @NotNull
    @Column(name = "PRICE")
    Long price;
    
    @Column(name = "DATETIMEOD")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date dateTimeOrder;

	public Long getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public FoodEntity getFoodEntity() {
		return foodEntity;
	}

	public void setFoodEntity(FoodEntity foodEntity) {
		this.foodEntity = foodEntity;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getDateTimeOrder() {
		return dateTimeOrder;
	}

	public void setDateTimeOrder(Date dateTimeOrder) {
		this.dateTimeOrder = dateTimeOrder;
	}


}
