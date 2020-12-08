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
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryEntity {
    @Id
    @Column(name = "CTG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long CategoryId;    
    @NotNull
    @Column(name = "CTGNAME")
    String categoryName;   
    @JsonIgnoreProperties(value = {"categoryEntity"})
	@OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL,
	        orphanRemoval = true)
    private List <FoodEntity> foodEntity = new ArrayList<FoodEntity>();
	public List<FoodEntity> getFoodEntity() {
		return foodEntity;
	}
	public void setFoodEntity(List<FoodEntity> foodEntity) {
		this.foodEntity = foodEntity;
	}
	public Long getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(Long categoryId) {
		CategoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    
}
