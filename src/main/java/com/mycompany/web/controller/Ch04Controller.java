package com.mycompany.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

	@Controller
	@RequestMapping("/ch04")
	public class Ch04Controller {
		private static final Logger logger = LoggerFactory.getLogger(Ch04Controller.class);

		@RequestMapping("/content")
		public String content(@RequestHeader("User-Agent") String userAgent, HttpServletRequest request) {
			logger.info("userAgent: " + userAgent);
			String browserKind = null;
			if(userAgent.contains("Chrome")) {
				browserKind= "Chrome";
			}else if(userAgent.contains("Trident/7.0")) {
				browserKind= "IE 11";
			}	
			
			request.setAttribute("browserKind", browserKind);
			
			return "ch04/content";
		}
		@RequestMapping("/setCookie")
		public String setCookie(HttpServletResponse response, String mname) {//응답 내용에 쿠키가 실려서 간다 응답이므로 response가 필요하다 저장할땐 response
//			try {인코딩 하는 법 
//			mname = URLEncoder.encode(mname, "UTF-8"); 
//			} catch(Exception e) {}
//			logger.debug(mname);
			Cookie cookie = new Cookie("mname", mname);//쿠키 객체 생성
			//cookie.setMaxAge(365*24*60*60);//쿠키의 최대 유지 기간
			//cookie.setMaxAge(0); 0을 주게 되면 삭제 효과가 있다.
			response.addCookie(cookie);
			return "ch04/content"; 
		}
		
		@RequestMapping("/getCookie")
		public String getCookie(HttpServletRequest request) {
			Cookie[] cookies = request.getCookies();//클라이언트에서 넘어오는 쿠키는 여러개일수도 있다
			if(cookies != null) {
				for(Cookie cookie : cookies) {//쿠키의 갯수만큼 포문을 돌린다.
					if(cookie.getName().equals("mname")) {//mname이라는 쿠키를 확인하고
//						try {//인코딩 한거 불러 올때
//							String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
						request.setAttribute("mname", cookie.getValue());//그 값을 jsp로 넘긴다
					}
				}
			}
			
			return "ch04/getCookie"; 
		}
	}

