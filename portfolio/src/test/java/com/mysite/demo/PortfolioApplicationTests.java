package com.mysite.demo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.demo.question.Question;
import com.mysite.demo.question.QuestionRepository;
import com.mysite.demo.question.QuestionService;

@SpringBootTest
class PortfolioApplicationTests {

	@Autowired // 객체 주입 애너테이션 
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void contextLoads() {
	}

	@Test // 테스트 메서드임을 선언.
	void testJpa() {
		//  데이터 저장.
		//	Question q1 = new Question();
		//	q1.setSubject("아 졸렵다");
		//	q1.setContent("뭘해도 졸려운데 잠좀 깨는 비법좀");
		//	q1.setCreateDate(LocalDateTime.now());
		//	this.questionRepository.save(q1);
		//		
		//	Question q2 = new Question();
		//	q2.setSubject("오늘은 좀 피곤함");
		//	q2.setContent("커피가 소용이 없어요");
		//	q2.setCreateDate(LocalDateTime.now());
		//	this.questionRepository.save(q2);
		
		// 데이터 조회
//		List<Question> searchQuestion = this.questionRepository.findAll();
//		// assertEquals(기대값, 실제값) : 기대값이 실제값과 동일한지 체크. 
//		// 만약 결과가 false면 조회안됨.
//		assertEquals(2, searchQuestion.size());
//		
//		Question q = searchQuestion.get(0);
//		assertEquals("아 졸렵다", q.getSubject());
		
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()) {
//			Question q = oq.get();
//			assertEquals("아 졸렵다", q.getSubject());
//		}
		
//		update 테이블명
//		set 컬럼명 = 값 // 바꿀값
//		where 컬럼명 = 값 // 기준값
		
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정 테스트");
//		q.setContent("잘 바꼈는가");
//		this.questionRepository.save(q);

		for(int i=1; i<=200; i++) {
			String subject = String.format("테스트데이터:[%03d]", i);
			String content = "그런거없다 ㅋㅋ";
			this.questionService.create(subject, content, null);
		}
		
	}

}
