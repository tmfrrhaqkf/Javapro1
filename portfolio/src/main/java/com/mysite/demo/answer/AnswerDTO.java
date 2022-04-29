package com.mysite.demo.answer;

import java.time.LocalDateTime;

import com.mysite.demo.question.QuestionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {
	private Integer id;
	private String content;
	private LocalDateTime createDate;
	private QuestionDTO question;
}