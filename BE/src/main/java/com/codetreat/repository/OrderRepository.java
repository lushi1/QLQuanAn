package com.codetreat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codetreat.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query("from OrderEntity where tableId = ?1 and status = ?2")
	List <OrderEntity> getOrderEntity(Long tableId, String tableStatus);
	
	@Query("from OrderEntity where dateCheckout BETWEEN ?1 and ?2 and status = 1")
	List <OrderEntity> getOrderEntityByDateCheckout(Date dateFrom, Date dateUntil);
}
