package com.codetreat.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.Resource;
import com.codetreat.repository.ResourceRepository;
import com.codetreat.service.JobService;


@Service
public class ResourceServiceImpl implements JobService {

	private ResourceRepository resourceRepository;
	
	@Autowired
	public ResourceServiceImpl (ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}
	@Override
	public List<Resource> findAllResource() {
		return (List<Resource>) resourceRepository.findAll();
	}

	@Override
	public Optional<Resource> findById(Integer id) {
		return resourceRepository.findById(id);
	}

	@Override
	public void save(Resource resource) {
		resourceRepository.save(resource);
	}

	@Override
	public void remove(Resource resorce) {
		resourceRepository.delete(resorce);
		
	}

}
