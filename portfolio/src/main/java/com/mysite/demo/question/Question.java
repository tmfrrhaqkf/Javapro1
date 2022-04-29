package com.mysite.demo.question;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mysite.demo.answer.Answer;
import com.mysite.demo.user.SiteUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// id, subject, content, createDate
// 2022-04-19 조준모 question 테이블 생성
@Getter
@Setter
@Entity // JPA가 해당 클래스를 엔티티로 인식하게끔 처리하는 애너테이션
@Table(name="question")
@Data
@Builder
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor 
public class Question {

	@Id // 해당 속성을 기본키로 지정할때 사용
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// generatedValue = 값을 세팅하지않아도 1씩 자동으로 증가.
	// GenerationType.IDENTITY = 해당 컬럼만의 독립적인 시퀀스 생성.
	
	
	@Column(length = 200) // 해당 컬럼의 세부 속성 정의시 사용.
	private String subject;

	@Column(columnDefinition = "TEXT") // 글자제한 x
	private String content; 
	
	private LocalDateTime createDate;
	
	//cascadeType.Remove : 해당row 삭제시 연결되어있는 데이터 전부삭제
	// -> 질문 삭제하면 질문에 달렸던 답변 내용들 전부 삭제.
	// mappedBy : 참조 엔티티 속성명
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
	
}
