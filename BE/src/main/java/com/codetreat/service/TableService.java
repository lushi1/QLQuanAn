package com.codetreat.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreat.entity.FoodEntity;
import com.codetreat.entity.TableEntity;
import com.codetreat.repository.FoodRepository;
import com.codetreat.repository.TableRepository;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepo;
 
    public List<TableEntity> getAll() {
    	
        return (List<TableEntity>) tableRepo.findAll();
    }
 

    public TableEntity updateTable(Long Id, TableEntity tableEntity) {
    	TableEntity updatedTable;
        Optional<TableEntity> searchEntity = tableRepo.findById(Id);
        if (searchEntity.isPresent()) {
        	TableEntity table = searchEntity.get();
        	table.setTableStatus(tableEntity.getTableStatus());
            updatedTable = tableRepo.save(table);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedTable;
    }
}
