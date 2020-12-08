package com.codetreat.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "OD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long OrderId;
    
    @NotNull
    @Column(name = "TB_ID")
    Long tableId;
    
    @Column(name = "ODCODE")
    String orderCode;
    
    @Column(name = "STATUS")
    String status;
    
    @Column(name = "DATECHECKOUT")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date dateCheckout;   
    
    @Column(name = "DISCOUNT")
    Float discount;

    @Column(name = "ST_ID")
    Long staffID;

	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCheckout() {
		return dateCheckout;
	}

	public void setDateCheckout(Date dateCheckout) {
		this.dateCheckout = dateCheckout;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Long getStaffID() {
		return staffID;
	}

	public void setStaffID(Long staff) {
		this.staffID = staff;
	}

	public String getStatus() {
		return status;
	}

}
