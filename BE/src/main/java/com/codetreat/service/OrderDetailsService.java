package com.codetreat.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codetreat.entity.FoodEntity;
import com.codetreat.entity.OrderDetailsEntity;
import com.codetreat.repository.FoodRepository;
import com.codetreat.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepo;
 
    public List<OrderDetailsEntity> getAll() {
    	
        return (List<OrderDetailsEntity>) orderDetailsRepo.findAll();
    }
    
    public List<OrderDetailsEntity> getOrderDetailsById(Long id) {
    	
       List<OrderDetailsEntity> orderDetailList = orderDetailsRepo.findAll();
       if(orderDetailList.isEmpty()) {
    	   
       }
       for(int i = 0; i < orderDetailList.size(); i++)
       {
    	   if(orderDetailList.get(i).getOrderId() != id)
    	   {
    		   orderDetailList.remove(i);
    		   i--;
    	   }
       }
       return orderDetailList;
    }
 
    public OrderDetailsEntity createSample(OrderDetailsEntity orderDetailsEntity) {

        return orderDetailsRepo.save(orderDetailsEntity);
    }
    
    public ResponseEntity<Object> deleteSample(Long Id) {
        Optional<OrderDetailsEntity> orderDetailsEntity = orderDetailsRepo.findById(Id);
        if (orderDetailsEntity.isPresent()) {
        	OrderDetailsEntity orderDetails = orderDetailsEntity.get();
        	orderDetailsRepo.delete(orderDetails);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
    
    public void deleteByFoodIdAndOrderId(Long foodId, Long orderId) {
        List<OrderDetailsEntity> orderDetailsEntity = orderDetailsRepo.findAll();
        for(int i = 0; i < orderDetailsEntity.size(); i++)
        {
     	   if(orderDetailsEntity.get(i).getOrderId() != orderId || orderDetailsEntity.get(i).getFoodEntity().getFoodId() != foodId)
     	   {
     		  orderDetailsEntity.remove(i);
     		   i--;
     	   }
        }
        Long max = 0l;
        for(int i = 0; i < orderDetailsEntity.size(); i++)
        {
        	if(orderDetailsEntity.get(i).getOrderDetailsId() > max)
        	{
        		max = orderDetailsEntity.get(i).getOrderDetailsId();
        	}
        }

        deleteSample(max);
    }
    
 
}
