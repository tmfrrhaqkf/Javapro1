package com.mysite.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mysite.demo.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 질문, 답변 로그인 안했을시 강제로 로그인 페이지 이동
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserSecurityService userSecurityService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// 인증되지 않은 요청 허락(로그인을 하지 않아도 접근할수 있는 페이지 지정)
		// CSRF(cross site request forgery) : 취약점 공격 방지 기술.
		// -> 스프링 시큐리티가 CSEF 토큰값을 세션을 통해 발행하고 웹페이지에서는
		// 폼 전송시에 해당토큰을 함께 전송해 실제 웹 페이지에서
		// 작성된 데이터가 전달되는지를 검증하는 기술
		http.authorizeRequests().antMatchers("/**").permitAll()
		.and()
			.csrf().ignoringAntMatchers("/h2-console/**")//ignore 처리
		.and()
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and() // 스프링 시큐리티 로그인 설정 담당부분
			.formLogin()
			.loginPage("/user/login")
			.defaultSuccessUrl("/")
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
}
