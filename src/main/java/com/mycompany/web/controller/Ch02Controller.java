package com.mycompany.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch02")//공통경로 작성
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@RequestMapping("/content")//"/ch02/content"를 요청했을대 content()메소드를 실행하겠다
	public String content() {
		logger.debug("실행");
		return "ch02/content";
		
	}
	
	
	@GetMapping("/getMethod")//겟방식만 응답한다
	public String getMethod() {
		logger.debug("실행");
		return "/ch02/content";
	}
	
	@PostMapping("/postMethod")//포스트 방식만 응답한다
	public String postMethod() {
		logger.debug("실행");
		return "/ch02/content";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "ch02/joinForm";
	}
	
	@PostMapping("/join")//폼을 통해 넘어 오는것은 포스트로 처리한다
	public String join() {
		logger.debug("실행");
		return "ch02/content";
		
	}
	
}
