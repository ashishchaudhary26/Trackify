package com.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.CourierDetails;
import com.entity.Customer;
import com.repository.CourierDetailsRepository;
import com.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CourierDetailsRepository courierDetailsRepository;

    public static class CustomerNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public CustomerNotFoundException(Long id) {
            super("Customer not found with id: " + id);
        }
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long uuid) {
        return customerRepository.findById(uuid)
                .orElseThrow(() -> new CustomerNotFoundException(uuid));
    }

    public Customer updateCustomer(Long uuid, Customer updatedCustomer) {
        Customer existing = customerRepository.findById(uuid)
                .orElseThrow(() -> new CustomerNotFoundException(uuid));

        existing.setUsername(updatedCustomer.getUsername());
        existing.setPassword(updatedCustomer.getPassword());

        return customerRepository.save(existing);
    }

    public void deleteCustomer(Long uuid) {
        if (!customerRepository.existsById(uuid)) {
            throw new CustomerNotFoundException(uuid);
        }
        customerRepository.deleteById(uuid);
    }

    public List<CourierDetails> getCustomerShipments(Long customerId) {
        return courierDetailsRepository.findByCustomerUuid(customerId);
    }

    @Transactional
    public CourierDetails createCourierForCustomer(Long customerId, CourierDetails courier) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        courier.setCustomer(customer);

        if (courier.getTrackingNumber() == null || courier.getTrackingNumber().isBlank()) {
            courier.setTrackingNumber(generateTrackingNumber());
        }

        if (courier.getStatus() == null || courier.getStatus().isBlank()) {
            courier.setStatus("CREATED");
        }

        return courierDetailsRepository.save(courier);
    }

    private String generateTrackingNumber() {
        return "TRK-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

}
