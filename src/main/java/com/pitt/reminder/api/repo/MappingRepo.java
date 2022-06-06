package com.pitt.reminder.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitt.reminder.api.model.TMapping;

public interface MappingRepo extends JpaRepository<TMapping, Integer>{
	
	List<TMapping> findByDoctor_DoctorId(int doctorId);
	
	List<TMapping> findByDoctor_DoctorIdOrderByUnfinishedHighDescUnfinishedMiddleDescUnfinishedLowDesc(int doctorId);
	
}
