package com.entity;

import jakarta.persistence.*;
import java.time.Instant;

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

    
    private String eventType;     
    private Instant eventTime;
    private String performedBy;   

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

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Instant getEventTime() { return eventTime; }
    public void setEventTime(Instant eventTime) { this.eventTime = eventTime; }

    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }

    @Override
    public String toString() {
        return "DeliveryLog [id=" + id + ", trackingId=" + trackingId + ", eventType=" + eventType + ", eventTime=" + eventTime + "]";
    }
}
