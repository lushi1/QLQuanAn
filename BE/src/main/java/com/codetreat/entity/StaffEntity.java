package com.codetreat.entity;




import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "staffs")
public class StaffEntity {
    @Id
    @Column(name = "ST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long staffId;
    
    @NotNull
    @Column(name = "STNAME")
    String staffName;
    
    @NotNull
    @Column(name = "STGENDER")
    Boolean staffGender;
    
    @NotNull
    @Column(name = "STDATEOFBIRTH")
    Date staffDOB;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Boolean getStaffGender() {
		return staffGender;
	}

	public void setStaffGender(Boolean staffGender) {
		this.staffGender = staffGender;
	}

	public Date getStaffDOB() {
		return staffDOB;
	}

	public void setStaffDOB(Date staffDOB) {
		this.staffDOB = staffDOB;
	}
    
}
