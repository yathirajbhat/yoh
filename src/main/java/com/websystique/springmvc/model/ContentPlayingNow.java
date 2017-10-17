package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTENT_PLAYING")
public class ContentPlayingNow {

@Id
@GeneratedValue
@Column(name = "CONTENT_PLAYING_ID")
private long id;

@ManyToOne(optional = false)
@JoinColumn(name = "DEVICE_ID")
private Device device;

@ManyToOne(optional = false)
@JoinColumn(name = "CONTENT")
private UserDocument userDocument;

@Column(name = "CONTNENT_START_TIME")
private String startTime;

@Column(name = "CONTENT_END_TIME")
private String endTime;

@Column(name = "DELAY")
private Integer delay;

@Column(name = "DELAY_UNIT")
private String delayUnit;

@Column(name = "CAMPAIGN_PRICE")
private Double campaignPrice;

@Column(name = "IS_ACTIVE")
private Integer isActive;

@Column(name = "is_deleted")
private Integer isDeleted;

@Column(name = "group_id")
private String groupId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public UserDocument getUserDocument() {
		return userDocument;
	}

	public void setUserDocument(UserDocument userDocument) {
		this.userDocument = userDocument;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}
	
	public String getDelayUnit() {
		return delayUnit;
	}

	public void setDelayUnit(String delayUnit) {
		this.delayUnit = delayUnit;
	}
	
	public Double getCampaignPrice() {
		return campaignPrice;
	}

	public void setCampaignPrice(Double campaignPrice) {
		this.campaignPrice = campaignPrice;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "ContentPlayingNow [id=" + id + ", device=" + device + ", userDocument=" + userDocument + ", startTime="
				+ startTime + ", endTime=" + endTime + ", delay=" + delay + ", delayUnit=" + delayUnit + ", isActive="
				+ isActive + "]";
	}
	
}
