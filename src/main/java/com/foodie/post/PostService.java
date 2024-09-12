package com.foodie.post;

import java.util.List;


public class PostService {

	PostDAO postDAO = new PostDAO();
	
	public List<PostDTO> selectAll(){
		return postDAO.selectAll();
	}
	
		
	public int postInsert (PostDTO post) {
		return postDAO.postInsert(post);
	}
	
}
