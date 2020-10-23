package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	private MemberController mCon = new MemberController();

	public void start() {

		while (true) {
			System.out.println("===== 회원 관리 프로그램 ver2.0 =====");
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
				insertMemeber();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			}
		}
	}

	public void selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> list = mCon.selectAll();
		if (list.isEmpty()) {// list안에 데이터가 없다면!
			System.out.println("<<< 회원들의 정보가 현재 없습니다. >>>");
		} else {
			for (Member m : list) {
				System.out.println(m);
			}
		}
	}

	public void selectOneId() {
		// TODO Auto-generated method stub
		System.out.print("검색할 회원 아이디를 입력하세요 : ");
		String selectId = sc.next();
		Member m = mCon.selectOneId(selectId);
		if (m == null) {
			System.out.println("찾는 ID가 존재하지 않습니다.");
		} else {
			System.out.println(m);
		}
	}

	public void selectName() {
		// TODO Auto-generated method stub
		System.out.print("검색할 회원 이름을 입력하세요 : ");
		String selectName = sc.next();

		ArrayList<Member> list = mCon.selectName(selectName);
		if (list.isEmpty()) {// list안에 데이터가 없다면!
			System.out.println("찾는 이름의 회원이 존재하지 않습니다.");
		} else {
			for (Member m : list) {
				System.out.println(m);
			}
		}
	}

	public void insertMemeber() {
		// TODO Auto-generated method stub

		Member m = new Member();
		System.out.println("===== 회원 가입 폼=====");
		while (true) {
			System.out.print("[ID(필수)] : ");
			m.setMemberId(sc.next());
			Member checkM = mCon.selectOneId(m.getMemberId());
			if (checkM == null) {// list안에 데이터가 없다면!
				break;
			} else {
				System.out.print("중복된 아이디입니다.");
			}
		}
		System.out.print("[비밀번호(필수)] : ");
		m.setMemberPwd(sc.next());
		System.out.print("[이름(필수)] : ");
		m.setMemberName(sc.next());
		System.out.print("[성별 (남/여)] : ");
		m.setGender(sc.next().charAt(0));
		System.out.print("[나이(필수)] : ");
		m.setAge(sc.nextInt());
		System.out.print("[이메일] : ");
		m.setEmail(sc.next());
		System.out.print("[핸드폰번호(- 빼고 입력)] : ");
		m.setPhone(sc.next());
		sc.nextLine(); // 버퍼비우기
		System.out.print("[주소] : ");
		m.setAddress(sc.nextLine());
		System.out.print("[취미](여러개인 경우 ,로 구분) : ");
		m.setHobby(sc.nextLine());
		
		boolean check = mCon.insertMember(m);
		if (check) {
			System.out.println("<<<회원가입 성공>>>");
		} else {
			System.err.println("회원가입 실패!!! 지속적 문제시 관리자에게 문의하세요");
		}
	}

	public void updateMember() {
		// TODO Auto-generated method stub
		System.out.print("수정할 회원 ID를 입력하세요 : ");
		String selectId = sc.next();
		boolean check = false;
		Member m = mCon.selectOneId(selectId);

		if (m == null) {// list안에 데이터가 없다면!
			System.out.println("찾는 ID가 존재하지 않습니다.");
		} else {
			System.out.print(m.getMemberId() + "의 비밀번호 입력:");
			String pwd = sc.next();
			if (m.getMemberPwd().equals(pwd)) {
				while(true) {
					System.out.println("===수정할 정보 선택===");
					System.out.println("1.[비밀번호]");
					System.out.println("2.[이름]");
					System.out.println("3.[성별]");
					System.out.println("4.[나이]");
					System.out.println("5.[이메일]");
					System.out.println("6.[핸드폰번호]");
					System.out.println("7.[주소]");
					System.out.println("8.[취미]");
					System.out.println("9.[수정을 취소하고 돌아가기]");
					System.out.println("0.[변경 내용 적용]");
					System.out.print("선택:");
					int select = sc.nextInt();
					if(select == 0) {
						check = mCon.updateMember(m);
					} else {
						switch (select) {
						case 1:
							System.out.println("====비밀번호 변경====");
							System.out.println("[현재 비밀번호] : " + m.getMemberPwd());
							System.out.print("[수정할 비밀번호] : ");
							m.setMemberPwd(sc.next());
							break;
						case 2:
							System.out.println("====이름 변경====");
							System.out.println("[현재 이름] : " + m.getMemberName());
							System.out.print("[수정할 이름] : ");
							m.setMemberName(sc.next());
							break;
						case 3:
							System.out.println("====성별 변경====");
							System.out.println("[현재 성별] : " + m.getGender());
							System.out.print("[수정할 성별 (남/여)] : ");
							m.setGender(sc.next().charAt(0));
							break;
						case 4:
							System.out.println("====나이 변경====");
							System.out.println("[현재 나이] : " + m.getAge());
							System.out.print("[수정할 나이(필수)] : ");
							m.setAge(sc.nextInt());
							break;
						case 5:
							System.out.println("====이메일 변경====");
							System.out.println("[현재 이메일] : " + m.getEmail());
							System.out.print("[수정할 이메일] : ");
							m.setEmail(sc.next());
							break;
						case 6:
							System.out.println("====핸드폰번호 변경====");
							System.out.println("[현재 핸드폰번호] : " + m.getPhone());
							System.out.print("[수정할 핸드폰번호] : ");
							m.setPhone(sc.next());
							break;
						case 7:
							System.out.println("====주소 변경====");
							System.out.println("[현재 주소] : " + m.getAddress());
							System.out.print("[수정할 주소] : ");
							sc.nextLine();
							m.setAddress(sc.nextLine());
							break;
						case 8:
							System.out.println("====취미 변경====");
							System.out.println("[현재 취미] : " + m.getHobby());
							System.out.print("[수정할 취미] : ");
							sc.nextLine();
							m.setHobby(sc.nextLine());
							break;
						case 9:
							System.out.println("수정 취소");
							return;
						default:
							System.out.println("잘못된 입력");
							break;
						}
					}
				}
			} else {
				System.out.println("비밀번호 오류 다음에 다시 시도하여 주십시오.");
				return;
			}
		}
		if (check) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}

	public void deleteMember() {
		// TODO Auto-generated method stub
		boolean check = false;
		System.out.print("탈퇴할 회원 ID를 입력하세요 : ");
		String selectId = sc.next();

		Member m = mCon.selectOneId(selectId);

		if (m == null) {// list안에 데이터가 없다면!
			System.out.println("삭제하려는 ID가 존재하지 않습니다.");
		} else {
			System.out.print(m.getMemberId() + "의 비밀번호 입력:");
			String pwd = sc.next();
			if (m.getMemberPwd().equals(pwd)) {
				System.out.println("정말로 삭제하시겠습니까?? (삭제하시면 정보를 되돌릴 수 없습니다.)");
				System.out.println("Y / N 입력 (다른문자 입력시 취소)");
				char select = sc.next().charAt(0);
				
				if(select == 'Y') {
					check = mCon.deleteMember(m);
				} else {
					System.out.println("삭제 취소");
					return;
				}
			}
		}
		
		if(check) {
			System.out.println(selectId+"님 회원탈퇴가 완료되었습니다.");
		} else {
			System.out.println("회원 탈퇴 실패!! 지속적으로 실패시 관리자에게 문의하세요");
		}
	}
}
