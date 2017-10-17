package com.websystique.springmvc.model;

import java.util.List;
import java.util.Map;

public class PriceResponse extends Response {

	private Double price;
	private FullSlots datesNotAvailable;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public FullSlots getDatesNotAvailable() {
		return datesNotAvailable;
	}
	public void setDatesNotAvailable(FullSlots datesNotAvailable) {
		this.datesNotAvailable = datesNotAvailable;
	}
	
}
