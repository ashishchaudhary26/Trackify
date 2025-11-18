package com.controller;

import com.entity.DeliveryLog;
import com.service.DeliveryLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class DeliveryLogController {

    @Autowired
    private DeliveryLogService deliveryLogService;
    @PostMapping
    public ResponseEntity<DeliveryLog> createLog(@RequestBody DeliveryLog log) {
        DeliveryLog created = deliveryLogService.saveDeliveryLog(log);
        return ResponseEntity.created(URI.create("/api/logs/" + created.getId())).body(created);
    }

    @GetMapping("/byCourier/{courierId}")
    public ResponseEntity<List<DeliveryLog>> getByCourier(@PathVariable("courierId") Long courierId) {
        return ResponseEntity.ok(deliveryLogService.getLogsByCourierId(courierId));
    }
    @GetMapping("/byTracking/{trackingId}")
    public ResponseEntity<List<DeliveryLog>> getByTracking(@PathVariable("trackingId") String trackingId) {
        return ResponseEntity.ok(deliveryLogService.getLogsByTrackingId(trackingId));
    }
}
