package com.controller;

import com.entity.CourierDetails;
import com.entity.Customer;
import com.service.CustomerService;
import com.service.CustomerService.CustomerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer created = customerService.saveCustomer(customer);
        return ResponseEntity.created(URI.create("/api/customers/" + created.getUuid())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer body) {
        try {
            Customer updated = customerService.updateCustomer(id, body);
            return ResponseEntity.ok(updated);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/couriers")
    public ResponseEntity<?> getCustomerCouriers(@PathVariable("id") Long id) {
        try {
            // ensure customer exists
            customerService.getCustomerById(id);
            return ResponseEntity.ok(customerService.getCustomerShipments(id));
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @PostMapping("/{id}/couriers")
    public ResponseEntity<?> createCourierForCustomer(@PathVariable("id") Long id, @RequestBody CourierDetails courier) {
        try {
            CourierDetails created = customerService.createCourierForCustomer(id, courier);
            return ResponseEntity.created(URI.create("/api/customers/" + id + "/couriers/" + created.getId())).body(created);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
