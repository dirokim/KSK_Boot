package com.winter.app.member;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO implements UserDetails {
	@NotBlank(message = "꼭 입력하세요", groups= {MemberJoinGroup.class,MemberUpdateGroup.class})
	private String username;
	@NotBlank(groups = MemberJoinGroup.class)
	private String password;
	private String passwordCheck;
	private String phone;
	@Email(groups = {MemberJoinGroup.class,MemberUpdateGroup.class})
	private String email;
	private String address;
	private String name;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// 계정이 완료 됐냐 ?
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠기진 않았는지 ?
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 의 유효기간
		return true;
	}
	@Override
	public boolean isEnabled() {
		// 이 계정이 사용 가능한지
		return true;
	}
	
	
	
	
	
}
