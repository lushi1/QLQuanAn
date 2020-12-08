package com.codetreat.service;

import java.util.List;
import java.util.Optional;

import com.codetreat.entity.Resource;

public interface JobService {
	List<Resource> findAllResource();
	
	Optional<Resource> findById(Integer resourceId);
	
	void save(Resource resource);
	void remove(Resource resource);
}
