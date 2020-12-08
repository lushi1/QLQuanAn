package com.codetreat.controller;

import java.util.List;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codetreat.entity.FoodEntity;
import com.codetreat.service.FoodService;

@RestController
public class FoodController {
 
    @Autowired
    FoodService foodService;

    @CrossOrigin
    @RequestMapping(value = "/food")
    public List<FoodEntity> sample() {
        return foodService.getAll();
    }
 
    @CrossOrigin
    @RequestMapping(value = "/createfood", method = RequestMethod.POST)
    public FoodEntity createSample(@Valid @RequestBody FoodEntity foodEntity) {
        return foodService.createSample(foodEntity);
    }
 
    @CrossOrigin
    @RequestMapping(value = "/deletefood/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSample(@PathVariable(value = "id") Long id) {
        return foodService.deleteSample(id);
    }
 
    @CrossOrigin
    @RequestMapping(value = "/updatefood/{id}", method = RequestMethod.PUT)
    public FoodEntity updateSample(@PathVariable(value = "id") Long id,
            @Valid @RequestBody FoodEntity foodEntity) {
        return foodService.updateFood(id, foodEntity);
    }
    
    
 
}