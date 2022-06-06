package com.pitt.reminder.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tdoctor")
public class TDoctor {

	@Id
	@GeneratedValue
	private int doctorId;
	
	private String doctorName;
	
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getDoctorName() {
		return doctorName;
	}
	
	public void setDoctorName(String name) {
		this.doctorName = name;
	}
	
	@OneToMany(mappedBy = "doctor")
	Set<TMapping> mapping;
	
	
}
