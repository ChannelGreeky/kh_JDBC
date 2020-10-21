package com.kh.member.controller;

import java.util.ArrayList;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDAO = new MemberDAO();
	
	// 회원 정보 전체 조회
	public ArrayList<Member> selectAll() {
		ArrayList<Member> list = mDAO.selectAll(); //Controller에서 DAO를 호출
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
