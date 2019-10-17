package com.mycompany.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component//관리 객체로 만들기
public class Ch09Dao4 {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Dao4.class);
	
	public void insert() {
		logger.debug("실행");
	}
}
