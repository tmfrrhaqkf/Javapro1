package com.mysite.demo.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.demo.DataNotFoundException;
import com.mysite.demo.question.Question;
import com.mysite.demo.question.QuestionDTO;
import com.mysite.demo.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	public Answer create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
		return answer;
	}
	
	public Answer getAnswer(Integer id) {
		//optional: 해당 데이터의 유무를 검사하는 컬렉션 타입.
		Optional<Answer> answer = this.answerRepository.findById(id); 
		if(answer.isPresent()) {
			return answer.get();
		}else {
			throw new DataNotFoundException("없는데요 ㅋㅋ");
		}
		
	}
	
	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
	

	
}