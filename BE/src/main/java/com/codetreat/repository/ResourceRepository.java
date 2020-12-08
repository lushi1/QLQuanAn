package com.codetreat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreat.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer>{
	
}
