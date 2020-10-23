package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;

public class MemberDAO {
	//Service로 따로 구현한 것
	
	public ArrayList<Member> selectAll(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rset = null;

		ArrayList<Member> list = new ArrayList<Member>();

		try {
			stmt = conn.createStatement();

			String query = "SELECT * FROM MEMBER WHERE DROP_YN = 'N";

			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Member m = new Member();

				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));

				list.add(m);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	public Member selectOneId(Connection conn, String selectId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = new Member();

		try {
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, selectId);
			
			rset = pstmt.executeQuery();
			
			if(rset != null) {
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				return m;
			}
			
			return m;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	
	public ArrayList<Member> selectName(Connection conn, String selectName) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Member> list = new ArrayList<Member>();

		try {
			String query = "SELECT * FROM MEMBER WHERE MEMEBER_NAME LIKE ?";
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, selectName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = new Member();

				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setGender(rset.getString("GENDER").charAt(0));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));

				list.add(m);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int insertMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			String query = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,DEFAULT,'N')";
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {		
			String query = "UPDATE MEMBER SET "
					+ "MEMBER_PWD = ?,"
					+ "MEMBER_NAME = ?,"
					+ "GENDER = ?,"
					+ "AGE = ?,"
					+ "EMAIL = ?,"
					+ "PHONE = ?,"
					+ "ADDRESS = ?,"
					+ "HOBBY = ?"
					+ " WHERE MEMBER_ID = ?";
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, String.valueOf(m.getGender()));
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getHobby());
			pstmt.setString(9, m.getMemberId());
		
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			String query = "UPDATE MEMBER SET "
					+ "DROP_YN = 'Y'"
					+ " WHERE MEMBER_ID = ?";
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
		
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
