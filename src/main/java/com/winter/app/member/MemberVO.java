package com.winter.app.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	private String phone;
	@Email
	private String email;
	private String address;
	private String name;
}
