package com.codetreat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreat.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
	
}
