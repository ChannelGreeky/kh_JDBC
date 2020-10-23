package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class MemberView {
	Scanner sc = new Scanner(System.in);
	MemberController mCon = new MemberController();
	
	public void start() {
		int select = 0;
		while(true) {
			System.out.println("========회원관리 프로그램 ver2.0========");
			System.out.println("1. 회원 정보 전체 조회");
			System.out.println("2. 회원 ID로 조회");
			System.out.println("3. 회원 이름으로 전체 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 : ");
			select = sc.nextInt();
			
			switch(select) {
			case 1:
				selectAll();
				break;
			case 2:
				selectOneId();
				break;
			case 3:
				selectName();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default : System.out.println("잘못된 입력");
				
			}
		}
	}
	private void selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = mCon.selectAll();
		if(list.isEmpty()) {
			System.out.println("회원이 존재하지 않습니다.");
		} else {
			for(Member m : list) {
				System.out.println(m);
			}	
		}
	}
	private void selectOneId() {
		// TODO Auto-generated method stub
		System.out.println("검색할 회원의 ID를 입력하세요");
		System.out.print("검색 ID : ");
		String selectId = sc.next();
		
		Member m = mCon.selectOneId(selectId);
		
		if(m == null) {
			System.out.println("검색한 회원 ID가 존재하지 않습니다.");
		} else {
			System.out.println(m);
		}
	}
	private void selectName() {
		// TODO Auto-generated method stub
		System.out.println("검색할 회원의 이름를 입력하세요");
		System.out.print("검색 이름 : ");
		String selectName = sc.next();
		
		ArrayList<Member> list = mCon.selectName(selectName);
		
		if(list.isEmpty()) {
			System.out.println(selectName+" 이름의 회원이 존재하지 않습니다.");
		} else {
			for(Member m : list) {
				System.out.println(m);
			}
		}
	}
	private void insertMember() {
		// TODO Auto-generated method stub
		System.out.println("=회원 가입 폼=");
		Member m = new Member();
		while(true) {
			System.out.print("1. 회원 ID(필수/중복 불가) : ");
			m.setMemberId(sc.next());
			Member checkM = mCon.selectOneId(m.getMemberId());
			
			if(checkM == null) {
				break;
			} else {
				System.out.println("중복된 아이디입니다.");
			}
		}
		System.out.print("2. 회원 비밀번호(필수) : ");
		m.setMemberPwd(sc.next());
		System.out.print("3. 회원 이름(필수) : ");
		m.setMemberName(sc.next());
		System.out.print("4. 회원 성별(남/여 中 택1) : ");
		m.setGender(sc.next().charAt(0));
		System.out.print("5. 회원 나이(필수) : ");
		m.setAge(sc.nextInt());
		System.out.print("6. 회원 이메일 : ");
		m.setEmail(sc.next());
		System.out.print("7. 회원 핸드폰번호(- 제외하고 입력) : ");
		m.setPhone(sc.next());
		sc.nextLine();
		System.out.print("8. 회원 주소 : ");
		m.setAddress(sc.nextLine());
		System.out.print("9. 회원 취미(여러 개일 경우 , 로 구분) : ");
		m.setHobby(sc.nextLine());
		
		boolean result = mCon.insertMember(m);
		
		if(result) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패!! 지속적으로 실패시 관리자에게 문의하세요");
		}
		
	}
	private void updateMember() {
		// TODO Auto-generated method stub
		System.out.print("수정할 회원 ID를 입력하세요 : ");
		String updateId = sc.next();
		
		Member m = mCon.selectOneId(updateId);
		
		boolean result = false;
		if(m == null) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		} else {
			System.out.print("비밀번호를 입력하세요 : ");
			if(m.getMemberPwd().equals(sc.next()))
			{
				while(true) {
					System.out.println("수정할 목록을 선택하세요.");
					System.out.println("1. 비밀번호 변경");
					System.out.println("2. 이름 변경");
					System.out.println("3. 성별 변경");
					System.out.println("4. 나이 변경");
					System.out.println("5. 이메일 변경");
					System.out.println("6. 핸드폰번호 변경");
					System.out.println("7. 주소 변경");
					System.out.println("8. 취미 변경");
					System.out.println("9. 변경 취소");
					System.out.println("0. 변경 사항 적용");
					System.out.print("선택 : ");
					int select = sc.nextInt();
					if (select == 9) {
						System.out.println("변경 내용을 취소하고 돌아갑니다.");
						return;	
					} 
					if (select == 0) {
						result = mCon.updateMember(m);
						break;
					}
					switch(select) {
					case 1:
						System.out.println("현재 비밀번호 : " + m.getMemberPwd());
						System.out.print("수정할 비밀번호(필수 입력사항 입니다.) : ");
						m.setMemberPwd(sc.next());
						break;
					case 2:
						System.out.println("현재 이름 : " + m.getMemberName());
						System.out.print("수정할 이름(필수 입력사항 입니다.) : ");
						m.setMemberName(sc.next());
						break;
					case 3:
						System.out.println("현재 성별 : " + m.getGender());
						System.out.print("수정할 성별(남/녀 中 택 1) : ");
						m.setGender(sc.next().charAt(0));
						break;
					case 4:
						System.out.println("현재 나이 : " + m.getAge());
						System.out.print("수정할 나이(필수 입력사항 입니다.) : ");
						m.setAge(sc.nextInt());
						break;
					case 5:
						System.out.println("현재 이메일 : " + m.getEmail());
						System.out.print("수정할 이메일 : ");
						m.setEmail(sc.next());
						break;
					case 6:
						System.out.println("현재 핸드폰번호 : " + m.getPhone());
						System.out.print("수정할 핸드폰번호 : ");
						m.setPhone(sc.next());
						break;
					case 7:
						System.out.println("현재 주소 : " + m.getAddress());
						System.out.print("수정할 비밀번호(필수 입력사항 입니다.) : ");
						sc.nextLine();
						m.setAddress(sc.nextLine());
						break;
					case 8:
						System.out.println("현재 취미 : " + m.getHobby());
						System.out.print("수정할 취미(여러 개일 경우 , 로 구분) : ");
						sc.nextLine();
						m.setHobby(sc.nextLine());
						break;
					}
				}
			} else {
				System.out.println("비밀번호가 맞지 않습니다.");
				return;
			}
		}
		
		if(result) {
			System.out.println("변경이 정상적으로 완료되었습니다.");
		} else {
			System.out.println("변경 실패!! 지속적으로 실패시 관리자에게 문의하세요");
		}
	}
	private void deleteMember() {
		// TODO Auto-generated method stub
		System.out.print("탈퇴할 ID를 입력하세요 : ");
		String updateId = sc.next();
		
		Member m = mCon.selectOneId(updateId);
		
		boolean result = false;
		if(m == null) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		} else {
			System.out.print("탈퇴를 위해 비밀번호를 입력하세요 : ");
			if(m.getMemberPwd().equals(sc.next())) {
				System.out.println("정말로 탈퇴하시겠습니까?");
				System.out.print("[Y / N] 中 택 1 : ");
				if(sc.next().charAt(0) == 'Y') {
					result = mCon.deleteMember(m);
				} else {
					System.out.println("탈퇴를 취소하고 돌아갑니다.");
					return;
				}
			} else {
				System.out.println("비밀번호가 맞지 않습니다.");
				System.out.println("메인 메뉴로 돌아갑니다.");
				return;
			}
		}
		
		if(result) {
			System.out.println("탈퇴가 성공적으로 완료되었습니다.");
		} else {
			System.out.println("삭제 실패!! 지속적으로 실패 시 관리자에게 문의하세요.");
		}
	}
}
