package com.example.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.UserDetailsInterface;
import com.example.model.UserDetails;
import com.example.model.VehicleDetails;
import com.example.service.IUserService;
import com.example.service.IVehicleService;


@Controller
public class UserController {
//	Set<String>Car_container=new HashSet();
	
	@Autowired
	IUserService iuser_service;
	//@Autowired
	//IVehicleService ivehicle_service;
	@Autowired
	UserDetailsInterface userDetailsInterface;
	
	@RequestMapping(value="/")
	public String home_page() {
		System.out.println("in home page");
		return "home.jsp";
	}
	
	//for the home page 
	@RequestMapping(value="/home")
	public String home() {
		System.out.println("in home page");
		return "home.jsp";
	}
	
	//shifting to the sign up page from haoe page
	@RequestMapping(value="/SignUpPage")
	public String move_to_sign_up_page() {
		return "sign_up.jsp";
	}
	
	//called once when we click add car on the sign up page
	@RequestMapping(value = "/adding_car")
	public ModelAndView add_car_to_sign_up_page(@RequestParam String email,@RequestParam String password) {
		ModelAndView mv=new ModelAndView("car_add_here.jsp");
		mv.addObject("email", email);
		mv.addObject("password", password);
		return mv ;
	}
	
	@GetMapping("/add_car_and_insert_into_user_information")
	public ModelAndView checkcontainsCarAndAdd(UserDetails userDetails) {
		ModelAndView mv=new ModelAndView("car_add_here.jsp");
		userDetails.setEmail(userDetails.getEmail().trim());
		userDetails.setCar_no(userDetails.getCar_no().trim());
		mv.addObject("email",userDetails.getEmail());
		mv.addObject("password",userDetails.getPassword());
		UserDetails satisfied_val=iuser_service.containsCar(userDetails.getCar_no());
		if(satisfied_val==null)
			userDetailsInterface.save(userDetails);
		else {
			mv=new ModelAndView("Car_present.jsp");
			mv.addObject("car_no", userDetails.getCar_no());
		}
		return mv;
	}
	
	@RequestMapping(value="/SignInPage")
	public String move_to_sign_in_page() {
		return "sign_in.jsp";
	}
	@RequestMapping(value="/see the details")
	public ModelAndView move_to_success_or_login_page(@RequestParam String email,@RequestParam String password) {
		ModelAndView mv;
		email=email.trim();
		List<UserDetails> login_accepted=iuser_service.login_page1(email, password);
		for(UserDetails login:login_accepted) {
			if(login_accepted.size()!=0 && login.getPassword().equals(password)) {//second condition is used because of case-sensitivity
				mv=new ModelAndView("Entries.jsp");
				mv.addObject("email",email);
				return mv;
			}
		}
		mv=new ModelAndView("warnings.jsp");
		
		return mv;//Entries.jsp Warnings.jsp
	}
	
	
	
}
