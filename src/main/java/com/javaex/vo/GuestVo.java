package com.javaex.vo;

public class GuestVo {
	
	//필드
	private int no;
	private String name;
	private String password;
	private String date;
	private String content;
	
	//생성자
	public GuestVo() {
		
	}
	
	public GuestVo(int no, String password) {
		super();
		this.no = no;
		this.password = password;
	}

	public GuestVo(String name, String password, String content) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
	}
	
	public GuestVo(String name, String password, String date, String content) {
		super();
		this.name = name;
		this.password = password;
		this.date = date;
		this.content = content;
	}
	public GuestVo(int no, String name, String password, String date, String content) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.date = date;
		this.content = content;
	}

	//메소드 g/s
		public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", name=" + name + ", password=" + password + ", date=" + date + ", content="
				+ content + "]";
	}
	
	
}
