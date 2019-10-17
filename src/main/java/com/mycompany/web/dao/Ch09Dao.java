package com.mycompany.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Ch09Dao {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Dao.class);


public Ch09Dao() {
	logger.debug("실행");//객체가 만들어 졌냐는 생성자를 생성해서 확인한다 이것이 실행이 되면 객체가 만들어 진것
}

public void insert() {
	logger.debug("실행");
}
}