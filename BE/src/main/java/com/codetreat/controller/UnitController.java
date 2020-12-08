package com.codetreat.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codetreat.entity.Resource;
import com.codetreat.entity.Unit;
import com.codetreat.service.UnitService;


@RestController
public class UnitController {
	private UnitService unitService;
	
	@Autowired
	public UnitController(UnitService unitService) {
		this.unitService = unitService;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/units", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Unit>> findAllUnit(){
		List<Unit> units = unitService.findAllUnit();
		if(units.isEmpty()) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(units, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/units/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Unit> findUnitById(@Valid @PathVariable ("unitId") Integer unitId){
		Optional<Unit> unit = unitService.findById(unitId);
		if(!unit.isPresent()) {
			return new ResponseEntity<>(unit.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<> (unit.get(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/units/{unitId}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Unit> updateUnit( @PathVariable("unitId") Integer unitId,
			@RequestBody Resource resource){
		Optional<Unit> currentUnit = unitService.findById(unitId);
		if(!currentUnit.isPresent()) {
			return new ResponseEntity<>(currentUnit.get(), HttpStatus.NO_CONTENT);
		}

		currentUnit.get().setUnitName(currentUnit.get().getUnitName());	
		return new ResponseEntity<>(currentUnit.get(), HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/units/{unitId}", method = RequestMethod.DELETE)
	public ResponseEntity<Unit> deleteResource(@PathVariable("unitId") Integer unitId
			){
		Optional<Unit> unit = unitService.findById(unitId);
		
		if(!unit.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		unitService.remove(unit.get());
		return new ResponseEntity<>(unit.get(), HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/units", method = RequestMethod.POST)
	public ResponseEntity<Unit> createResource(@Valid @RequestBody Unit unit, UriComponentsBuilder builder){
		unitService.save(unit);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/units/{unitId}")
				.buildAndExpand(unit.getUnitId()).toUri());
		return new ResponseEntity<>(unit, HttpStatus.CREATED);
	}
}

