package com.codetreat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codetreat.entity.OrderEntity;
import com.codetreat.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
    @CrossOrigin
    @RequestMapping(value = "/order", produces = "application/json; charset=utf-8")
    public List<OrderEntity> sample() {
        return orderService.getAll();
    }
    
    @CrossOrigin
    @RequestMapping(value = "/orderByIdAndStatus/id={id}&status={status}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List <OrderEntity> orderByIdAndStatus(@PathVariable(value = "id" ) Long id, @PathVariable(value = "status" ) String status) {
        return orderService.getByIdAndStatus(id, status);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/orderByDateCheckout/dateFrom={dateFrom}&dateUntil={dateUntil}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List <OrderEntity> orderByDateCheckout(@PathVariable(value = "dateFrom" ) String dateFrom, @PathVariable(value = "dateUntil" ) String dateUntil) throws ParseException  {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date dateFromFR = simpleDateFormat.parse(dateFrom);
    	Date dateUntilUT = simpleDateFormat.parse(dateUntil);
    	
        return orderService.getByDateCheckout(dateFromFR, dateUntilUT);
    }
    
    
    @CrossOrigin
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public OrderEntity createSample(@Valid @RequestBody OrderEntity orderEntity) {
        return orderService.createSample(orderEntity);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/updateOrder/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public OrderEntity updateSample(@PathVariable(value = "id") Long id,
            @Valid @RequestBody OrderEntity orderEntity) {
        return orderService.updateOrder(id, orderEntity);
    }
    

}
