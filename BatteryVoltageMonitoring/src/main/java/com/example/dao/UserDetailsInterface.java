package com.example.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserDetails;

@Repository
public interface UserDetailsInterface extends CrudRepository<UserDetails, Integer>{
	//Step1 insert the details into the UserDetails table if the car number is not present
	@Query("select user_car from UserDetails user_car where user_car.car_no is ?1")
	 UserDetails containsCar(String car_no);
	
	//signin page
	@Query("from UserDetails user_info where user_info.email is ?1 and user_info.password is ?2")
	List<UserDetails> login_page1(String email,String password);
	
	//adding data to the voltage table
	@Query("select user_car from UserDetails user_car where user_car.car_no is ?1")
	UserDetails check_if_can_add_and_add(String car_no);
	
	//getting the user information to use for the vehicle table for getting all car_no,threshold voltage and email
	@Query("from UserDetails")
	List<UserDetails> get_all_info();
	
	//checking wheather user is registered to the car ot not 
	@Query("from UserDetails user_detail where user_detail.car_no is ?1 and user_detail.email is ?2")
	UserDetails check_matching_email_and_car_no(String car_no,String email);
}
