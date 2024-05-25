package com.example.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.VehicleDetailsInterface;
import com.example.model.UserDetails;
import com.example.model.VehicleDetails;
import com.example.service.IUserService;
import com.example.service.IVehicleService;
import com.example.service.email_sender;

@RestController
public class VoltageController {
	
	@Autowired
	VehicleDetailsInterface vehicleDetailsInterface;
	VehicleDetails vehicle_details=new VehicleDetails();
	
	@Autowired
	IUserService iUserService;
	IVehicleService ivehicleService;
	@RequestMapping(value="/add_data_in_sql",method=RequestMethod.POST)
	public ResponseEntity add_data_in_sql(@RequestBody String vehicle_data) {
		
		String single_data[]=vehicle_data.split(";");
		System.out.println(single_data[0]);
		LocalDateTime ldt=LocalDateTime.now();
		int first=single_data[0].indexOf("'");
		//System.out.println(first);
		int last=single_data[0].lastIndexOf("'");
		//System.out.println(last);
		int index_for_voltage_getting=single_data[1].indexOf(",");
		vehicle_details.setId(null);//indicates insert operation if its not null that means update operation
		vehicle_details.setCar_no(single_data[0].substring(first+1,last));
		vehicle_details.setVoltage(Double.parseDouble(single_data[1].substring(index_for_voltage_getting+1,single_data[1].length()-1)));
		vehicle_details.setTimestamp(ldt);
		if(iUserService.check_if_can_add_and_add(vehicle_details.getCar_no())!=null) {
			vehicleDetailsInterface.save(vehicle_details);
			List<VehicleDetails> sorted_val=vehicleDetailsInterface.sorting();
		}	
		return ResponseEntity.ok().body("");
	}
	
	@Scheduled(fixedDelay = 1*60*1000)
	public ResponseEntity fetchData() throws Exception {
		List<UserDetails>all_info= iUserService.get_all_info();
		for(UserDetails each_info:all_info) {
			String car_no=each_info.getCar_no();
			String email=each_info.getEmail();
			Double threshold_voltage=each_info.getThreshold_voltage();
			values_for_vehicle_database_comparison(car_no,email,threshold_voltage);
			
			System.out.println(each_info.getCar_no()+"  "+each_info.getEmail()+"  "+each_info.getThreshold_voltage());
		}
		return ResponseEntity.ok().body("");
	}

	//using the car_no,email and threshold voltage for the other table 
	private void values_for_vehicle_database_comparison(String car_no, String email, Double threshold_voltage) throws Exception {
		LocalDateTime today=LocalDateTime.now().withNano(0);
		LocalDateTime five_mins_before=LocalDateTime.now().minusMinutes(5).withNano(0);
		String today_sql=today.toString().replace('T', ' ');
		String five_mins_before_today_sql=five_mins_before.toString().replace('T',' ');
		System.out.println(today);
		System.out.println(five_mins_before);
		Page<VehicleDetails> send_email_or_no=vehicleDetailsInterface.values_for_email_sender(today,five_mins_before,car_no,threshold_voltage,(Pageable) PageRequest.of(0,1));
		if(send_email_or_no!=null)
		for(VehicleDetails each_email:send_email_or_no) {
			if(each_email.getVoltage()<threshold_voltage)
				email_sender.sendmail(email,car_no,each_email.getVoltage());
		}
		//System.out.println(send_email_or_no.getCar_no()+"is car no  "+send_email_or_no.getVoltage()+"is voltage ");
		/*if(send_email_or_no!=null) {
			System.out.println(send_email_or_no.getCar_no()+"  "+send_email_or_no.getVoltage());
		}*/
		// TODO Auto-generated method stub
	}
	//showing all the details between two timestamps
	@RequestMapping(value="/show_details")
	 public ResponseEntity<Object> Showing_details(@RequestParam String FromDate, @RequestParam String ToDate,@RequestParam String Car_no,@RequestParam String email) throws Exception{
		List<VehicleDetails>all_details=new ArrayList();
		FromDate=FromDate.trim();
		ToDate=ToDate.trim();
		LocalDateTime From=LocalDateTime.parse(FromDate+'T'+"00:00:00");
		LocalDateTime To=LocalDateTime.parse(ToDate+'T'+"23:59:59");
		Car_no=Car_no.trim();
		if(iUserService.check_matching_email_and_car_no(Car_no, email)==null)
		return ResponseEntity.ok().body("");
		else {	
			all_details=vehicleDetailsInterface.getAllDetails(From,To);
		}
		return ResponseEntity.ok().body(all_details);
	}
}
