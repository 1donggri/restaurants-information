package com;

public class Menu {
	
	public String getMain() {
		String s = "";
		s += "<<< 맛집 정보 프로그램 >>> \n";
		s += "┌───────────────────┐\n";
		s += "│ 1. 회원 가입	    │\n";
		s += "│ 2. 로그인	    │\n";
		s += "└───────────────────┘\n";
		
		return s;
	}
	
	public String getAfterLogin() {
		String s = "";
		s += "┌───────────────────┐\n";
		s += "│ 1. 맛집 검색	    │\n";
		s += "│ 2. 맛집 등록	    │\n";
		s += "│ 3. 맛집 수정	    │\n";
		s += "│ 4. 맛집 삭제	    │\n";
		s += "│ 5. 좋아요 누르기	    │\n";
		s += "│ 6. 로그 아웃	    │\n";
		s += "└───────────────────┘\n";
		
		return s;
	}
	
	public String getAfterSearch() {
		String s = "";
		s += "<<<    맛집 검색    >>> \n";
		s += "┌───────────────────┐\n";
		s += "│ 1. 지역별 랭킹	    │\n";
		s += "│ 2. 카테고리별 랭킹	    │\n";
		s += "│ 3. 조건별 검색	    │\n";
		s += "└───────────────────┘\n";
		
		return s;
	}

}
