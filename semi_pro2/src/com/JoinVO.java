package com;

import java.sql.Date;

/*
 * 사용자 정보를 담고 있는 객체
 * 멤버 변수에 대한 getter/setter
 */

public class JoinVO {
	
	private String userID;		// 아이디			20 Byte
	private String userPW;		// 패스워드		20 Byte
	private String username;	// 사용자 이름 	20 Byte
	private Date createDate;	// 회원가입일
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserPW() {
		return userPW;
	}
	
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	

}
