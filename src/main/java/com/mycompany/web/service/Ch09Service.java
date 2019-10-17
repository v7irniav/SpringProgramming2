package com.mycompany.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mycompany.web.dao.Ch09Dao;


@Service//사용하려면 매개값을 주면 안된다.
public class Ch09Service {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service.class);
		

	
	@Autowired
	private Ch09Dao ch09Dao;//필드 Ch09Service는 Ch09Dao를 사용한다 Ch09Dao없이는 Ch09Service를 사용할수 없다.
	
	
	public void method1() {//애가 실행되기 전에 셋터가 먼저 실행해야 한다.
		logger.debug("실행");
		ch09Dao.insert();
	}
	
}
