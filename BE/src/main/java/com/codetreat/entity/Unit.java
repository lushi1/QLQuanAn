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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "units")
public class Unit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNI_ID")
	private Integer unitId;
	
	@Column(name = "UNINAME")
	private String unitName;
	
	@JsonIgnoreProperties(value = {"unit"})
	@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<FoodDetail> foodDetail = new ArrayList<>();

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<FoodDetail> getFoodDetail() {
		return foodDetail;
	}

	public void setFoodDetail(List<FoodDetail> foodDetail) {
		this.foodDetail = foodDetail;
	}

	
	
	
	
}
