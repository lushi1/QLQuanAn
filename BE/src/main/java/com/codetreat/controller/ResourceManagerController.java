package com.codetreat.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
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
import com.codetreat.service.JobService;

@RestController
public class ResourceManagerController {
	private JobService resourceService;
	
	@Autowired	
	public ResourceManagerController(JobService resourceService) {
		this.resourceService = resourceService;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Resource>> findAllResource(){
		List<Resource> resources = resourceService.findAllResource();
		if(resources.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources/{resourceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> getResourceById(
			@PathVariable("resourceId") Integer resourceId){
		Optional<Resource> resource =  resourceService.findById(resourceId);
			
		if(!resource.isPresent()) {
			return new ResponseEntity<>(resource.get(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(resource.get(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources/{resourceId}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Resource> updateFood(@Valid @PathVariable("resourceId") Integer resourceId,
			@RequestBody Resource resource){
		Optional<Resource> currentResource = resourceService.findById(resourceId);
		if(!currentResource.isPresent()) {
			return new ResponseEntity<>(currentResource.get(), HttpStatus.NO_CONTENT);
		}

		currentResource.get().setResourceName(resource.getResourceName());	
		resourceService.save(currentResource.get());
		return new ResponseEntity<>(currentResource.get(), HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources/{resourceId}", method = RequestMethod.DELETE)
	public ResponseEntity<Resource> deleteResource(@PathVariable("resourceId") Integer resourceId
			){
		Optional<Resource> resource = resourceService.findById(resourceId);
		if(!resource.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		resourceService.remove(resource.get());
		return new ResponseEntity<>(resource.get(), HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources", method = RequestMethod.POST)
	public ResponseEntity<Resource> createResource(@Valid @RequestBody Resource resource, UriComponentsBuilder builder){
		resourceService.save(resource);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/resource/{resourceId}")
				.buildAndExpand(resource.getResourceId()).toUri());
		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
}
