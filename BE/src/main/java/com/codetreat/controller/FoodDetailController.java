package com.codetreat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codetreat.entity.FoodDetail;
import com.codetreat.entity.FoodDetailId;
import com.codetreat.service.FoodDetailService;

@RestController
public class FoodDetailController {
	private FoodDetailService foodDetailService;
	
	@Autowired
	public FoodDetailController(FoodDetailService foodDetailService) {
		this.foodDetailService = foodDetailService;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/food_details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FoodDetail>> findAllFoodDetail(){
		List<FoodDetail> foodDetail = foodDetailService.findAllFoodDetail();
		if(foodDetail.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<> (foodDetail, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/food_details/{foodId}/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<FoodDetail> findFoodDetailById(@PathVariable("foodId") Integer foodId, @PathVariable("resourceId") Integer resourceId ){
		Optional<FoodDetail> foodDetail = foodDetailService.findById(new FoodDetailId(foodId, resourceId));
		if(!foodDetail.isPresent()) {
			return new ResponseEntity<> (foodDetail.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<> (foodDetail.get(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/food_details/{foodId}&{resourceId}", method = RequestMethod.PUT)
	public ResponseEntity<FoodDetail> updateFoodDetail(
			@PathVariable("foodId") Integer foodId, 
			@PathVariable("resourceId") Integer resourceId,
			@RequestBody FoodDetail foodDetail){
		Optional<FoodDetail> currentFoodDetail = foodDetailService.findById(new FoodDetailId(foodId, resourceId));
		if(!currentFoodDetail.isPresent()) {
			return new ResponseEntity<> (currentFoodDetail.get(), HttpStatus.NO_CONTENT);
		}
		currentFoodDetail.get().setQuantitative(foodDetail.getQuantitative());
		currentFoodDetail.get().setUnit(foodDetail.getUnit());
		foodDetailService.save(currentFoodDetail.get());
		return new ResponseEntity<>(currentFoodDetail.get(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/food_details/{foodId}&{resourceId}", method = RequestMethod.DELETE)
	public ResponseEntity<FoodDetail> removeFoodDetail(
			@PathVariable("foodId") Integer foodId,
			@PathVariable("resourceId") Integer resourceId){
		Optional<FoodDetail> foodDetail = null;
		try {
			foodDetail = foodDetailService.findById(new FoodDetailId(foodId, resourceId));
		}catch (Exception e) {
			System.out.println("Loi sai o dau do roi");
		}
		
		
		if(!foodDetail.isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		foodDetailService.remove(foodDetail.get());
		return new ResponseEntity<> (foodDetail.get(), HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/food_details", method = RequestMethod.POST)
	public ResponseEntity<FoodDetail> addFoodDetail(
			@RequestBody FoodDetail foodDetail){
		Optional<FoodDetail> foodDetaiHaved = null;
		try {
			foodDetaiHaved = foodDetailService.findById(foodDetail.getFoodDetailId());
		}catch (Exception e) {
			System.out.println("Loi sai o dau do roi");
		}
		
		
		if(foodDetaiHaved.isPresent()) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}
		foodDetailService.save(foodDetail);
		//Get foodDetail and name resource
		
		Optional<FoodDetail> foodDetailAdded = foodDetailService.findById(foodDetail.getFoodDetailId());
		return new ResponseEntity<> (foodDetailAdded.get(), HttpStatus.CREATED);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/food_details/{foodId}", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDetail>> getFoodDetailByFoodId(@PathVariable("foodId") Integer foodId){
		List<FoodDetail> foodDetails = foodDetailService.findAllFoodDetail();
		if(foodDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		//Delete foodDetails is not child of Food has foodId id
		for(int i = 0 ; i < foodDetails.size(); i++) {
			if(foodDetails.get(i).getFoodDetailId().getFoodId() != foodId) {
				foodDetails.remove(i);
				--i;
			}
		}
		if(foodDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<> (foodDetails, HttpStatus.OK);
	}
	
	
	

	
}
