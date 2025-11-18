package com.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.CourierDetails;
import com.entity.DeliveryLog;
import com.entity.Staff;
import com.repository.CourierDetailsRepository;
import com.repository.StaffRepository;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CourierDetailsRepository courierDetailsRepository;

    @Autowired
    private DeliveryLogService deliveryLogService; // new dependency for autologs

    public static class StaffNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public StaffNotFoundException(Long id) {
            super("Staff not found with id: " + id);
        }
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));
    }

    public Staff updateStaff(Long id, Staff updatedStaff) {
        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(id));

        existing.setName(updatedStaff.getName());
        existing.setLocation(updatedStaff.getLocation());
        existing.setPassword(updatedStaff.getPassword());

        return staffRepository.save(existing);
    }

    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new StaffNotFoundException(id);
        }
        staffRepository.deleteById(id);
    }

    public List<CourierDetails> getAssignedParcels(Long staffId) {
        return courierDetailsRepository.findByStaffId(staffId);
    }

    // in-memory filter for available (unassigned) parcels
    public List<CourierDetails> findAvailableParcels() {
        return courierDetailsRepository.findAll().stream()
                .filter(cd -> cd.getStaff() == null)
                .collect(Collectors.toList());
    }

    // in-memory filter by city
    public List<CourierDetails> findAvailableParcelsByCity(String city) {
        return courierDetailsRepository.findAll().stream()
                .filter(cd -> cd.getStaff() == null)
                .filter(cd -> cd.getDestinationCity() != null && cd.getDestinationCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // assign courier to staff and set status = "PICKED_UP" and create a DeliveryLog
    @Transactional
    public CourierDetails pickCourier(Long staffId, Long courierId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));

        CourierDetails courier = courierDetailsRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Courier not found with id: " + courierId));

        if (courier.getStaff() != null) {
            throw new RuntimeException("Courier already assigned to staff id: " + courier.getStaff().getId());
        }

        courier.setStaff(staff);
        courier.setStatus("PICKED_UP");
        CourierDetails saved = courierDetailsRepository.save(courier);

        // create delivery log
        DeliveryLog log = new DeliveryLog();
        log.setTrackingId(saved.getTrackingNumber());
        log.setCourierDetails(saved);
        log.setEventType("PICKED_UP");
        log.setEventTime(Instant.now());
        log.setPerformedBy(staff.getName());
        deliveryLogService.saveDeliveryLog(log);

        return saved;
    }

    // mark courier as delivered (must be assigned to this staff) and create DeliveryLog
    @Transactional
    public CourierDetails deliverCourier(Long staffId, Long courierId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new StaffNotFoundException(staffId));

        CourierDetails courier = courierDetailsRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Courier not found with id: " + courierId));

        if (courier.getStaff() == null) {
            throw new RuntimeException("Courier is not assigned to any staff.");
        }

        if (!courier.getStaff().getId().equals(staff.getId())) {
            throw new RuntimeException("Courier is assigned to another staff (id: " + courier.getStaff().getId() + ").");
        }

        if ("DELIVERED".equalsIgnoreCase(courier.getStatus())) {
            throw new RuntimeException("Courier already delivered.");
        }

        courier.setStatus("DELIVERED");
        CourierDetails saved = courierDetailsRepository.save(courier);

        // create delivery log
        DeliveryLog log = new DeliveryLog();
        log.setTrackingId(saved.getTrackingNumber());
        log.setCourierDetails(saved);
        log.setEventType("DELIVERED");
        log.setEventTime(Instant.now());
        log.setPerformedBy(staff.getName());
        deliveryLogService.saveDeliveryLog(log);

        return saved;
    }
}
