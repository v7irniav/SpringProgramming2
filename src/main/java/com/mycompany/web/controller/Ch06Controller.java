package com.mycompany.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.web.dto.Ch06Board;



@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch06Controller.class);
	
		@RequestMapping("/content")
		public String content() {
			return "ch06/content";
		}
		@PostMapping("/login")
		public String login(String mid, String mpassword, HttpSession session) {
			String loginResult = "";
			if(mid.equals("admin")) {
				if(mpassword.equals("iot12345")) {
					loginResult = "success";
				}else {
					loginResult = "wrongMpassword";
				}
			} else {
				loginResult = "wrongMid";
			}
			session.setAttribute("loginResult", loginResult);
			return "redirect:/ch06/content";//요청을 다시 해달라는 뜻
		}
		@RequestMapping("/logout")
		public String logout(HttpSession session) {
			session.removeAttribute("loginResult");
			return "redirect:/ch06/content";
		}
		
		//파일 다운로드
		@RequestMapping("/fileDownload")
		public void fileDownlad(String fname, HttpServletRequest request, HttpServletResponse response) throws Exception{//리턴타입이 보이드가 되는 경우는 파일을 다운로드 할때
			//HttpServletResponse 응답
			//이 메소드에서 응답을 생성할 경우에는 굳이 jsp를 갈필요가 없다.
			logger.debug(fname);
			//응답 헤더에 추가
			ServletContext application = request.getServletContext();
			String mimeType = application.getMimeType(fname);
			response.setHeader("Content-Type", "mimeType");//둘다 써도 된다.//response.setContentType("image/png");//둘다 써도 된다.
			//text/html 본문에 html이 들어가야됨 application/json json이 들어가야함 image/png png 이미지가 들어가야 한다
			
			
			
			String userAgent = request.getHeader("User-Agent");
			String downloadName;
			if(userAgent.contains("Trident/7.0") || userAgent.contains("MSIE")) {
				//IE11 이하 버전 또는 IE10 이하 버전에서 한글 파일을 복원하기 위해
				downloadName = URLEncoder.encode(fname, "UTF-8");
				
			} else {
				//WebKit 기반 브라우저(Chrom, Safari, FireFox, Opera, Edge)에서 한글파일을 복원하기 위해
				downloadName = new String(fname.getBytes("UTF-8"), "ISO-8859-1");//http헤드에 들어가는 문자셋은 ISO-8859-1
			}
			//fname을 utf-8로 변환해서 "ISO-8859-1"로 다시 변환해라
			response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadName +"\"");//Content-Disposition헤더명  attachment첨부 
			String realPath = application.getRealPath("/resources/image/" + fname);
			File file = new File(realPath);
			response.setHeader("Content-Length", String.valueOf(file.length()));//파일의 크기 얻기
			
//			//응답 본문에 데이터 추가
			//			PrintWriter pw = response.getWriter();//문자니까 writer
//			pw.print("<!DOCTYPE html>");
//			pw.print("<html><body>Hello</body></html>");
//			pw.flush();
//			pw.close();
//			//실제로 jsp없이  응답 내용이 간다
			
			
//			PrintWriter pw = response.getWriter();
//			pw.print("{\"result\":\"ok\"}");//이것만 보낼건데 jsp 만들기 귀찮고 할대 사용한다
//				//{"result"::"ok"}
//			pw.flush();
//			pw.close();
			
			
			OutputStream os = response.getOutputStream();
			//ServletContext application = request.getServletContext();
			//String realPath = request.getServletContext().getRealPath("/resources/image/" + fname);//getRealPath 실제 경로라는 말 줄
			InputStream is = new FileInputStream(realPath);
			byte[] buffer = new byte[1024];
			while(true) {
				int readByte = is.read(buffer);
				if(readByte == -1) break;
				os.write(buffer, 0, readByte);//버퍼에 저장되어 있는것 부터 0 인덱스 부터 읽어라
				
			}
			os.flush();
			os.close();
			is.close();
			
			
			
			
			//InputStream is = new FileInputStream("D:\\IoTCourse\\workspace_web\\SpringProgramming2\\WebContent\\resources\\image\\Desert.jpg");
			//절대경로 이미지를 여기다 넣고 사용하는건 위치가 달라질수 있어서 알맞지 않다
			
			
			
			
		}
		@RequestMapping("/jsonDownload1")
		public String jsonDownload1(Model model) {
			Ch06Board board = new Ch06Board();
			board.setBno(100);
			board.setBtitle("공부하고 싶다.");
			board.setBcontent("까짓것 하면 되겠지...열공!");
			board.setWriter("감못잡아");
			board.setDate(new Date());
			board.setHitcount(1);
			
			model.addAttribute("board", board);
					
			
			return "ch06/jsonDownload1";
		}
		
		@RequestMapping("/jsonDownload2")
		public void jsonDownload2(HttpServletResponse response) throws Exception{
			Ch06Board board = new Ch06Board();
			board.setBno(300);
			board.setBtitle("나는 오타쟁이");
			board.setBcontent("오타는 나의 인생, 오타 내는 것은 당연, 근데 못찾으면 안됨!");
			board.setWriter("감못잡아");
			
			board.setDate(new Date());
			board.setHitcount(1);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("bno", board.getBno());
			jsonObject.put("btitle", board.getBtitle());
			jsonObject.put("writer", board.getWriter());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			jsonObject.put("date",sdf.format(board.getDate()));
			jsonObject.put("hitcount", board.getHitcount());
			String json = jsonObject.toString();
			
			response.setContentType("application/json; charset=UTF-8");//jsp로 안가고 맞바로 응답을 만들어 버린다
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		}
		
}






