package com.codetreat.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.CategoryEntity;
import com.codetreat.repository.CategoryRepository;

@Service
public class CategoryService {
	 
	@Autowired
    CategoryRepository categoryRepo;
 
    public List<CategoryEntity> getAll() {
        return (List<CategoryEntity>) categoryRepo.findAll();
	}
    
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
        return categoryRepo.save(categoryEntity);
    }
 
    public CategoryEntity updateCategory(Long Id, CategoryEntity categoryEntity) {
    	CategoryEntity updatedFood;
        Optional<CategoryEntity> searchEntity = categoryRepo.findById(Id);
        if (searchEntity.isPresent()) {
        	CategoryEntity category = searchEntity.get();
        	category.setCategoryName(categoryEntity.getCategoryName());
            updatedFood = categoryRepo.save(category);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedFood;
    }


}
