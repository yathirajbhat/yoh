package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYING_PRICE")
public class PlayingPrice {

	@Id
	@GeneratedValue
	@Column(name = "PLAYING_ID")
	private int id;
	
	@Column(name = "PLAYING_START_HOURS")
	private float playingStartHours;
	
	@Column(name = "PLAYING_END_HOURS")
	private float playingEndHours;
	
	@Column(name = "PRICES")
	private float prices;
	
	@Column(name = "SPEACIAL_PRICES")
	private float speacialPrices;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "DEVICE_CATEGORY_ID")
	private DeviceCategory deviceCategory;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPlayingStartHours() {
		return playingStartHours;
	}
	public void setPlayingStartHours(float playingStartHours) {
		this.playingStartHours = playingStartHours;
	}
	public float getPlayingEndHours() {
		return playingEndHours;
	}
	public void setPlayingEndHours(float playingEndHours) {
		this.playingEndHours = playingEndHours;
	}
	public float getPrices() {
		return prices;
	}
	public void setPrices(float prices) {
		this.prices = prices;
	}
	public float getSpeacialPrices() {
		return speacialPrices;
	}
	public void setSpeacialPrices(float speacialPrices) {
		this.speacialPrices = speacialPrices;
	}
	public DeviceCategory getDeviceCategory() {
		return deviceCategory;
	}
	public void setDeviceCategory(DeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}
	@Override
	public String toString() {
		return "playingPrice [id=" + id + ", playingStartHours=" + playingStartHours + ", playingEndHours="
				+ playingEndHours + ", prices=" + prices + ", speacialPrices=" + speacialPrices + ", deviceCategory="
				+ deviceCategory + "]";
	}
	
}
