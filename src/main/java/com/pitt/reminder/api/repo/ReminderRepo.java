package com.pitt.reminder.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pitt.reminder.api.model.TReminder;;

public interface ReminderRepo extends JpaRepository<TReminder, Integer>{

	List<TReminder> findByMapping_Doctor_DoctorId(int doctorId);
	
	List<TReminder> findByMapping_Patient_PatientId(int patientId);
	
	List<TReminder> findByMapping_Mid(int mid);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update TReminder c set c.status = 1 WHERE c.rid = :rid")
    void setStatus(@Param("rid") int rid);
}
