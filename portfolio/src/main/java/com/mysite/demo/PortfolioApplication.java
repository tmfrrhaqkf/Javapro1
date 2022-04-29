package com.mysite.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

}

// developmentOnly 모드(dependency 추가)
// -> 개발환경에서 적용하는 모드 
//    컨트롤러단 변경을 즉시 적용하거나 운영환경과의 구분을 위해 적용

// 롬복 관련 라이브러리(dependency)
// complieOnly
// 해당 라이브러리가 컴파일 단계에서만 필요한 경우에 사용

// annotationProcessor
// 컴파일 단계에서 어노테이션을 분석하고 처리하기위해 사용.





