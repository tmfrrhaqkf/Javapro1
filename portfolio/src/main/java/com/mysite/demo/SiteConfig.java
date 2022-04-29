package com.mysite.demo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 부트의 설정을 담당하는 클래스라는 의미 부여.
public class SiteConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
