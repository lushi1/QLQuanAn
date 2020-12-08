package com.codetreat.service;

import java.util.List;
import java.util.Optional;

import com.codetreat.entity.Unit;

public interface UnitService {
List<Unit> findAllUnit();
	
	Optional<Unit> findById(Integer unitId);
	
	void save(Unit unit);
	void remove(Unit unit);
}
