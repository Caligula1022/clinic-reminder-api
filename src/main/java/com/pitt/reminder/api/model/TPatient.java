package com.pitt.reminder.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tpatient")
public class TPatient {

	@Id
	@GeneratedValue
	private int patientId;
	
	private String patientName;
	
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	@OneToMany(mappedBy = "patient")
	Set<TMapping> mapping;

}
