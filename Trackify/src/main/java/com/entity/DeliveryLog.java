package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deliverylog")
public class DeliveryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingId;
    private String toName;
    private String destinationAddress;
    private String destinationCity;

    @ManyToOne
    private CourierDetails courierDetails;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }

    public String getToName() { return toName; }
    public void setToName(String toName) { this.toName = toName; }

    public String getDestinationAddress() { return destinationAddress; }
    public void setDestinationAddress(String destinationAddress) { this.destinationAddress = destinationAddress; }

    public String getDestinationCity() { return destinationCity; }
    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }

    public CourierDetails getCourierDetails() { return courierDetails; }
    public void setCourierDetails(CourierDetails courierDetails) { this.courierDetails = courierDetails; }

    @Override
    public String toString() {
        return "DeliveryLog [id=" + id + ", trackingId=" + trackingId + "]";
    }
}
