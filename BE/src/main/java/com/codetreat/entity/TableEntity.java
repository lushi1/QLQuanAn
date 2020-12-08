package com.codetreat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tables")
public class TableEntity {

    @Id
    @Column(name = "TB_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long TableId;
    
    @Column(name = "TBNAME")
    String tableName;
    
    @NotNull
    @Column(name = "TBSTATUS")
    Long tableStatus;

	public Long getTableId() {
		return TableId;
	}

	public void setTableId(Long tableId) {
		TableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(Long tableStatus) {
		this.tableStatus = tableStatus;
	}


}
