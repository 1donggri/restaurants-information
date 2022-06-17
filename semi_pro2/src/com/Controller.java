package com;

import java.util.ArrayList;

/*
 * 중간 제어용 객체
 */

public class Controller {
	
	private DAO dao = new DAO();
	
	public boolean join(JoinVO data) {
		// 회원가입 제어
		// 중복된 아이디가 없어야 함
		JoinVO account = dao.get(data.getUserID());
		
		if(account != null) {
			return false;
		}
		
		boolean result = dao.register(data);
		return result;
	}
	
	public JoinVO login(String userid, String userpw) {
		// 로그인 제어
		JoinVO data = dao.get(userid);
		
		if(data.getUserPW().equals(userpw)) {
			return data;
		}
		
		return null;
	}
	
	public boolean join(ResVO data) {
		// 맛집 등록 제어
		// 같은 지역 내에 동일한 이름의 맛집이 등록되어 있다면 등록하지 못하게 함
		ResVO res = dao.get(data.getRes_name(), data.getLocation());
		
		if(res != null) {
			return false;
		}
		
		boolean result = dao.register(data);
		return result;
	}
	
	public ResVO search(String resname, String location) {
		// 맛집 수정 또는 삭제 전 검색 제어
		return dao.get(resname, location);
	}
	
	public ArrayList locationLank(String location) {
		// 지역별 랭킹 조회
		return dao.locationLank(location);
	}
	
	public ArrayList typeLank(String type) {
		// 카테고리별 랭킹 조회
		return dao.typeLank(type);
	}
	
	public ArrayList resList(String type, String location) {
		// 조건별 맛집 리스트 검색
		return dao.resList(type, location);
	}
	
	public boolean update(ResVO data) {
		// 맛집 정보 수정
		return dao.update(data);
	}
	
	public boolean delete(ResVO data) {
		// 맛집 정보 삭제
		return dao.delete(data);
	}

}
