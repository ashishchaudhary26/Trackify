package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CourierDetails;

public interface CourierDetailsRepository extends JpaRepository<CourierDetails, Long> {

}
