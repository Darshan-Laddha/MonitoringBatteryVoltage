package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDetailsInterface;
import com.example.model.UserDetails;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDetailsInterface user_details;
	
	public UserDetails containsCar(String car_no) {
		UserDetails car=user_details.containsCar(car_no);
		System.out.print(car_no+" "+car);
		// TODO Auto-generated method stub
		return car;
	}

	@Override
	public List<UserDetails> login_page1(String email, String password) {
		List<UserDetails> login_info=user_details.login_page1(email, password);
		// TODO Auto-generated method stub
		return login_info;
	}
	@Override
	public UserDetails check_if_can_add_and_add(String car_no) {
		UserDetails add_to_vehicle_database= user_details.check_if_can_add_and_add(car_no);
		// TODO Auto-generated method stub
		return add_to_vehicle_database;
	}

	@Override
	public List<UserDetails> get_all_info() {
		
		List<UserDetails> all_info=user_details.get_all_info();// TODO Auto-generated method stub
		return all_info;
	}

	@Override
	public UserDetails check_matching_email_and_car_no(String car_no, String email) {
		UserDetails email_and_car_match= user_details.check_matching_email_and_car_no( car_no, email);// TODO Auto-generated method stub
		return email_and_car_match;
	}
}
