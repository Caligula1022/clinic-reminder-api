package com.pitt.reminder.api.controller;


import com.pitt.reminder.api.model.TAuth;
import com.pitt.reminder.api.model.TMapping;
import com.pitt.reminder.api.model.TReminder;
import com.pitt.reminder.api.repo.AuthRepo;
import com.pitt.reminder.api.repo.MappingRepo;
import com.pitt.reminder.api.repo.ReminderRepo;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class ApiController {

	@Autowired
	MappingRepo mappingRepo;
	
	@Autowired
	ReminderRepo reminderRepo;
	
	@Autowired
	AuthRepo authRepo;
	
	@GetMapping("/api/mapping")
	public List<TMapping> getMapping() {
		return mappingRepo.findAll();
	}
	
	@GetMapping("/api/mapping/{mid}")
	public Optional<TMapping> getMappingById(@PathVariable("mid")  int dpid) {
		return mappingRepo.findById(dpid);
	}
	
	@GetMapping("/api/mapping/bydoctor/{doctorId}")
	public List<TMapping> getMappingByDoctorId(@PathVariable("doctorId") int doctorId){
		//return mappingRepo.findByDoctor_DoctorId(doctorId);
		return mappingRepo.findByDoctor_DoctorIdOrderByUnfinishedHighDescUnfinishedMiddleDescUnfinishedLowDesc(doctorId);
	}
	
	@GetMapping("/api/reminders")
	public List<TReminder> getAllReminder(){
		return reminderRepo.findAll();
	}
	
	@GetMapping("/api/reminders/{rid}")
	public Optional<TReminder> getRemidersById(@PathVariable("rid") int rid){
		return reminderRepo.findById(rid);
	}
	
	@GetMapping("/api/reminders/doctor/{doctorId}")
	public List<TReminder> getRemindersByDoctorId(@PathVariable("doctorId") int doctorId){
		return reminderRepo.findByMapping_Doctor_DoctorId(doctorId);
	}

	
	@GetMapping("/api/reminders/patient/{patientId}")
	public List<TReminder> getRemindersByPatientId(@PathVariable("patientId") int patientId){
		List<TReminder> results = reminderRepo.findByMapping_Patient_PatientId(patientId);
		Collections.sort(results, compareByOverallStatus);
		return results;
		//return reminderRepo.findByMapping_Patient_PatientIdOrderByOverdueAscStatusAscCreatedTimeAsc(patientId);
	}
	
	@GetMapping("/api/reminders/bymapping/{mid}")
	public List<TReminder> getRemindersByMappingId(@PathVariable("mid") int mid){
		List<TReminder> results = reminderRepo.findByMapping_Mid(mid);
		System.out.println("this is the results");

		//System.out.println(results.get(245).getCreatedTime());
//		if (results.contains(null)) {
//
//		}
		Collections.sort(results, compareByOverallStatus);
		return results;
	}
	
	@PutMapping("/api/reminders/done/{rid}")
	public Optional<TReminder> updateStatus(@PathVariable("rid") int rid) {
		System.out.println(rid);
		reminderRepo.setStatus(rid);
		return getRemidersById(rid);
	}
	
	@PostMapping("/api/reminder")
	public String saveReminder(@RequestBody TReminder entity){
		//System.out.println("asdasdasdqweqwe???");
		//entity.setCreatedTime(System.currentTimeMillis());
//		if (entity.getCreatedTime() == null) {
//			entity.setCreatedTime() =
//		}
		reminderRepo.save(entity);
		return "{\"status\": \"OK\"}";
	}
	
	@PostMapping("/api/auth/login")
	public String authUser(@RequestBody TAuth loginInfo) {
		
		TAuth auth = authRepo.findByUsername(loginInfo.getUsername());
		
		if (auth != null) {
			if (auth.getPassword().equals(loginInfo.getPassword())) {
				return "{\"status\" : \"AUTHORIZED\", \"ID\" : "+auth.getRev_id()+", \"role\": \""+auth.getRole()+"\"}";
			}else {
				return "{\"status\" : \"UNAUTHORIZED\"}";
			}
		}
		
		return "{\"status\" : \"UNAUTHORIZED\"}";
	}
	
	/* comparator */
	Comparator<TReminder> compareByOverallStatus = new Comparator<TReminder>() {
		@Override
		public int compare(TReminder a, TReminder b) {
			if (a.getOverallStatus() > b.getOverallStatus())
				return 1;
			else if (a.getOverallStatus() < b.getOverallStatus())
				return -1;
			else
				return 0;
			//return a.getOverallStatus() > b.getOverallStatus() ? 1 : -1;
		}
	};
	
	
}
