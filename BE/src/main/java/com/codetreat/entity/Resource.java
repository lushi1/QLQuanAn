package com.codetreat.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "resources")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resource{

	@Id
	@Column(name = "RS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resourceId;

	@NotNull
	@Column(name = "RSNAME")
	private String resourceName; 
	
	@JsonIgnoreProperties(value = {"resource"})
	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<FoodDetail> foodDetails = new ArrayList<>();
	
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<FoodDetail> getFoodDetails() {
		return foodDetails;
	}

	public void setFoodDetails(List<FoodDetail> foodDetails) {
		this.foodDetails = foodDetails;
	}
	
	

}
