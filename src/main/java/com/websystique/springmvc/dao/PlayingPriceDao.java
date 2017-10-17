package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.model.PlayingPrice;

public interface PlayingPriceDao {

	public PlayingPrice getPriceForHour(int hour,DeviceCategory category);
	
}
