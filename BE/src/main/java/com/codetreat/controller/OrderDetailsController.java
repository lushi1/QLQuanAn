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

import com.codetreat.entity.OrderDetailsEntity;
import com.codetreat.service.OrderDetailsService;

@RestController
public class OrderDetailsController {
	

    @Autowired
    OrderDetailsService orderDetailsService;

    @CrossOrigin
    @RequestMapping(value = "/orderDetails")
    public List<OrderDetailsEntity> sample() {
        return orderDetailsService.getAll();
    }
    
    @CrossOrigin
    @RequestMapping(value = "/orderDetails/{id}", method = RequestMethod.GET)
    public List<OrderDetailsEntity> getOrderDetailsById(@PathVariable(value = "id") Long id) {
        return orderDetailsService.getOrderDetailsById(id);
    }
 
    @CrossOrigin
    @RequestMapping(value = "/createOrderDetails", method = RequestMethod.POST)
    public OrderDetailsEntity createSample( @RequestBody OrderDetailsEntity orderDetailsEntity) {
        return orderDetailsService.createSample(orderDetailsEntity);
    }
 
    @CrossOrigin
    @RequestMapping(value = "/deleteOrderDetails/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSample(@PathVariable(value = "id") Long id) {
        return orderDetailsService.deleteSample(id);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/deleteOrderDetails/{foodId}/{orderId}", method = RequestMethod.DELETE)
    public void deleteSample(@PathVariable(value = "foodId") Long foodId, @PathVariable(value = "orderId") Long orderId) {
        orderDetailsService.deleteByFoodIdAndOrderId(foodId, orderId);
    }

    
}
