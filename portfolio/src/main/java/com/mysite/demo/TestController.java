package com.mysite.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
	@RequestMapping("/test")
	public String test() {
		return "test";
			
	}
}

// 웹 서비스를 진행한다 -> 데이터가 생성될 확률이 높다.
// 데이터를 저장, 조회, 수정 하는 CRUD 기능이 필요.
// 웹 서비스는 데이터 처리시 대부분 DB를 이용
// DB사용 -> 쿼리사용.

// 하지만 ORM을 이용해서 자바 문법으로 DB를 다룰수 있음.
// 테이블생성 -> 테이블명 question
//Question q1 = new Question();
//q1.setSubject("안녕하슈"); insert into question (subject,content) values()
//q1.setContent("가입 인사 ㅎㅇ");
//this.questionRepository.save(q1);

// ORM의 장단점
// 장점
// 1. DB에대한 지식 없이도 DB에 데이터를 저장하거나 불러올수 있다.
// 2. DB 종류를 고려할 필요가 없다.
// 3. 개발자가 달라도 통일된 쿼리를 작성할수 있다. -> 오류 발생률 감소.

// 단점
// 1. 복잡한 쿼리 작성시에는 오히려 더 어렵다.
//   -> 작업속도의 저하.

// JPA(Java Persistence API) 
//  -> ORM의 표준 인터페이스
//  -> 실제클래스로 하이버네이트 사용. 









