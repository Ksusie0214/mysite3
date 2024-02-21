package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {

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

	public int insertGuest(GuestVo guestVo) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비(insert + update)
			String query = "";
			query += " insert into guest ";
			query += " values(null, ?, ?, now(), ? ) ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPassword());
			pstmt.setString(3, guestVo.getContent());

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
	// insertGuest

	public List<GuestVo> guestSelect() {
		int count = -1;

		this.getConnection();

		List<GuestVo> guestList = new ArrayList<GuestVo>();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비(insert + update)
			String query = "";
			query += " select   no, ";
			query += " 			name, ";
			query += " 			password, ";
			query += " 			date, ";
			query += " 			content ";
			query += " from guest ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery(query);

			// 4.결과처리

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String date = rs.getString("date");
				String content = rs.getString("content");

				GuestVo guestVo = new GuestVo(no, name, password, date, content);
				// 리스트에 주소 추가
				guestList.add(guestVo);

				System.out.println(guestVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return guestList;

	}
// GuestSelect


public int GuestDelete(int no, String password) {
	
	int count = -1;

	
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
		String url = "jdbc:mysql://localhost:3306/web_db";
		conn = DriverManager.getConnection(url, "web", "web");
		// 3. SQL문 준비 / 바인딩 / 실행
		String query = "";
		query += " delete from guest ";
		query += " where no=? ";
		query += " and password=? ";
		
		//바인딩
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);
		pstmt.setString(2, password);
		
		//실행
		count = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + "건 삭제되었습니다.");
		
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
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
		}return count;
}}
