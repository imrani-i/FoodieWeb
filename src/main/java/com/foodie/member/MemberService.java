package com.foodie.member;

import java.sql.SQLException;
import java.util.List;

import com.foodie.post.PostDTO;




public class MemberService {

	MemberDAO memberDAO = new MemberDAO();
	// 1. 회원가입

	public int memberInsert(MemberDTO member) throws SQLException {
		return memberDAO.memberInsert(member);
	}

	// 2.아이디 체크
	public MemberDTO loginCheck(String id, String password) {
		return memberDAO.loginCheck(id, password);
	}
	
	// 3.가입 체크
	public boolean joinCheck(String id) {
		return memberDAO.joinCheck(id);
	}
	
	// 4.작성글 조회
	
	public List<PostDTO> selectByWriter(String writer) {
		return memberDAO.selectByWriter(writer);
	}
	
	// 5. 작성글 삭제
	public int deletePost(int deleteNo) {
		return memberDAO.deletePost(deleteNo);
	}
}
