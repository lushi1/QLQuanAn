package com.codetreat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fooddetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private FoodDetailId foodDetailId;
	
	@NotNull
	@Column(name = "Quantitative")
	private int quantitative;
	
	@JsonIgnoreProperties(value = {"foodDetail"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNI_ID")
	private Unit unit;
	
	@JsonIgnoreProperties(value = {"foodDetails"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RS_ID", insertable = false, updatable = false)
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public FoodDetailId getFoodDetailId() {
		return foodDetailId;
	}

	public void setFoodDetailId(FoodDetailId foodDetailId) {
		this.foodDetailId = foodDetailId;
	}

	public int getQuantitative() {
		return quantitative;
	}

	public void setQuantitative(int quantitative) {
		this.quantitative = quantitative;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	

	
}
