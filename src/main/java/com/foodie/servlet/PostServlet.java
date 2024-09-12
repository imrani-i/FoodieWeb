package com.foodie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.foodie.member.MemberDTO;
import com.foodie.post.PostDAO;
import com.foodie.post.PostDTO;
import com.foodie.post.PostService;
import com.foodie.restaurant.RestaurantDTO;
import com.foodie.restaurant.RestaurantService;



@WebServlet("/jsp/postRegister.do")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 여기서는 단순히 가게 리스트를 가져와서 응답에 포함시키는 것으로 예시를 들었습니다.
        // 실제로는 DAO(Data Access Object)나 서비스 계층을 통해 데이터를 가져오는 것이 더 적합합니다.
		RestaurantService rservice = new RestaurantService();
        List<RestaurantDTO> restaurantList = rservice.selectRestaurantAll(); // YourDAO는 실제 DAO 클래스 이름으로 대체되어야 합니다.

        // JSON 형식으로 응답을 작성
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < restaurantList.size(); i++) {
            RestaurantDTO restaurant = restaurantList.get(i);
            out.print("{\"name\":\"" + restaurant.getRestaurantName() + "\"}"); // 예시로서 'name' 필드를 사용했습니다.
            if (i < restaurantList.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
        out.flush();
        
        
		request.getRequestDispatcher("postRegister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		PostDAO postDAO = new PostDAO();
		
		String restarant = request.getParameter("restarant");
		String category = request.getParameter("category");
		String writer = loginMember.getId();
		int grade = Integer.parseInt(request.getParameter("grade"));
		String recommend = request.getParameter("recommend")!=null ? request.getParameter("recommend") : " ";
		String comment = request.getParameter("comment")!=null ? request.getParameter("comment") : " ";
		LocalDateTime now = LocalDateTime.now();
	
		PostDTO post = postDAO.makePost(category,restarant,writer,comment,recommend,grade,now);
	
		String alertScript = "<script>alert('" + writer + "님의 게시물이 등록되었습니다.');</script>";
		response.getWriter().println(alertScript);
		response.getWriter().println("<script>window.location.href = 'main.jsp';</script>");
		/* response.sendRedirect("main.jsp"); */
	}

}
