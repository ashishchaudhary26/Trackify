package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CourierDetails;
import com.entity.Customer;
import com.entity.DeliveryLog;
import com.entity.Staff;
import com.repository.CourierDetailsRepository;
import com.repository.CustomerRepository;
import com.repository.StaffRepository;
import com.repository.DeliveryLogRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourierDetailsService {

    @Autowired
    private CourierDetailsRepository courierRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    private DeliveryLogRepository logRepo;


    public CourierDetails createCourier(CourierDetails courier) {
        return courierRepo.save(courier);
    }

    public CourierDetails updateCourier(Long id, CourierDetails courier) {
        CourierDetails existing = courierRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found with ID: " + id));

        courier.setId(id);
        return courierRepo.save(courier);
    }

    public CourierDetails getCourierById(Long id) {
        return courierRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Courier not found with ID: " + id));
    }

    public List<CourierDetails> getAllCouriers() {
        return courierRepo.findAll();
    }

    public void deleteCourier(Long id) {
        courierRepo.deleteById(id);
    }

    public CourierDetails getByTrackingNumber(String trackingNumber) {
        return courierRepo.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Courier not found with tracking number: " + trackingNumber));
    }



    public CourierDetails assignStaff(Long courierId, Long staffId) {
        CourierDetails courier = getCourierById(courierId);
        Staff staff = staffRepo.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + staffId));

        courier.setStaff(staff);
        return courierRepo.save(courier);
    }


    public CourierDetails assignCustomer(Long courierId, Long customerId) {
        CourierDetails courier = getCourierById(courierId);
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));

        courier.setCustomer(customer);
        return courierRepo.save(courier);
    }


    public DeliveryLog addDeliveryLog(Long courierId, DeliveryLog log) {
        CourierDetails courier = getCourierById(courierId);

        log.setCourierDetails(courier); // Set mapping
        DeliveryLog savedLog = logRepo.save(log);

        courier.getLogs().add(savedLog);
        courierRepo.save(courier);

        return savedLog;
    }


    public List<DeliveryLog> getCourierLogs(Long courierId) {
        CourierDetails courier = getCourierById(courierId);
        return courier.getLogs();
    }

}
