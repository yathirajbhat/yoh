package com.websystique.springmvc.model;

import java.util.List;

public class CampaignResponse {

	private String startDateTime;
	private String endDateTime;
	private int delay;
	private String unit;
	private List<CampaignContents> campaignContents;
	
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<CampaignContents> getCampaignContents() {
		return campaignContents;
	}
	public void setCampaignContents(List<CampaignContents> campaignContents) {
		this.campaignContents = campaignContents;
	}
	
	
	@Override
	public String toString() {
		return "AppResponse [startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", delay=" + delay
				+ ", unit=" + unit + ", campaignContents=" + campaignContents + "]";
	}
	
}
