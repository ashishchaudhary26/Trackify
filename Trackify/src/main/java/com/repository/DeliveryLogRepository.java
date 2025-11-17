package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.DeliveryLog;

public interface DeliveryLogRepository extends JpaRepository<DeliveryLog, Long> {

}
