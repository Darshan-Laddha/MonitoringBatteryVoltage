package com.example.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dao.VehicleDetailsInterface;
import com.example.model.VehicleDetails;

public class VehicleService implements IVehicleService {

	@Autowired
	private VehicleDetailsInterface vehicle_details;
	@Override
	public List<VehicleDetails> sorting() {
		List<VehicleDetails> sorted_by_timestamp=vehicle_details.sorting();
		// TODO Auto-generated method stub
		return sorted_by_timestamp;
	}
	@Override
	public Page<VehicleDetails> values_for_email_sender(LocalDateTime today, LocalDateTime five_mins_before, String car_no,
			 Double threshold_voltage,Pageable pageable) {
		Page<VehicleDetails> send_email_or_no=vehicle_details.values_for_email_sender(today, five_mins_before, car_no, threshold_voltage,pageable);
		// TODO Auto-generated method stub
		return send_email_or_no;
	}
	@Override
	public List<VehicleDetails> getAllDetails(LocalDateTime from, LocalDateTime to) {
		List<VehicleDetails> all_details=vehicle_details.getAllDetails(from,to);// TODO Auto-generated method stub
		return all_details;
	}
	
	

}
