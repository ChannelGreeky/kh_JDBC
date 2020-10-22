package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;

public class MemberDAO {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;

	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");

			stmt = conn.createStatement();

			String query = "SELECT * FROM MEMBER";

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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}

	public Member selectOneId(String selectId) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");

			stmt = conn.createStatement();

			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '"+selectId+"'";

			rset = stmt.executeQuery(query);
			if (rset.next()) {
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
				
				return m;
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Member> selectName(String selectName) {
		ArrayList<Member> selectList = new ArrayList<Member>();
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");

			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME = '"+selectName+"'";

			rset = stmt.executeQuery(query);
			while(rset.next()) {
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
				
				selectList.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectList;
	}

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");

			stmt = conn.createStatement();
			
			String query = "INSERT INTO MEMBER VALUES("
					+ "'"+m.getMemberId()+"',"
					+ "'"+m.getMemberPwd()+"',"
					+ "'"+m.getMemberName()+"',"
					+ "'"+m.getGender()+"',"
					+ m.getAge()+","
					+ "'"+m.getEmail()+"',"
					+ "'"+m.getPhone()+"',"
					+ "'"+m.getAddress()+"',"
					+ "'"+m.getHobby()+"',"
					+ "DEFAULT)";

			//트랜잭션 흐름제어를 위해 자동커밋 진행을 해제
			conn.setAutoCommit(false);
			//excuteQuery 메소드는 출력전용 메소드(SELECT)
			//실행시 출력된 결과 (ResultSet)를 리턴
			
			//insert, update, delete는 출력 전용 메소드인 excuteQuery로 실행 X
			//excuteUpdate 메소드를 이용하여 데이터의 삽입, 수정, 삭제를 수행
			//excuteUpdate 메소드는 처리된 행의 개수를 리턴 (ex 1행 데이터 삭제시 1 반환)
			//(실패시 0 반환)
			result = stmt.executeUpdate(query);
			
			if(result > 0) {
				conn.commit(); // 성공적으로 마쳤으면 커밋
			} else {
				conn.rollback(); // 성공적으로 마쳤으면 롤백
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");

			stmt = conn.createStatement();
			
			String query = "UPDATE MEMBER SET "
							+ "MEMBER_PWD = '"+m.getMemberPwd()+"',"
							+ "MEMBER_NAME = '"+m.getMemberName()+"',"
							+ "GENDER = '"+m.getGender()+"',"
							+ "AGE = '"+m.getAge()+"',"
							+ "EMAIL = '"+m.getEmail()+"',"
							+ "PHONE = '"+m.getPhone()+"',"
							+ "ADDRESS = '"+m.getAddress()+"',"
							+ "HOBBY = '"+m.getHobby()+"'"
							+ " WHERE MEMBER_ID = '"+m.getMemberId()+"'";;

			conn.setAutoCommit(false);
			
			result = stmt.executeUpdate(query);
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean deleteMember(Member m) {
		// TODO Auto-generated method stub
		int deleteResult = 0;
		int insertResult = 0;
		boolean result = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "member", "1234");
			stmt = conn.createStatement();
			String insertQuery = "INSERT INTO DELETE_MEMBER ("
							+ "SELECT MEMBER_ID, MEMBER_NAME, GENDER, PHONE, ENROLL_DATE, SYSDATE "
							+ "WHERE MEMBER_ID = '"+m.getMemberId()+"')";			
			String deleteQuery = "DELETE FROM MEMBER WHERE MEMBER_ID = '"+ m.getMemberId()+"'";
			
			conn.setAutoCommit(false); 
			insertResult = stmt.executeUpdate(insertQuery);
			deleteResult = stmt.executeUpdate(deleteQuery);
			
			if(insertResult > 0 && deleteResult > 0) {
				conn.commit(); // 성공적으로 마쳤으면 커밋
				result = true;
			} else {
				conn.rollback(); // 성공적으로 마쳤으면 롤백
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
