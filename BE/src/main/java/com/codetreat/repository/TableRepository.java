package com.codetreat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreat.entity.FoodEntity;
import com.codetreat.entity.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long>{

}
