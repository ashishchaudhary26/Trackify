package com.controller;

import com.entity.CourierDetails;
import com.entity.Staff;
import com.service.StaffService;
import com.service.StaffService.StaffNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff created = staffService.saveStaff(staff);
        return ResponseEntity.created(URI.create("/api/staff/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(staffService.getStaffById(id));
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable("id") Long id, @RequestBody Staff body) {
        try {
            Staff updated = staffService.updateStaff(id, body);
            return ResponseEntity.ok(updated);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.noContent().build();
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}/my-parcels")
    public ResponseEntity<List<CourierDetails>> getAssignedParcels(@PathVariable("id") Long id) {
        return ResponseEntity.ok(staffService.getAssignedParcels(id));
    }

    @GetMapping("/{id}/available")
    public ResponseEntity<?> getAvailableParcels(
            @PathVariable("id") Long id,
            @RequestParam(value = "city", required = false) String city) {

        try {
            staffService.getStaffById(id); // ensure staff exists
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }

        if (city == null || city.isBlank()) {
            return ResponseEntity.ok(staffService.findAvailableParcels());
        } else {
            return ResponseEntity.ok(staffService.findAvailableParcelsByCity(city));
        }
    }

    @PostMapping("/{staffId}/pick/{courierId}")
    public ResponseEntity<?> pickCourier(@PathVariable("staffId") Long staffId, @PathVariable("courierId") Long courierId) {
        try {
            CourierDetails updated = staffService.pickCourier(staffId, courierId);
            return ResponseEntity.ok(updated);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PostMapping("/{staffId}/deliver/{courierId}")
    public ResponseEntity<?> deliverCourier(@PathVariable("staffId") Long staffId, @PathVariable("courierId") Long courierId) {
        try {
            CourierDetails updated = staffService.deliverCourier(staffId, courierId);
            return ResponseEntity.ok(updated);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
