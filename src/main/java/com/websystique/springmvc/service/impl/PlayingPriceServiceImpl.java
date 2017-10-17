package com.websystique.springmvc.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.PlayingPriceDao;
import com.websystique.springmvc.model.DeviceCategory;
import com.websystique.springmvc.model.PlayingPrice;
import com.websystique.springmvc.service.PlayingPriceService;

@Service("playingPriceService")
@Transactional
public class PlayingPriceServiceImpl implements PlayingPriceService {

	@Autowired
	PlayingPriceDao playingPriceDao;
	@Override
	public PlayingPrice playingPrice(int hour , DeviceCategory category) {
		
		return playingPriceDao.getPriceForHour(hour,category);
	}

}
