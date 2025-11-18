 package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.DeliveryLog;

public interface DeliveryLogRepository extends JpaRepository<DeliveryLog, Long> {
    List<DeliveryLog> findByCourierDetailsId(Long id);
    List<DeliveryLog> findByTrackingId(String trackingId);
}
