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

    public static class DeliveryLogNotFoundException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DeliveryLogNotFoundException(Long id) {
            super("DeliveryLog not found with id: " + id);
        }
    }
    public DeliveryLog saveDeliveryLog(DeliveryLog deliveryLog) {
        return deliveryLogRepository.save(deliveryLog);
    }

    public List<DeliveryLog> getAllDeliveryLogs() {
        return deliveryLogRepository.findAll();
    }

    public DeliveryLog getDeliveryLogById(Long id) {
        return deliveryLogRepository.findById(id)
                .orElseThrow(() -> new DeliveryLogNotFoundException(id));
    }

    public DeliveryLog updateDeliveryLog(Long id, DeliveryLog updatedLog) {
        DeliveryLog existing = deliveryLogRepository.findById(id)
                .orElseThrow(() -> new DeliveryLogNotFoundException(id));

        existing.setTrackingId(updatedLog.getTrackingId());
        existing.setToName(updatedLog.getToName());
        existing.setDestinationAddress(updatedLog.getDestinationAddress());
        existing.setDestinationCity(updatedLog.getDestinationCity());

        return deliveryLogRepository.save(existing);
    }
    public void deleteDeliveryLog(Long id) {
        if (!deliveryLogRepository.existsById(id)) {
            throw new DeliveryLogNotFoundException(id);
        }
        deliveryLogRepository.deleteById(id);
    }
    
}
