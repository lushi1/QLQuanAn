package com.codetreat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.codetreat.entity.FoodEntity;
 
@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {


}