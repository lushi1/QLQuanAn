package com.codetreat.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.codetreat.entity.CategoryEntity;
import com.codetreat.service.CategoryService;
 
@RestController
public class CategoryController {
 
    @Autowired
    CategoryService categoryService;
    
    @CrossOrigin
    @RequestMapping(value = "/category")
    public List<CategoryEntity> sample() {
        return categoryService.getAll();
    }
    
    @CrossOrigin
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public CategoryEntity createCategory(@Valid @RequestBody CategoryEntity categoryEntity) {
        return categoryService.createCategory(categoryEntity);
    }
 
    @CrossOrigin
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public CategoryEntity updateCategory(@PathVariable(value = "id") Long id,
            @Valid @RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(id, categoryEntity);
    }
    
}