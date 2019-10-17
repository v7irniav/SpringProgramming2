package com.mycompany.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.web.service.Ch09MemberService;
import com.mycompany.web.service.Ch09Service;
import com.mycompany.web.service.Ch09Service2;
import com.mycompany.web.service.Ch09Service3;
import com.mycompany.web.service.Ch09Service4;



@Controller//애가 컴포넌트 역할을 한다
@Component//이거 사용해도 상관없다
@Service///이거 사용해도 상관없다

@RequestMapping("/ch09")
public class Ch09Controller {//@Autowired를 사용할려면 해당 클래스가 관리객체가 되야 한다
	//Controller는 요청을 처리하는 객체
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);

	@Autowired
	private Ch09Service ch09Service;// 스프링이 관리하는 서비스 객체가 주입되어야 한다
	@Autowired
	private Ch09Service2 ch09Service2;
	@Autowired
	private Ch09Service3 ch09Service3;
	@Autowired
	private Ch09Service4 ch09Service4;
	
//	@Autowired
//	private Ch09MemberService1 ch09MemberService1;
//	@Autowired
//	private Ch09MemberService2 ch09MemberService2;
	
	
	@Resource(name="ch09MemberService")
	private Ch09MemberService ch09MemberService;//인터페이스 타입
	
	
	

	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}

	@RequestMapping("/method1") // 메소드 1에 대한 요청 매핑
	public String method1() {
		logger.debug("실행");
		ch09Service.method1();// 이걸 사용하고 싶다
		return "redirect:/ch09/content";
	}

	@RequestMapping("/method2")
	public String method2() {
		logger.debug("실행");
		ch09Service2.method2();
		return "redirect:/ch09/content";
	}

	@RequestMapping("/method3")
	public String method3() {
		logger.debug("실행");
		ch09Service3.method3();
		return "redirect:/ch09/content";
	}
	
	@RequestMapping("/method4")
	public String method4() {
		logger.debug("실행");
		ch09Service4.method4();
		return "redirect:/ch09/content";
	}

}
