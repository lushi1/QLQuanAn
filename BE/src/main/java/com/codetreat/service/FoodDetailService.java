package com.codetreat.service;

import java.util.List;
import java.util.Optional;

import com.codetreat.entity.FoodDetail;
import com.codetreat.entity.FoodDetailId;

public interface FoodDetailService {
	List<FoodDetail> findAllFoodDetail();
	
	Optional<FoodDetail> findById(FoodDetailId foodDetailId);
	
	void save(FoodDetail foodDetail);
	void remove(FoodDetail foodDetail);

}
