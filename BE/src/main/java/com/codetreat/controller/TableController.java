package com.codetreat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codetreat.entity.CategoryEntity;
import com.codetreat.entity.FoodEntity;
import com.codetreat.entity.TableEntity;
import com.codetreat.service.CategoryService;
import com.codetreat.service.TableService;

@RestController
public class TableController {
	 
    @Autowired
    TableService tableService;
    
    @CrossOrigin
    @RequestMapping(value = "/table")
    public List<TableEntity> sample() {
        return tableService.getAll();
    }
    
    @CrossOrigin
    @RequestMapping(value = "/updateTable/{id}", method = RequestMethod.PUT)
    public TableEntity updateSample(@PathVariable(value = "id") Long id,
            @Valid @RequestBody TableEntity tableEntity) {
        return tableService.updateTable(id, tableEntity);
    }
}
