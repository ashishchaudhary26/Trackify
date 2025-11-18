package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.CourierDetails;
import com.entity.DeliveryLog;
import com.service.CourierDetailsService;

@RestController
@RequestMapping("/courier")
@CrossOrigin
public class CourierDetailsController {

    @Autowired
    private CourierDetailsService courierService;

    @PostMapping("/add")
    public ResponseEntity<CourierDetails> createCourier(@RequestBody CourierDetails courier) {
        return ResponseEntity.ok(courierService.createCourier(courier));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourierDetails> updateCourier(
            @PathVariable Long id,
            @RequestBody CourierDetails courier) {

        return ResponseEntity.ok(courierService.updateCourier(id, courier));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CourierDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.getCourierById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourierDetails>> getAll() {
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
        return ResponseEntity.ok("Courier deleted successfully with ID: " + id);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<CourierDetails> getByTracking(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(courierService.getByTrackingNumber(trackingNumber));
    }

    @PutMapping("/{courierId}/assignStaff/{staffId}")
    public ResponseEntity<CourierDetails> assignStaff(
            @PathVariable Long courierId,
            @PathVariable Long staffId) {

        return ResponseEntity.ok(courierService.assignStaff(courierId, staffId));
    }

    @PutMapping("/{courierId}/assignCustomer/{customerId}")
    public ResponseEntity<CourierDetails> assignCustomer(
            @PathVariable Long courierId,
            @PathVariable Long customerId) {

        return ResponseEntity.ok(courierService.assignCustomer(courierId, customerId));
    }

    @PostMapping("/{courierId}/log")
    public ResponseEntity<DeliveryLog> addLog(
            @PathVariable Long courierId,
            @RequestBody DeliveryLog log) {

        return ResponseEntity.ok(courierService.addDeliveryLog(courierId, log));
    }

    @GetMapping("/{courierId}/logs")
    public ResponseEntity<List<DeliveryLog>> getLogs(@PathVariable Long courierId) {
        return ResponseEntity.ok(courierService.getCourierLogs(courierId));
    }
}
