package com.foodie.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDTO {
	
	private String id;
	private String password;
	
	public MemberDTO() {
		
	}
	public MemberDTO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	

}


