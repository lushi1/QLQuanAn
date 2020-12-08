package com.codetreat.service;

import java.util.List;
import java.util.Optional;
 
import javax.persistence.EntityNotFoundException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.codetreat.entity.FoodEntity;
import com.codetreat.repository.FoodRepository;
 
@Service
public class FoodService {
 
    @Autowired
    FoodRepository foodRepo;
 
    public List<FoodEntity> getAll() {
    	
        return (List<FoodEntity>) foodRepo.findAll();
    }
 
    public FoodEntity createSample(FoodEntity foodEntity) {
        return foodRepo.save(foodEntity);
    }
 
    public FoodEntity updateFood(Long Id, FoodEntity foodEntity) {
        FoodEntity updatedFood;
        Optional<FoodEntity> searchEntity = foodRepo.findById(Id);
        if (searchEntity.isPresent()) {
        	FoodEntity food = searchEntity.get();
            food.setFoodCode(foodEntity.getFoodCode());
            food.setFoodName(foodEntity.getFoodName());
            food.setFoodPrice(foodEntity.getFoodPrice());
            food.setFoodPicture(foodEntity.getFoodPicture());
            food.setFoodStatus(foodEntity.getFoodStatus());
            food.setCategoryID(foodEntity.getCategoryID());
            updatedFood = foodRepo.save(food);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedFood;
    }
 
    public ResponseEntity<Object> deleteSample(Long Id) {
        Optional<FoodEntity> foodEntity = foodRepo.findById(Id);
        if (foodEntity.isPresent()) {
            FoodEntity food = foodEntity.get();
            foodRepo.delete(food);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}