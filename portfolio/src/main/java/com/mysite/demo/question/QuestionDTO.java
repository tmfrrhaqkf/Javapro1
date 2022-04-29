package com.mysite.demo.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.demo.answer.AnswerDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
	private Integer id;
	private String subject;
	private String content;
	private LocalDateTime createDate;
	private List<AnswerDTO> answerList;
}