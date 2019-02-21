package com.ssz.wechat.wechatdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssz.wechat.wechatdemo.dao.CityDao;
import com.ssz.wechat.wechatdemo.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	
	public List<CityDao> getAll(){
		return cityDao.getAll();
	}
}
