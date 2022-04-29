package com.mysite.demo.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 4, max=20)
	@NotEmpty(message="사용자 ID는 필수임")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수임")
	private String password1;
	@NotEmpty(message="비밀번호 확인은 필수임")
	private String password2;
	
	@NotEmpty(message="이메일은 필수임")
	@Email
	private String email;
}
