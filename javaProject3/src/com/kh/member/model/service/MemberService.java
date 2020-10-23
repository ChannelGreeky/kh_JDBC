package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {
	//Service의 역할은 DBMS와 직접적인 연결을 하기 위한 역할을 가진 Class
	// + 트랜잭션을 제어하기 위한 역할
	MemberDAO mDAO = new MemberDAO();
	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		
		ArrayList<Member> list = null;
		
		list = mDAO.selectAll(conn);
		
		new JDBCTemplate().Close(conn);
		
		
		return list;
	}
	public Member selectOneId(String selectId) {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		
		Member m = new Member();
		
		m = mDAO.selectOneId(conn, selectId);
		
		new JDBCTemplate().Close(conn);
		
		return m;
	} 

	public ArrayList<Member> selectName(String selectName) {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		ArrayList<Member> list = null;
		list = mDAO.selectName(conn, selectName);
		new JDBCTemplate().Close(conn);
		

		return list;
	}
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		int result=0;
		result = mDAO.insertMember(conn, m);
		
		new JDBCTemplate().Close(conn);

		return result;
	}
	
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		int result=0;
		result = mDAO.updateMember(conn, m);
		
		new JDBCTemplate().Close(conn);

		return result;
	}
	
	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = new JDBCTemplate().getConnection();
		int result=0;
		result = mDAO.deleteMember(conn, m);
		
		new JDBCTemplate().Close(conn);

		return result;
	}

}
