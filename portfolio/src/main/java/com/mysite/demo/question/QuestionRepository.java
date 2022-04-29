package com.mysite.demo.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository 
extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question>{
// 리포지토리 
// 데이터 처리를 위해 실제 데이터베이스와 연동하는 인터페이스
// 데이터베이스 테이블에 접근하는 메서드를 사용하기 위한 인터페이스
// CRUD를 어떻게 처리할지 정의하는 계층
		Question findBySubject(String subject);
		Question findBySubjectAndContent(String subject, String content);
		List<Question> findBySubjectLike(String subject);
		Page<Question> findAll(Pageable pageable);
		//Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
}