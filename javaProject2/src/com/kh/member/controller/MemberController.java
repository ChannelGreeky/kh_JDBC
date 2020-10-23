package com.kh.member.controller;

import java.util.ArrayList;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberController {
	MemberDAO mDAO = new MemberDAO();

	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = mDAO.selectAll();
		return list;
	}

	public Member selectOneId(String selectId) {
		// TODO Auto-generated method stub
		Member m = mDAO.selectOneId(selectId);

		if (m == null) {
			return null;
		} else {
			return m;
		}
	}

	public ArrayList<Member> selectName(String selectName) {
		// TODO Auto-generated method stub
		selectName = "%" + selectName + "%";

		ArrayList<Member> list = mDAO.selectName(selectName);

		return list;
	}

	public boolean insertMember(Member m) {
		// TODO Auto-generated method stub
		if (m.getGender() == '남') {
			m.setGender('M');
		} else {
			m.setGender('F');
		}
		int result = mDAO.insertMember(m);

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateMember(Member m) {
		// TODO Auto-generated method stub
		
		if (m.getGender() == '남') {
			m.setGender('M');
		} else {
			m.setGender('F');
		}
		
		int result = mDAO.updateMember(m);

		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteMember(Member m) {
		// TODO Auto-generated method stub
		int result = mDAO.deleteMember(m);
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
