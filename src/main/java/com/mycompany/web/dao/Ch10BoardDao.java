package com.mycompany.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.dto.Ch10Board;

@Component
public class Ch10BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Ch10Board> selectList(int startRowNo, int endRowNo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRowNo", startRowNo);
		map.put("endRowNo", endRowNo);
		List<Ch10Board> boardList = sqlSessionTemplate.selectList("board.selectList", map);//board(아이디)에 해당하는 selectList를 실행시킨다
		return boardList;
	}
	
	public int selectTotalRowNo() {
		int totalRowNum = sqlSessionTemplate.selectOne("board.selectTotalRowNum");
		return totalRowNum;
	}
	
	public int insert(Ch10Board board) {
		int rows = sqlSessionTemplate.insert("board.insert", board);
		return rows;
	}

	public Ch10Board selectBoard(int bno) {//게시물 상세보기
		Ch10Board board = sqlSessionTemplate.selectOne("board.selectBoard", bno);
		return board;
	}

	public int updateHitcount(int bno) {
		int rows = sqlSessionTemplate.update("board.updateHitcount", bno);
		return rows;
	}

	public int updateBoard(Ch10Board board) {
			int rows = sqlSessionTemplate.update("board.updateBoard", board);
			return rows;
	}

	public int deleteBoard(int bno) {
		int rows = sqlSessionTemplate.delete("board.deleteBoard", bno);
		return rows;
		
	}
	
}
