package com.codetreat.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.Unit;
import com.codetreat.repository.UnitRepository;
import com.codetreat.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService{

	private UnitRepository unitRepository;
	
	@Autowired
	public UnitServiceImpl(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}
	
	@Override
	public List<Unit> findAllUnit() {
		return unitRepository.findAll();
	}

	@Override
	public Optional<Unit> findById(Integer unitId) {
		return unitRepository.findById(unitId);
	}

	@Override
	public void save(Unit unit) {
		unitRepository.save(unit);
	}

	@Override
	public void remove(Unit unit) {
		unitRepository.delete(unit);
		
	}

}
