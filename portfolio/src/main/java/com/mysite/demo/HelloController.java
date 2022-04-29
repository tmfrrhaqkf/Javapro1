package com.mysite.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	// URL 부여.(URL 요청 발생시 실행할 메서드와 url정의)
	@RequestMapping("/hell")
	@ResponseBody // 아래의 메서드의 응답 결과가 문자열 그 자체다.
	public String hello() {
		return "지옥에 온걸 환영한다 ㅋㅋㅋ";
			
	}
	// 문제 
	// url명 : quiz를 갖는 메서드 quiz를 만드시고
	// 해당 페이지를 띄웠을때 화면에 퀴즈 정답 입니다 라는 메세지를 띄워보세요.
	@RequestMapping("/quiz")
	@ResponseBody // 아래의 메서드의 응답 결과가 문자열 그 자체다.
	public String quiz() {
		return "퀴즈 정답 입니다";
			
	}
	
	
	
}
