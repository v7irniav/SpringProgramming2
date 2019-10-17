package com.mycompany.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.web.service.Ch09Service3;

@Component("ch09Dao3")//스프링은 @Component붙어있는 모든 객체는 관리 객체로 생성한다//("ch09Dao3")는 아이디이다 굳이 넣지 않아도 된다.
public class Ch09Dao3 {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Service3.class);

	public Ch09Dao3() {
		logger.debug("실행");
		
	}
	
	public void insert() {
		logger.debug("실행");
	}


}


