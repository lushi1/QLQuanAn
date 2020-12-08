package com.codetreat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreat.entity.FoodDetail;
import com.codetreat.entity.FoodDetailId;

@Repository
public interface FoodDetailRepository extends JpaRepository<FoodDetail, FoodDetailId>{
}
