package com.example.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.VehicleDetails;

public interface IVehicleService {
	List<VehicleDetails> sorting();
	Page<VehicleDetails> values_for_email_sender(LocalDateTime today, LocalDateTime five_mins_before, String car_no,
			 Double threshold_voltage,Pageable pageable);
	List<VehicleDetails> getAllDetails(LocalDateTime from, LocalDateTime to);
	

}
