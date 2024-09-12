package com.foodie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodie.member.MemberDTO;
import com.foodie.member.MemberService;

@WebServlet("/user/userLogin.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// login창 보여주기
		RequestDispatcher rd = request.getRequestDispatcher("userMain.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자가 입력한 ID, PASS 검사
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("login".equals(action)) {
			
			MemberService service = new MemberService();
			String id = request.getParameter("username");
			String password = request.getParameter("password");

			MemberDTO member = service.loginCheck(id, password);
			if (member == null || member.getId().equals("idError")) {
				// 존재하지않는 직원
				// request.setAttribute("message", "존재하지않는 회원입니다.");
				alertAndRedirect(response, "존재하지 않는 회원입니다.", "userMain.jsp");
			} else if (member.getId().equals("pwError")) {
				// 비밀번호 오류
				// request.setAttribute("message", "비밀번호 오류입니다.");

				alertAndRedirect(response, "비밀번호 오류입니다.", "userMain.jsp");
			} else {
				// 로그인 성공
				
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", member);
				response.sendRedirect("../jsp/main.do");
				
			}
		} return;
		

		/*
		 * RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		 * rd.forward(request, response);
		 */

	}

	private void alertAndRedirect(HttpServletResponse response, String message, String redirectUrl) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + message + "'); window.location.replace('" + redirectUrl + "');</script>");
	}

}
