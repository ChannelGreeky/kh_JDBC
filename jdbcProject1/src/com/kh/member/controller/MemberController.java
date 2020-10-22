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
		return m;
	}

	public ArrayList<Member> selectName(String selectName) {
		// TODO Auto-generated method stub
		// ArrayList<Member> list = mDAO.selectName(selectName);

		return mDAO.selectName(selectName);
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
		int result = 0;
		
		if (m.getGender() == '남') {
			m.setGender('M');
		} else {
			m.setGender('F');
		}
		
		result = mDAO.updateMember(m);
		
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteMember(Member m) {
		// TODO Auto-generated method stub
		return mDAO.deleteMember(m);
	}
}
