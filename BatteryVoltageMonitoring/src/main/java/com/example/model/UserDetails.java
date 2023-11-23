package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	private Integer id;
	private String car_no;
	private String email;
	private String password;
	private Double threshold_voltage;
	
	public UserDetails() {
		super();
	}
	public UserDetails(Integer id, String car_no, String email, String password, Double threshold_voltage) {
		super();
		this.id = id;
		this.car_no = car_no;
		this.email = email;
		this.password = password;
		this.threshold_voltage = threshold_voltage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getThreshold_voltage() {
		return threshold_voltage;
	}
	public void setThreshold_voltage(Double threshold_voltage) {
		this.threshold_voltage = threshold_voltage;
	}
	
	

}
