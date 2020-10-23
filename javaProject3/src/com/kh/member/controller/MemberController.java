package com.kh.member.controller;

import java.util.ArrayList;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

public class MemberController {
	private MemberService mService = new MemberService();

	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list= mService.selectAll();
		
		return list;
	}

	public Member selectOneId(String selectId) {
		// TODO Auto-generated method stub
		Member m = mService.selectOneId(selectId);
		
		return m;
	}

	public ArrayList<Member> selectName(String selectName) {
		// TODO Auto-generated method stub
		selectName = "%" + selectName + "%";
		ArrayList<Member> list = mService.selectName(selectName);
		
		return list;
	}

	public boolean insertMember(Member m) {
		// TODO Auto-generated method stub
		if (m.getGender() == '남') {
			m.setGender('M');
		} else {
			m.setGender('F');
		}
		int result = mService.insertMember(m);
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
		
		result = mService.updateMember(m);
		
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteMember(Member m) {
		// TODO Auto-generated method stub
		int result = mService.deleteMember(m);
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
	}

}
