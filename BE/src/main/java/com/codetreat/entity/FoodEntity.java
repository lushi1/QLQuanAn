package com.codetreat.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@Entity
@Table(name = "foods")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FoodEntity {
 
    @Id
    @Column(name = "FD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long FoodId;
    
    @JsonIgnoreProperties(value = {"foodEntity"} )
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "CTG_ID", insertable = false, updatable = false)
    CategoryEntity categoryEntity;
    
    @NotNull
    @Column(name = "CTG_ID")
    Integer categoryID;
    
    @NotNull
    @Column(name = "FDCODE")
    String foodCode;
 
    @NotNull
    @Column(name = "FDNAME")
    String foodName;
 
    @NotNull
    @Column(name = "FDPRICE")
    Integer foodPrice;
 
    @NotNull
    @Column(name = "FDPICTURE")
    String foodPicture;

    @NotNull
    @Column(name = "FDSTATUS")
    String foodStatus;
    
    @JsonIgnoreProperties(value = {"foodEntity"})
	@OneToMany(mappedBy = "foodEntity", cascade = CascadeType.ALL,
	        orphanRemoval = true)
    private List <OrderDetailsEntity> orderDetails = new ArrayList<OrderDetailsEntity>();
    
    
    public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public List<OrderDetailsEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getFoodId() {
        return FoodId;
    }
 
    public void setFoodId(Long foodId) {
    	FoodId = foodId;
    }
 
    public Integer getCategoryID() {
        return categoryID;
    }
 
    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

 
    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }
    
    public String getFoodCode() {
		return foodCode;
	}
    
    public String getFoodName() {
        return foodName;
    }
 
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    
    public Integer getFoodPrice() {
        return foodPrice;
    }
 
    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }
    
    public String getFoodPicture() {
        return foodPicture;
    }
 
    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }
    
    public String getFoodStatus() {
        return foodStatus;
    }
 
    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

	
 
}
