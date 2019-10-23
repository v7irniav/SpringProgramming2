package com.mycompany.web.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.web.dto.Ch10Member;

@Component
public class Ch10MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public Ch10Member selectMember(String mid) {
		Ch10Member member = sqlSessionTemplate.selectOne("member.selectMemberByMid", mid);// member는 매퍼 member.xml에 있다.
		return member;
	}

	public int insert(Ch10Member member) {// 실제로 방연된 행수로 리턴해라
		int rows = sqlSessionTemplate.insert("member.insert", member);
		return rows;

	}
}
