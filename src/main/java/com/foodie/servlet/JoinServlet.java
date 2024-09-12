package com.foodie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodie.member.MemberDTO;
import com.foodie.member.MemberService;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/user/userJoin.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("userMain.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("join".equals(action)) {

			MemberService service = new MemberService();
			System.out.println( request.getParameter("username"));
			String id = request.getParameter("username");
			String password = request.getParameter("password");
			boolean canJoin = service.joinCheck(id);
			System.out.println(canJoin);
			if (canJoin == true) {
				alertAndRedirect(response, "중복된 아이디입니다.", "userMain.jsp");
			} else {
				MemberDTO member = new MemberDTO(id,password);
				try {
					service.memberInsert(member);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				alertAndRedirect(response, "회원가입 성공!\n 로그인 후 사용해주세요.", "userMain.jsp");
			}

		}

	}

	private void alertAndRedirect(HttpServletResponse response, String message, String redirectUrl) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('" + message + "'); window.location.replace('" + redirectUrl + "');</script>");
	}

}
