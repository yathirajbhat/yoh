package com.websystique.springmvc.service;

import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.model.PlayingPrice;

public interface PlayingPriceService {

	public PlayingPrice playingPrice(int hour, DeviceCategory category);
}
