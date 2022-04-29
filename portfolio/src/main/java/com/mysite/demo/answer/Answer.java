package com.mysite.demo.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mysite.demo.question.Question;
import com.mysite.demo.user.SiteUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="answer")
@Data
@Builder
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
public class Answer {
	@Id // 해당 속성을 기본키로 지정할때 사용
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// generatedValue = 값을 세팅하지않아도 1씩 자동으로 증가.
	// GenerationType.IDENTITY = 해당 컬럼만의 독립적인 시퀀스 생성.
	
	@Column(columnDefinition = "TEXT") // 글자제한 x
	private String content;
	
	private LocalDateTime createDate;
	@ManyToOne // 외래키 생성.( Ans의 경우는 Question에 여러개가 붙을수 있어
							// ManyToOne으로 생성.
	private Question question;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
}