package com;

/*
 * 맛집 정보를 담고 있는 객체
 * 멤버 변수에 대한 getter/setter
 */

public class ResVO {
	
	private String res_name;		// 맛집 이름					50 Byte
	private String res_type;		// 맛집 종류 (한식, 중식 ..)		20 Byte
	private String location;		// 위치 (서울, 부산, 전주 ..)	20 Byte	
	private int res_likes;			// 좋아요 개수
	private String prior_name;		// 수정 전 맛집 이름
	private String prior_location;	// 수정 전 맛집 위치
	
	
	public void setPrior_name(String prior_name) {
		this.prior_name = prior_name;
	}

	public String getPrior_name() {
		return prior_name;
	}

	public String getPrior_location() {
		return prior_location;
	}

	public void setPrior_location(String prior_location) {
		this.prior_location = prior_location;
	}

	public String getRes_name() {
		return res_name;
	}
	
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	
	public String getRes_type() {
		return res_type;
	}
	
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getRes_likes() {
		return res_likes;
	}
	
	public void setRes_likes(int res_likes) {
		this.res_likes = res_likes;
	}
	
	
	

}
