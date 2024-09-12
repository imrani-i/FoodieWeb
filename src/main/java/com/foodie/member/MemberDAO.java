package com.foodie.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodie.post.PostDTO;


import util.DBUtil;
import util.DateUtil;

public class MemberDAO {

	Connection conn; // DB연결
	Statement st; // sql 코드 전송
	PreparedStatement pst; // Statement 를 상속 받음, (?)바인딩 변수 지원
	ResultSet rs; // 결과 반환
	
	// 포스팅 삭제
	public int deletePost(int deleteNo) {
		int result = 0;
		String sql = "delete from post"
				+ " where post_no = ?";
		
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deleteNo);
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;

	}

	// 작성글 조회
	public List<PostDTO> selectByWriter(String writer) {
		List<PostDTO> postList = new ArrayList<PostDTO>();
		String sql = "select *"
				+ " from post"
				+ " where post.writer = ? ORDER BY post_no ASC";
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, writer);
			rs =pst.executeQuery();
			while (rs.next()) {
				PostDTO post = makePost(rs);
				postList.add(post);
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return postList;
	}

	// 회원가입
	public int memberInsert(MemberDTO member) throws SQLException {
		int result = 0;
		String sql = "insert into foodieuser values (?,?)";
		;
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, member.getId());
			pst.setString(2, member.getPassword());
			result = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;

	}
	
	

	public MemberDTO loginCheck(String id, String password) {
		MemberDTO member = null; 

		String check =  "select member_id, member_pw "
				+ " from foodieuser where member_id = ?";
			
		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(check);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				if(rs.getString("member_pw").equals(password)){
					member = new MemberDTO();
					member.setId(rs.getString("member_id"));
					member.setPassword(rs.getString("member_pw"));
				} else {
					member = new MemberDTO();
					member.setId("pwError"); // 비밀번호 오류 
				}
			}else {
				member = new MemberDTO();
				member.setId("idError"); // 존재하지않는 직원 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	
	public boolean joinCheck(String id) {
	    String checkQuery = "SELECT member_id FROM foodieuser WHERE member_id = ?";
	    boolean result = false;
	    
	    try {
	        conn = DBUtil.dbConnection();
	        pst = conn.prepareStatement(checkQuery);
	        pst.setString(1, id);
	        rs = pst.executeQuery();
	        if (rs.next()) {
	        	result = true; // 결과가 있으면 true 설정
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 자원 해제
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println(result);
	    return result;
	}
	


	private PostDTO makePost(ResultSet rs) throws SQLException {

		PostDTO post = new PostDTO();
		post.setPostNo(rs.getInt("post_no"));
		post.setRestaurantPname(rs.getString("restaurant_pname"));
		post.setWriter(rs.getString("writer"));
		post.setGrade(rs.getInt("grade"));
		post.setContent(rs.getString("post_content"));
		post.setRecommendMenu(rs.getString("recommend_menu"));
		post.setPcategory(rs.getString("menu_pcategory"));
		post.setTime(DateUtil.timeStampToLocalDateTime(rs.getTimestamp("post_date")));
		return post;

	}

}
