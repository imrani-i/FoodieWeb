package com.foodie.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodie.member.MemberDTO;
import com.foodie.member.MemberService;
import com.foodie.post.PostDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import util.DBUtil;
import util.DateUtil;


@WebServlet("/jsp/myPost.do")
public class MyPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyPostServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		String writer = loginMember.getId();
		MemberService mservice = new MemberService();
		List<PostDTO> postList = mservice.selectByWriter(writer);
		request.setAttribute("writer", writer);
		request.setAttribute("postList", postList);	
		request.getRequestDispatcher("myPost.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		String writer = loginMember.getId();
		MemberService mservice = new MemberService();
		
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String jsonString = sb.toString();

	    // JSON 데이터 파싱
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

	    // 파싱된 데이터 사용
	    int postNo = jsonObject.get("postNo").getAsInt();

		mservice.deletePost(postNo);
		response.getWriter().write(String.valueOf(postNo));
	}

}
