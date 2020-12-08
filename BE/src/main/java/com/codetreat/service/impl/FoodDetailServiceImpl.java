package com.codetreat.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.FoodDetail;
import com.codetreat.entity.FoodDetailId;
import com.codetreat.repository.FoodDetailRepository;
import com.codetreat.service.FoodDetailService;

@Service
public class FoodDetailServiceImpl implements FoodDetailService{
	
	private FoodDetailRepository foodDetailRepository;
	
	@Autowired
	public FoodDetailServiceImpl(FoodDetailRepository foodDetailRepository) {
		this.foodDetailRepository = foodDetailRepository;
	}

	public List<FoodDetail> findAllFoodDetail() {
		return (List<FoodDetail>)foodDetailRepository.findAll();
	}

	@Override
	public Optional<FoodDetail> findById(FoodDetailId foodDetailId) {
		return foodDetailRepository.findById(foodDetailId);
	}

	@Override
	public void save(FoodDetail foodDetail) {
		foodDetailRepository.save(foodDetail);
	}

	@Override
	public void remove(FoodDetail foodDetail) {
		foodDetailRepository.delete(foodDetail);
		
	}
	
}
