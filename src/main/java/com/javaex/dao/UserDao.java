package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";

	// 메소드 일반
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}// getConnection()

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}// close()

	public int insertUser(UserVo userVo) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비(insert + update)
			String query = "";
			query += " insert into users ";
			query += " values(null ,? ,? ,? ,? ) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건 추가되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;

	}
// insertUser

	public UserVo selectUserByIdPw(UserVo userVo) {
		UserVo authUser = null;
		
		this.getConnection();
	
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비(insert + update)
			String query = "";
			query += " select   no, ";
			query += " 			name ";
			query += " from users ";
			query += " where id=? ";
			query += " and password=? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
	
			// 실행
			rs = pstmt.executeQuery();
	
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				authUser = new UserVo();
				authUser.setNo(no);
				authUser.setName(name);
			}
	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return authUser;
	
	}
	// selectUserByIdPw

	public UserVo selectUserById(UserVo userVo) {
		UserVo authUser = null;
		
		this.getConnection();
	
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비(insert + update)
			String query = "";
			query += " select   no, ";
			query += " 			id, ";
			query += " 			password, ";
			query += " 			name, ";
			query += " 			gender ";
			query += " from users ";
			query += " where id=? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
	
			// 실행
			rs = pstmt.executeQuery();
	
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				authUser = new UserVo();
				authUser.setNo(no);
				authUser.setId(id);
				authUser.setPw(password);
				authUser.setName(name);
				authUser.setGender(gender);
			}
	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return authUser;
	
	}
	// selectUserByIdPw


public int modifyUser(UserVo userVo) {

	int count=-1;
	
	this.getConnection();

	try {
		// 3. SQL문 준비 / 바인딩 / 실행
		// sql문 준비(insert + update)
		String query = "";
		query += " update users ";
		query += " set		no=?, ";
		query += " 			id=?, ";
		query += " 			password=?, ";
		query += " 			name=?, ";
		query += " 			gender=? ";
		query += " where id=? ";
		
		// 바인딩
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, userVo.getNo());
		pstmt.setString(2, userVo.getId());
		pstmt.setString(3, userVo.getPw());
		pstmt.setString(4, userVo.getName());
		pstmt.setString(5, userVo.getGender());
		pstmt.setString(6, userVo.getId());

		// 실행
		count = pstmt.executeUpdate();

		// 4.결과처리
		System.out.println(count + "건 수정되었습니다.");

	} catch (SQLException e) {
		System.out.println("error:" + e);
	}
	this.close();
	return count;

}
}// modifyUser
	

		
