package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CourierDetails;

public interface CourierDetailsRepository extends JpaRepository<CourierDetails, Long> {
	Optional<CourierDetails> findByTrackingNumber(String trackingNumber);
	List<CourierDetails> findByCustomerUuid(Long customerId);
	List<CourierDetails> findByStaffId(Long staffId);


}
