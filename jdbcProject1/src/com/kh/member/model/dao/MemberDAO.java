package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;

public class MemberDAO {
	private ArrayList<Member> list = new ArrayList<Member>();
	
	// 회원 정보 전체 조회
	public ArrayList<Member> selectAll() {
		//DBMS와의 연동을 위한 객체 레퍼런스를 제작
		Connection conn = null; //DBMS에 연결하기 위한 객체
		Statement stmt = null;	//SQL 구문을 전송하기 위한 객체
		ResultSet rset = null;	//SQL 결과를 저장하기 위한 객체
		
		try {//Class는 정적개체로 프로그램시작시 메모리에 바로 생성되어 있다.
			//1.드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DBMS연결 (성공시 Connection 객체, 실패시 null)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","member","1234");
				//@localhost는 ip주소를 입력하는 자리. localhost는 자신의 ip주소를 가르킨다. 
			
			//3.Statement 객체 생성
			stmt = conn.createStatement();
			
			//4.SQL 구문 전송 및 결과 리턴
			String query = "SELECT * FROM MEMBER";
			rset = stmt.executeQuery(query);
			
			//5. ResultSet 처리
			while(rset.next()) { //다음 행을 가리켰을때 있다면, (디폴트는 첫번째)
				Member m = new Member(); //회원 1명의 데이터를 저장할 객체
				
				m.setMemberId(rset.getString("MEMBER_ID")); //그 행의 MEMBER_ID 필드값을 가져온다.
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
		
	}

	// 회원 아이디로 조회
	public void selectOneId() {

	}

	// 회원 이름으로 조회
	public void selectName() {

	}

	// 회원 가입
	public void insertMemeber() {

	}

	// 회원 정보 변경
	public void updateMember() {

	}

	// 회원 삭제
	public void deleteMember() {

	}

}
