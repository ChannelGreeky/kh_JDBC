package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;


public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mCon = new MemberController();
	
	public void start() {
		//사용자에세 보여지는 화면
		while(true) {
			System.out.println("===== 회원 관리 프로그램 =====");
			System.out.println("1.회원 정보 전체 조회");
			System.out.println("2.회원 아이디로 조회");
			System.out.println("3.회원 이름으로 조회");
			System.out.println("4.회원 가입");
			System.out.println("5.회원 정보 수정");
			System.out.println("6.회원 탈퇴");
			System.out.println("0.프로그램 종료");
			System.out.print("선택 : ");
			int select = sc.nextInt();
		
			if (select == 0) {
				System.out.println("정말로 종료하시겠습니까?(y/n)");
				if (sc.next().toUpperCase().charAt(0) == 'Y') {
					System.out.println("프로그램을 종료합니다...");
					break; // while문을 빠져나간다.(종료코드)
				}
			}
		
			switch (select) {
			case 1: selectAll(); break;
			case 2: selectOneId(); break;
			case 3: selectName(); break;
			case 4: insertMemeber(); break;
			case 5: updateMember(); break;
			case 6: deleteMember(); break;
			}
		}
		
	}
	//회원 정보 전체 조회
	public void selectAll() {
		ArrayList<Member> list = mCon.selectAll(); //Controller의 selectAll 호출
		if(list.isEmpty()) {//list안에 데이터가 없다면!
			System.out.println("<<< 회원들의 정보가 현재 없습니다. >>>");
		}else {
			for(Member m : list) {
				System.out.println(m);
			}
		}
	}
	
	//회원 아이디로 조회
	public void selectOneId(){
	
	}
	
	//회원 이름으로 조회
	public void selectName() {
		
	}
	
	//회원 가입
	public void insertMemeber() {
		
	}
	
	//회원 정보 변경
	public void updateMember() {
		
	}
	
	//회원 삭제
	public void deleteMember() {
		
	}
	
}
