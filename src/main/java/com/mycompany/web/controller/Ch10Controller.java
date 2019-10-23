package com.mycompany.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.web.dto.Ch10Board;
import com.mycompany.web.dto.Ch10Member;
import com.mycompany.web.service.Ch10Service;
import com.mycompany.web.service.LoginResult;

@Controller
@RequestMapping("/ch10")
public class Ch10Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ch10Controller.class);

	@Autowired // @Resource(name="dataSource")
	private DataSource dataSource;

	@Autowired
	private Ch10Service service;

	@RequestMapping("/content")
	public String content() {
		return "ch10/content";
	}

	@RequestMapping("/connTest")
	public void connTest(HttpServletResponse response) {
		boolean result = false;
		try {
			// 커넥션 풀에서 연결된 커넥션 대여
			Connection conn = dataSource.getConnection(); // 연결되었다면 예외가 발생하지 않는다
			if (conn != null) {
				result = true;
			}
			// 커넥션 풀로 커넥션을 반납
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", result);
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@RequestMapping("/getMember")
	public String getMember(String mid, Model model) { // mid(primary key)로 검색했기 때문에 하나의 행만 가져온다 .selectList 여러행 (ex 게시물
														// 목록)
		Ch10Member member = sqlSessionTemplate.selectOne("member.selectMemberByMid", mid); // member.xml 파일의
																							// namespace="member"의미
																							// member.select문 id
		model.addAttribute("member", member);
		return "ch10/getMember";
	}

	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam(defaultValue = "1") int pageNo, HttpSession session) {
		session.setAttribute("pageNo", pageNo);
		
		int rowsPerPage = 10;// 페이지당 행수
		int pagesPerGroup = 5;// 이전, 다음을 클릭했을때 나오는 그룹당 페이지 수
		int totalRowNum = service.getTotalRowNo();// 전체 게시물 수 //디비한테 물어봐야함
		int totalPageNum = totalRowNum / rowsPerPage;// 전체 페이지 수
		if (totalRowNum % rowsPerPage != 0)
			totalPageNum++;// 뒤에 짜투리도 페이지수로 인정
		int totalGroupNum = totalPageNum / pagesPerGroup;// 전체 그룹 수
		if (totalPageNum % pagesPerGroup != 0)
			totalGroupNum++;
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;// 현재페이지의 그룹번호
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;// 현재 그룹의 시작 페이지 번호
		int endPageNo = startPageNo + pagesPerGroup - 1;// 현재 그룹의 마지막 페이지 번호
		if (groupNo == totalGroupNum)
			endPageNo = totalPageNum;
		int startRowNo = (pageNo - 1) * rowsPerPage + 1;// 공식//현재시작 페이지의 행 번호
		int endRowNo = pageNo * rowsPerPage;// 현재공식//해당 페이지의 끝 행번호
		if (groupNo == totalGroupNum)
			endRowNo = totalRowNum;

		// 현제 페이지의 게시물 가져오기
		List<Ch10Board> boardList = service.getBoardList(startRowNo, endRowNo);

		// jsp로 페이지 정보 넘기기
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("totalGroupNum", totalGroupNum);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("boardList", boardList);

		return "ch10/boardList";
	}

//	@RequestMapping("/boardList")
//	public String boardList(Model model, int pageNo) {
//		//페이지당 행수
//		int rowsPerPage = 10;
//		//이전, 다음을 클릭했을 때 나오는 페이지 수
//		int pagesPerGroup = 5;
//		//전체 게시물 수
//		int totalRowNum = service.getTotalRowNo();
//		//전체 페이지 수
//		int totalPageNum = totalRowNum / rowsPerPage;
//		if(totalRowNum % rowsPerPage != 0) totalPageNum++;
//		//전체 그룹 수
//		int totalGroupNum = totalPageNum / pagesPerGroup;
//		if(totalPageNum % pagesPerGroup != 0) totalGroupNum++;
//		
//		//현재 페이지의 그룹번호
//		int groupNo = (pageNo-1)/pagesPerGroup + 1;
//		//현재 그룹의 시작 페이지 번호
//		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
//		//현재 그룹의 마지막 페이지 번호
//		int endPageNo = startPageNo + pagesPerGroup - 1;
//		if(groupNo == totalGroupNum) endPageNo = totalPageNum;
//		
//		//현재 페이지의 시작 행번호
//		int startRowNo = (pageNo-1)*rowsPerPage + 1;
//		//현재 페이지의 끝 행번호
//		int endRowNo = pageNo*rowsPerPage;
//		if(groupNo == totalGroupNum) endRowNo = totalRowNum;
//		
//		//현재 페이지의 게시물 가져오기
//		List<Ch10Board> boardList = service.getBoardList(startRowNo, endRowNo);
//		
//		//JSP로 페이지 정보 넘기기
//		model.addAttribute("pagesPerGroup", pagesPerGroup);
//		model.addAttribute("totalPageNum", totalPageNum);
//		model.addAttribute("totalGroupNum", totalGroupNum);
//		model.addAttribute("groupNo", groupNo);
//		model.addAttribute("startPageNo", startPageNo);
//		model.addAttribute("endPageNo", endPageNo);
//		model.addAttribute("pageNo", pageNo);
//		model.addAttribute("boardList", boardList);
//		
//		return "ch10/boardList";
//	}

	@RequestMapping("/writeBoardForm")
	public String writeBoardForm(HttpSession session) {// session에 맴버 아이디가 저장 되도록
		String mid = (String) session.getAttribute("mid");// 섹션에 저장되어 있는 mid를 가져와라 없으면 null이 된다.
		if (mid == null) {
			return "redirect:/ch10/loginForm"; // 값이 널이면 로그인 폼으로 이동하라
		}
		return "ch10/writeBoardForm";
	}

	@RequestMapping("/writeBoard")
	public String writeBoard(Ch10Board board, HttpSession session) {
		board.setBwriter((String)session.getAttribute("mid"));
		//logger.debug("dao 실행 전:" + board.getBno());
		service.writeBoard(board);
		//logger.debug("dao 실행 후:" + board.getBno());
		// boardList(); 이렇게 하면 안된다 클라이언트가 요청을 했을때만 실행하도록 정의를 했기 때문이다.
		return "redirect:/ch10/boardList";
	}

	// 로그인
	@RequestMapping("/loginForm")
	public String loginForm(String error, Model model) {
		if (error != null) {
			if (error.equals("fail_mid")) {
				model.addAttribute("midError", "*아이디가 존재하지 앖습니다.");
			} else if (error.equals("fail_mpassword")) {
				model.addAttribute("mpasswordError", "*패스워드가 틀렀습니다.");
			}
		}
		return "ch10/loginForm";

	}

	@PostMapping("/login") // 로그인 액션을 처리 하는 메소드
	public String login(String mid, String mpassword, HttpSession session) {// 메게변수를 받고 서비스로 넘기고 기능은 처리는 전부 서비스로 넘기는게 좋다
		LoginResult result = service.login(mid, mpassword);// 열거 타입
		if (result == LoginResult.FAIL_MID) {
			// model.addAttribute("midError", "*아이디가 존재하지 않습니다.");//무슨 이유로 돌아갔는지 전달 모델은
			// 리다이렉트가 안된다.
			// session.setAttribute("midError", "*아이디가 존재하지 않습니다.");//이렇게 섹션은 도니다.
			return "redirect:/ch10/loginForm?error=fail_mid";// get방식
		} else if (result == LoginResult.FAIL_MPASSWORD) {
			return "redirect:/ch10/loginForm?error=fail_mpassword";//
		}
		session.setAttribute("mid", mid);
		return "redirect:/ch10/boardList";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("mid");
		return "redirect:/ch10/boardList";
	}
	
	@GetMapping("/join")//회원가입
	public String joinForm() {
		return "ch10/joinForm";
		
	}
	
	@PostMapping("/join")//회원가입
	public String join(Ch10Member member) {
		service.join(member);
		return "redirect:/ch10/loginForm";
	}
	
	@RequestMapping("/checkMid")//중복 체크
	public void checkMid(String mid, HttpServletResponse response) throws Exception {
		boolean result = service.checkMid(mid);
		response.setContentType("application/json; charset=UTF-8");//제이슨 만들기(응답 직접 만들기)
		PrintWriter pw = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		pw.print(jsonObject.toString());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/boardDetail")//게시물 상세보기
	public String boardDetail(int bno, Model model) {
		service.increaseHitcount(bno);
		Ch10Board board = service.getBoard(bno);//게시물의 정보
		model.addAttribute("board", board);
		return "ch10/boardDetail";
	}
	
	@GetMapping("/updateBoard")//디비에 있는 내용을 가져와서
	public String updateBoardForm(int bno, Model model) {
		Ch10Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch10/updateBoardForm";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Ch10Board board, HttpSession session) {
		service.updateBoard(board);
		int pageNo = (Integer) session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo=" + pageNo;
	}
	
	@RequestMapping("/deleteBoard")
	public String deleteBoard(int bno, HttpSession session) {
		service.deleteBoard(bno);
		int pageNo = (Integer) session.getAttribute("pageNo");
		return "redirect:/ch10/boardList?pageNo=" + pageNo;
	}
	
	
	
	
	
	
	
	
	
}