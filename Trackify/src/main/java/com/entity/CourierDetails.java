package com.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class CourierDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pickupAddress;
    private String pickupCity;
    private String toName;
    private String fromName;
    private String toMobile;
    private String destinationAddress;
    private String destinationCity;
    private String paymentMethod;
    private double weightKg;
    private double totalCost;
    private String trackingNumber;
    private String status;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Staff staff;

    @OneToMany(mappedBy = "courierDetails", cascade = CascadeType.ALL)
    private List<DeliveryLog> logs;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPickupAddress() { return pickupAddress; }
    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }

    public String getPickupCity() { return pickupCity; }
    public void setPickupCity(String pickupCity) { this.pickupCity = pickupCity; }

    public String getToName() { return toName; }
    public void setToName(String toName) { this.toName = toName; }

    public String getFromName() { return fromName; }
    public void setFromName(String fromName) { this.fromName = fromName; }

    public String getToMobile() { return toMobile; }
    public void setToMobile(String toMobile) { this.toMobile = toMobile; }

    public String getDestinationAddress() { return destinationAddress; }
    public void setDestinationAddress(String destinationAddress) { this.destinationAddress = destinationAddress; }

    public String getDestinationCity() { return destinationCity; }
    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Staff getStaff() { return staff; }
    public void setStaff(Staff staff) { this.staff = staff; }

    public List<DeliveryLog> getLogs() { return logs; }
    public void setLogs(List<DeliveryLog> logs) { this.logs = logs; }

    @Override
    public String toString() {
        return "CourierDetails [id=" + id + ", trackingNumber=" + trackingNumber + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trackingNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourierDetails other = (CourierDetails) obj;
        return Objects.equals(id, other.id);
    }
}
