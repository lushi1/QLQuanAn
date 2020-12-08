package com.codetreat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codetreat.entity.OrderDetailsEntity;
import com.codetreat.entity.OrderEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>{

}
