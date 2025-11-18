package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.DeliveryLog;
import com.repository.DeliveryLogRepository;

@Service
public class DeliveryLogService {

    @Autowired
    private DeliveryLogRepository deliveryLogRepository;

    public DeliveryLog saveDeliveryLog(DeliveryLog deliveryLog) {
        return deliveryLogRepository.save(deliveryLog);
    }

    public List<DeliveryLog> getAllDeliveryLogs() {
        return deliveryLogRepository.findAll();
    }

    public DeliveryLog getDeliveryLogById(Long id) {
        return deliveryLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryLog not found with id: " + id));
    }

    public List<DeliveryLog> getLogsByCourierId(Long courierId) {
        return deliveryLogRepository.findByCourierDetailsId(courierId);
    }

    public List<DeliveryLog> getLogsByTrackingId(String trackingId) {
        return deliveryLogRepository.findByTrackingId(trackingId);
    }

    public void deleteDeliveryLog(Long id) {
        deliveryLogRepository.deleteById(id);
    }
}
