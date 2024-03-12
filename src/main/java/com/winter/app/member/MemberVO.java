package com.winter.app.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	private List<RoleVO> roleVOs;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVOs:roleVOs) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVOs.getRolename());
			authorities.add(g);
		}
		return authorities;
	}

	
	
	
	
	
}
