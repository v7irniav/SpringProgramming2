package com.mycompany.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 클래스 선언, 메소드 선언 위에다가 작성된것들을 어노테이션이라 한다
//어노테이션도 자바 코드이다. 스프링에서 제공해주는 것들이다
//http://localhost:8080/SpringProgramming2까지가 생략된거다
public class HomeController {//컨트롤러는 요청을 처리하는 역할을 한다. 요청을 처리할때는 메소드를 불러서 처리한다.
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);// HomeController이 클래스의 로고
	// 로거는 HomeController이 클래스의 로고다
	//private 외부에서 사용할일이 없다 static 어디든 쓸수 있도록 하기 위해서 final은 여기 초기값 그대로 써라 딴거 가져와라
	//Logger는 HomeController를 위한 Logger다
	
	//생성자 //테스트 언제 만들어 질까//생성자는 딱 한번만 생성된다.
	public HomeController() {
		logger.debug("실행test");
	}
	
	
	
	@RequestMapping("/")// / 앞에 컨텍스트 이름까지 생략되어 있다.
	//이 메소드를 언제 실행해야 한다 는 뜻으로 ("/")는 요청 경로이다
	public String home() {//메소드 이름은 중요치 않다.
		logger.debug("실행");
		return "home";//home 뷰 이름 /WEB-INF/views가 앞에 붙여진거고 .jsp가 뒤에 붙여 진거다
		//파일 이름은 리턴값이랑 똑같아야 한다
	}
	
	@RequestMapping("/info")
	public String info() {
		logger.debug("실행");
		return "info";
	}
//WebContent/WEB-INFviews/폴더에 jsp 파일을 불러 온다
	
}
