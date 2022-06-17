package com;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
	
	private Scanner sc = new Scanner(System.in);
	private Menu menu = new Menu();
	private Controller jc = new Controller();
	
	public void show() {
		// 초기 화면
		while(true) {
			System.out.print(menu.getMain());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					this.joinMenu();	break;
				case "2":
					this.loginMenu();	break;
				default:
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력하세요.");
			}
		}
	}
	
	public void joinMenu() {
		// 회원 가입을 위한 화면과 기능 제공
		JoinVO data = new JoinVO();
		System.out.print("아이디 : ");
		data.setUserID(sc.nextLine());
		System.out.print("패스워드 : ");
		data.setUserPW(sc.nextLine());
		System.out.print("이름 : ");
		data.setUsername(sc.nextLine());
		
		boolean result = jc.join(data);
		
		if(result == true) {
			System.out.println("회원 가입을 축하합니다.");
		} else {
			System.out.println("회원 가입을 할 수 없습니다. (중복된 아이디가 있습니다.)");
		}
	}
	
	public void loginMenu() {
		// 로그인을 위한 화면과 기능 제공
		System.out.print("아이디 : ");
		String userid = sc.nextLine();
		System.out.print("패스워드 : ");
		String userpw = sc.nextLine();

		JoinVO account = jc.login(userid, userpw);
		
		if(account != null) {
			System.out.printf("%s 님이 로그인을 하였습니다. \n", account.getUsername());
			afterLoginMenu(account);
		} else {
			System.out.println("로그인을 실패하였습니다.");
		}
	}
	
	public void afterLoginMenu(JoinVO account) {
		// 로그인 후 메인 화면
		while(true) {
			System.out.print(menu.getAfterLogin());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					// 맛집 검색
					this.afterSearchMenu();
					break;
				case "2":
					// 맛집 등록
					ResVO data = new ResVO();
					System.out.print("맛집 이름 : ");
					data.setRes_name(sc.nextLine());
					System.out.print("맛집 종류 (한식, 일식, 중식, 양식) : ");
					data.setRes_type(sc.nextLine());
					System.out.print("맛집 위치 (지역명) : ");
					data.setLocation(sc.nextLine());
					data.setRes_likes(0);
					
					boolean result = jc.join(data);
					
					if(result == true) {
						System.out.println(data.getRes_name() + " 이(가) 맛집으로 등록 되었습니다.");
					} else {
						System.out.println("해당 지역에 이미 등록된 맛집 입니다.");
					}
					
					break;
				case "3":
					// 맛집 수정
					// 좋아요 수는 수정 x
					
					// 사용자가 지역을 검색하면 해당 지역의 맛집 리스트 보여주기
					System.out.print("조회할 지역 입력 : ");
					String location = sc.nextLine();
					locationLank(location);
					// 입력받은 지역과 맛집 이름으로 맛집 조회 
					System.out.print("조회할 맛집 이름 입력 : ");
					String resname = sc.nextLine();
					ResVO res = jc.search(resname, location);
					
					if(res == null) {
						System.out.println(location + " 지역에 해당 맛집이 존재하지 않습니다.");
						break;
					}
					
					res.setPrior_name(resname);
					res.setPrior_location(location);
					
					// 수정 전 맛집 정보 보여주기
					System.out.println(resname + " 에 대한 맛집 정보를 변경합니다.");
					System.out.println("맛집 이름 : " + res.getRes_name());
					System.out.println("맛집 타입 : " + res.getRes_type());
					System.out.println("맛집 위치 : " + res.getLocation());
					System.out.print("\n");
					
					// 수정
					System.out.println("아무것도 입력 하지 않으면 이전 값을 유지 합니다.");
					System.out.println("변경할 맛집 이름을 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? res.getRes_name() : input;
					res.setRes_name(input);
					
					System.out.println("변경할 맛집 타입을 입력하세요. (한식, 일식, 중식, 양식)");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? res.getRes_type() : input;
					res.setRes_type(input);
					
					System.out.println("변경할 맛집 위치(지역)를 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? res.getLocation() : input;
					res.setLocation(input);
					
					result = jc.update(res);
					
					if(result == true) {
						System.out.println("수정이 완료되었습니다.");
						System.out.println("맛집 이름 : " + res.getRes_name());
						System.out.println("맛집 타입 : " + res.getRes_type());
						System.out.println("맛집 위치 : " + res.getLocation());
					} else {
						System.out.println("수정에 실패하였습니다.");
					}
					
					break;
				case "4":
					// 맛집 삭제
					// 사용자가 지역을 검색하면 해당 지역의 맛집 리스트 보여주기
					System.out.print("조회할 지역 입력 : ");
					location = sc.nextLine();
					locationLank(location);
					System.out.print("삭제할 맛집 이름 입력 : ");
					resname = sc.nextLine();
					res = jc.search(resname, location);
					
					if(res == null) {
						System.out.println(location + " 지역에 해당 맛집이 존재하지 않습니다.");
						break;
					}
					System.out.println(resname + " 에 대한 맛집 정보를 삭제합니다.");
					
					if(jc.delete(res) == true) {
						System.out.println("삭제 되었습니다.");
					} else {
						System.out.println("삭제 처리를 수행할 수 없습니다.");
					}
					break;
				case "5":
					// 좋아요 누르기
					// 사용자가 지역을 검색하면 해당 지역의 맛집 리스트 보여주기
					System.out.print("조회할 지역 입력 : ");
					location = sc.nextLine();
					locationLank(location);
					System.out.print("좋아요 누를 맛집 이름 입력 : ");
					resname = sc.nextLine();
					res = jc.search(resname, location);
					
					if(res == null) {
						System.out.println(location + " 지역에 해당 맛집이 존재하지 않습니다.");
						break;
					}
					
					res.setPrior_name(resname);
					res.setPrior_location(location);
					
					res.setRes_likes(res.getRes_likes() + 1);
					result = jc.update(res);
					
					if(result == true) {
						System.out.println(resname + " 에 좋아요 를 눌렀습니다.");
						System.out.println(resname + " 의 좋아요 개수는 현재 " + res.getRes_likes() + " 개 입니다.");
					} else {
						System.out.println("좋아요 누르기 에 실패하였습니다.");
					}
					
					break;
				case "6":
					// 로그아웃
					System.out.println("로그아웃 중 입니다.");
					account = null;
					System.out.println("로그아웃 작업이 완료되었습니다.");
					return;
				default:
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력하세요.");
			}
		}
	}
	
	public void afterSearchMenu() {
		// '1. 맛집 검색' 이후의 화면 
		while(true) {
			System.out.print(menu.getAfterSearch());
			System.out.print(">>> ");
			String input = sc.nextLine();
			
			switch(input) {
				case "1":
					// 지역별 랭킹
					System.out.print("조회할 지역 입력 : ");
					String location = sc.nextLine();
					locationLank(location);
					break;
				case "2":
					// 카테고리별 랭킹
					System.out.print("조회할 카테고리 입력 (한식, 일식, 중식, 양식) : ");
					String type = sc.nextLine();
					ArrayList<ResVO> res = jc.typeLank(type);
					list(res);
					break;
				case "3":
					// 조건별 검색
					System.out.print("조회할 지역 입력 : ");
					location = sc.nextLine();
					System.out.print("조회할 카테고리 입력 (한식, 일식, 중식, 양식) : ");
					type = sc.nextLine();
					res = jc.resList(type, location);
					list(res);
					break;
				default:
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력하세요.");
			}
		}
	}
	
	public void locationLank(String location) {
		// location 에 따른 지역별 랭킹을 보여주는 기능 제공
		ArrayList<ResVO> res = jc.locationLank(location);
		list(res);
	}
	
	public void list(ArrayList<ResVO> res) {
		// 맛집 리스트를 출력해주는 기능 제공
		if(res.size() == 0) {
			System.out.println("해당 조건에 부합하는 맛집이 존재하지 않습니다.");
		} else {
			System.out.print("\n");
			System.out.println("------------------------------------------------------");
			System.out.printf("%-15s %-10s %-10s %-10s", "이름", "타입", "지역", "좋아요");
			System.out.println();
	        System.out.println("------------------------------------------------------");
			for(ResVO r : res) {
				System.out.format("%-15s %-10s %-10s %-10s"
								 , r.getRes_name()
								 , r.getRes_type()
								 , r.getLocation()
								 , r.getRes_likes());
				System.out.println();
			}
			System.out.println("------------------------------------------------------");
			System.out.print("\n");
		}
	}

}
