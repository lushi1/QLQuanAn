package com.codetreat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.OrderEntity;
import com.codetreat.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
    OrderRepository orderRepo;
 
    public List<OrderEntity> getAll() {
    	
        return (List<OrderEntity>) orderRepo.findAll();
    }
    
    public List<OrderEntity> getByIdAndStatus(Long tableId, String tableStatus) {
    	
        return (List<OrderEntity>) orderRepo.getOrderEntity(tableId, tableStatus);
    }
    
    public List<OrderEntity> getByDateCheckout(Date dateFrom, Date dateUntil) {
    	
        return (List<OrderEntity>) orderRepo.getOrderEntityByDateCheckout(dateFrom, dateUntil);
    }
 
    public OrderEntity createSample(OrderEntity orderEntity) {
        return orderRepo.save(orderEntity);
    }
 
    public OrderEntity updateOrder(Long Id, OrderEntity orderEntity) {
    	OrderEntity updateOrder;
        Optional<OrderEntity> searchEntity = orderRepo.findById(Id);
        if (searchEntity.isPresent()) {
        	OrderEntity order = searchEntity.get();
        	order.setStatus(orderEntity.getStatus());
        	order.setOrderCode("HD0"+ Id);
        	order.setDateCheckout(orderEntity.getDateCheckout());
        	updateOrder = orderRepo.save(order);
        } else {
            throw new EntityNotFoundException();
        }
        return updateOrder;
    }
    
}
