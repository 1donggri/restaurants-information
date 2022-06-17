package com;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.conn.db.DBConn;

/*
 * 데이터베이스에 직접 엑세스 하기 위한 객체
 */

public class DAO {
	
	private DBConn db;
	
	public DAO() {
		try {
			db = new DBConn(new File(System.getProperty("user.home") + "/semi_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean register(JoinVO data) {
		// 회원 가입 처리
		String query = "INSERT INTO account_t VALUES(?, ?, ?, SYSDATE)";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserID());
			pstat.setString(2, data.getUserPW());
			pstat.setString(3, data.getUsername());
			
			int rs = db.sendInsertQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JoinVO get(String userid) {
		// userID 에 해당하는 회원 정보 반환
		String query = "SELECT * FROM account_t WHERE USERID = ?";
		PreparedStatement pstat;
		try {
			pstat = db.getPstat(query);
			pstat.setString(1, userid);
			
			ResultSet rs = db.sendSelectQuery();
			if(rs.next()) {
				JoinVO data = new JoinVO();
				data.setUserID(rs.getString("userid"));
				data.setUserPW(rs.getString("userpw"));
				data.setUsername(rs.getString("username"));
				data.setCreateDate(rs.getDate("createdate"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean register(ResVO data) {
		// 맛집 등록 처리
		String query = "INSERT INTO restaurant_t VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getRes_name());
			pstat.setString(2, data.getRes_type());
			pstat.setString(3, data.getLocation());
			pstat.setInt(4, data.getRes_likes());
			
			int rs = db.sendInsertQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(ResVO data) {
		// 맛집 정보 수정 처리
		String query = "UPDATE restaurant_t"
				+ "		   SET RES_NAME = ?"
				+ "			 , RES_TYPE = ?"
				+ "			 , LOCATION = ?"
				+ "			 , RES_LIKES = ?"
				+ "		 WHERE RES_NAME = ? AND LOCATION = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getRes_name());
			pstat.setString(2, data.getRes_type());
			pstat.setString(3, data.getLocation());
			pstat.setInt(4, data.getRes_likes());
			pstat.setString(5, data.getPrior_name());
			pstat.setString(6, data.getPrior_location());
			
			int rs = db.sendUpdateQuery();
			if(rs >= 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(ResVO data) {
		// 맛집 정보 삭제 처리
		String query = "DELETE FROM restaurant_t WHERE RES_NAME = ? AND LOCATION = ?";
		PreparedStatement pstat;
		try {
			pstat = db.getPstat(query);
			pstat.setString(1, data.getRes_name());
			pstat.setString(2, data.getLocation());
			
			int rs = db.sendDeleteQuery();
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ResVO get(String res_name, String location) {
		// res_name 과 location 해당하는 맛집 정보 반환
		String query = "SELECT * FROM restaurant_t WHERE RES_NAME = ? AND location = ?";
		PreparedStatement pstat;
		try {
			pstat = db.getPstat(query);
			pstat.setString(1, res_name);
			pstat.setString(2, location);
			
			ResultSet rs = db.sendSelectQuery();
			if(rs.next()) {
				ResVO data = new ResVO();
				data.setRes_name(rs.getString("res_name"));
				data.setRes_type(rs.getString("res_type"));
				data.setLocation(rs.getString("location"));
				data.setRes_likes(rs.getInt("res_likes"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList locationLank(String location) {
		// location 에 해당하는 랭킹 반환 (좋아요 순)
		String query = "SELECT * FROM restaurant_t WHERE LOCATION = ? ORDER BY RES_LIKES DESC";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, location);
			
			ResultSet rs = db.sendSelectQuery();
			ArrayList<ResVO> list = new ArrayList<ResVO>();
			while(rs.next()) {
				ResVO data = new ResVO();
				data.setRes_name(rs.getString("res_name"));
				data.setRes_type(rs.getString("res_type"));
				data.setLocation(rs.getString("location"));
				data.setRes_likes(rs.getInt("res_likes"));
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList typeLank(String res_type) {
		// res_type 에 해당하는 랭킹 반환 (좋아요 순)
		String query = "SELECT * FROM restaurant_t WHERE RES_TYPE = ? ORDER BY RES_LIKES DESC";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, res_type);
			
			ResultSet rs = db.sendSelectQuery();
			ArrayList<ResVO> list = new ArrayList<ResVO>();
			while(rs.next()) {
				ResVO data = new ResVO();
				data.setRes_name(rs.getString("res_name"));
				data.setRes_type(rs.getString("res_type"));
				data.setLocation(rs.getString("location"));
				data.setRes_likes(rs.getInt("res_likes"));
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList resList(String res_type, String location) {
		// location 과 res_type 에 해당하는 전체 맛집 정보 반환 (좋아요 순)
		String query = "SELECT * FROM restaurant_t "
				+ "		WHERE RES_TYPE = ? AND LOCATION = ? "
				+ "		ORDER BY RES_LIKES DESC";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, res_type);
			pstat.setString(2, location);
			
			ResultSet rs = db.sendSelectQuery();
			ArrayList<ResVO> list = new ArrayList<ResVO>();
			while(rs.next()) {
				ResVO data = new ResVO();
				data.setRes_name(rs.getString("res_name"));
				data.setRes_type(rs.getString("res_type"));
				data.setLocation(rs.getString("location"));
				data.setRes_likes(rs.getInt("res_likes"));
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
