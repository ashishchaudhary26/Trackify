package com.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourierDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pickupAddress;
    private String pickupCity;
    private String toName;
    private String fromName;
    // @Column(name = "to_mobile_no") // Specify the column name explicitly
    private String toMobile;
    // private String toMobileNo;
    private String destinationAddress;
    private String destinationCity;
    private String paymentMethod;
    private double weightKg;
    private double totalCost;
    private String trackingNumber;
    private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public String getPickupCity() {
		return pickupCity;
	}
	public void setPickupCity(String pickupCity) {
		this.pickupCity = pickupCity;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getWeightKg() {
		return weightKg;
	}
	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CourierDetails [id=" + id + ", pickupAddress=" + pickupAddress + ", pickupCity=" + pickupCity
				+ ", toName=" + toName + ", fromName=" + fromName + ", toMobile=" + toMobile + ", destinationAddress="
				+ destinationAddress + ", destinationCity=" + destinationCity + ", paymentMethod=" + paymentMethod
				+ ", weightKg=" + weightKg + ", totalCost=" + totalCost + ", trackingNumber=" + trackingNumber
				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(destinationAddress, destinationCity, fromName, id, paymentMethod, pickupAddress, pickupCity,
				status, toMobile, toName, totalCost, trackingNumber, weightKg);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourierDetails other = (CourierDetails) obj;
		return Objects.equals(destinationAddress, other.destinationAddress)
				&& Objects.equals(destinationCity, other.destinationCity) && Objects.equals(fromName, other.fromName)
				&& Objects.equals(id, other.id) && Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(pickupAddress, other.pickupAddress) && Objects.equals(pickupCity, other.pickupCity)
				&& Objects.equals(status, other.status) && Objects.equals(toMobile, other.toMobile)
				&& Objects.equals(toName, other.toName)
				&& Double.doubleToLongBits(totalCost) == Double.doubleToLongBits(other.totalCost)
				&& Objects.equals(trackingNumber, other.trackingNumber)
				&& Double.doubleToLongBits(weightKg) == Double.doubleToLongBits(other.weightKg);
	}
    
    

}
