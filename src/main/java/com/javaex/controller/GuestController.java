package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/guest")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GuestController");

		// Guest에서 업무 구분
		String action = request.getParameter("action");
		System.out.println(action);

		if ("eform".equals(action)) {
			System.out.println("eform: 등록");

			System.out.println("list: 리스트");
			// db 사용
			GuestDao guestDao = new GuestDao();
			// list 가져오기
			List<GuestVo> guestList = guestDao.guestSelect();
			System.out.println(guestList);

			// 데이터 담기
			request.setAttribute("guestList", guestList);

			// 방명록 연결
			WebUtil.forward(request, response, "/WEB-INF/views/guest/addList.jsp");
			
			
		} else if ("write".equals(action)) {
			System.out.println("guest>write");

			// 파라미터에서 데이터 꺼내기
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");

			System.out.println(name);
			System.out.println(password);
			System.out.println(content);

			// 데이터를 vo로 묶어주기
			GuestVo guestVo = new GuestVo(name, password, content);

			System.out.println(guestVo.toString());

			// dao의 메소드로 등록
			GuestDao guestDao = new GuestDao();
			guestDao.insertGuest(guestVo);

			// 리다이렉트
			WebUtil.redirect(request, response, "/mysite3/guest?action=eform");
			
			
		}else if("delete".equals(action)) {
			System.out.println("guest>delete");
			// 파라미터에서 데이터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("pass");

			System.out.println(no);
			System.out.println(password);

			// 데이터를 vo로 묶어주기
			GuestVo guestVo = new GuestVo(no, password);

			System.out.println(guestVo.toString());

			// dao의 메소드로 등록
			GuestDao guestDao = new GuestDao();
			guestDao.GuestDelete(no, password);

			// 리다이렉트
			WebUtil.redirect(request, response, "http://localhost:8080/mysite3/guest?action=eform");
		}else if("dform".equals(action)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/guest/deleteForm.jsp");
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
