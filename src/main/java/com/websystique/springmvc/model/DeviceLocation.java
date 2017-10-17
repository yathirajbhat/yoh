package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVCE_LOCATION")
public class DeviceLocation {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "DEVCE_LOCATION_NAME")
	private String devceLocationName;
	@Column(name = "CITY")
	private String city;
	@Column(name = "DEVCE_LOCATION_LAT")
	private String devceLocationLat;
	@Column(name = "DEVCE_LOCATION_LON")
	private String devceLocationLon;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDevceLocationName() {
		return devceLocationName;
	}
	public void setDevceLocationName(String devceLocationName) {
		this.devceLocationName = devceLocationName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDevceLocationLat() {
		return devceLocationLat;
	}
	public void setDevceLocationLat(String devceLocationLat) {
		this.devceLocationLat = devceLocationLat;
	}
	public String getDevceLocationLon() {
		return devceLocationLon;
	}
	public void setDevceLocationLon(String devceLocationLon) {
		this.devceLocationLon = devceLocationLon;
	}
	@Override
	public String toString() {
		return "DeviceLocation [id=" + id + ", devceLocationName=" + devceLocationName + ", city=" + city
				+ ", devceLocationLat=" + devceLocationLat + ", devceLocationLon=" + devceLocationLon + "]";
	}
	
}
