package com.pitt.reminder.api.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "treminder")
public class TReminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rid;
	private String title;
	
	@Column(name = "description")
	private String desc;
	
	private int duration;
	
	@Column(name = "created_time", columnDefinition = "TIMESTAMP")
	private Timestamp createdTime = new Timestamp(System.currentTimeMillis());
	
	private String priority;
	private boolean status;
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRid() {
		return rid;
	}
	
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Timestamp getCreatedTime() {
		//createdTime.getTime();
		//System.out.println("asdasdasdasdqweqweqweqwe??????");
//		createdTime = (Timestamp) new Date().getTime();
//		createdTime = new Timestamp(System.currentTimeMillis());
		System.out.println("this is created time");
		System.out.println(createdTime);
		return createdTime;
	}
	
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
//		return createdTime;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isOverdue() {

		Long overdueTime = this.getCreatedTime().getTime() + TimeUnit.HOURS.toMillis(this.getDuration());
		return (System.currentTimeMillis() > overdueTime);
		
	}
	
	public int getOverallStatus() {
		if (isStatus()) return 1;
		if (isOverdue()) return 2;
		return 0;
	}

	@ManyToOne
	@JoinColumn(name = "mid")
	TMapping mapping;
	
	public int getMid() {
		return mapping.getMid();
	}
	
	public void setMid(int mid) {
		this.mapping.setMid(mid);
	}
	
	public int getPatientId() {
		return mapping.getPatientId();
	}
	
	public int getDoctorId() {
		return mapping.getDoctorId();
	}

	public void setMapping(TMapping mapping) {
		this.mapping = mapping;
	}
	
	
}
