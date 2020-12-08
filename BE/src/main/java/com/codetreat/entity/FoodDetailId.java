package com.codetreat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FoodDetailId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "FD_ID")
	private Integer foodId;
	
	@Column(name = "RS_ID")
	private Integer resourceId;
	
	public FoodDetailId() {

	}
	
	public FoodDetailId(Integer foodId, Integer resourceId) {
		this.foodId = foodId;
		this.resourceId = resourceId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	
	
}
