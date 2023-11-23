package com.example.service;

import java.util.List;
import java.util.Set;

import com.example.model.UserDetails;

public interface IUserService {
	UserDetails containsCar(String car_no);
	List<UserDetails> login_page1(String email,String password);
	UserDetails check_if_can_add_and_add(String car_no);
	List<UserDetails> get_all_info();
	UserDetails check_matching_email_and_car_no(String car_no,String email);
}
