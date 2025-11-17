package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	List<Staff> findByLocation(String location);

}
