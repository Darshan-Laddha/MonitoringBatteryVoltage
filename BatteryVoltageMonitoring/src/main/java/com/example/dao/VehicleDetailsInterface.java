package com.example.dao;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.VehicleDetails;

@Repository
public interface VehicleDetailsInterface extends CrudRepository<VehicleDetails, Integer> {
	
	@Query("from VehicleDetails vehicle_detail order By vehicle_detail.timestamp DESC")
	List<VehicleDetails> sorting();

	@Query("from VehicleDetails vehicle_detail where vehicle_detail.timestamp between ?2 and ?1 and vehicle_detail.car_no is ?3  order by vehicle_detail.timestamp desc")
	Page<VehicleDetails> values_for_email_sender(LocalDateTime today, LocalDateTime five_mins_before, String car_no, Double threshold_voltage,Pageable pageable);

	@Query("from VehicleDetails vehicle_detail where vehicle_detail.timestamp between ?1 and ?2 ")
	List<VehicleDetails> getAllDetails(LocalDateTime from, LocalDateTime to);	
}