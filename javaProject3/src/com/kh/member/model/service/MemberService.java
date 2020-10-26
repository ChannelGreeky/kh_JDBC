package com.kh.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {
	//Service의 역할은 DBMS와 직접적인 연결을 하기 위한 역할을 가진 Class
	// + 트랜잭션을 제어하기 위한 역할
	private MemberDAO mDAO = new MemberDAO();

	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = null;
		
		list = mDAO.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		
		return list;
	}
	public Member selectOneId(String selectId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = mDAO.selectOneId(conn, selectId);
		
		JDBCTemplate.close(conn);
		
		return m;
	} 

	public ArrayList<Member> selectName(String selectName) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = mDAO.selectName(conn, selectName);

		JDBCTemplate.close(conn);

		return list;
	}
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.insertMember(conn, m);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}
	
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result= mDAO.updateMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);

		return result;
	}
	
	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.deleteMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);

		return result;
	}

}
