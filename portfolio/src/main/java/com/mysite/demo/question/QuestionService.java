package com.mysite.demo.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mysite.demo.DataNotFoundException;
import com.mysite.demo.answer.Answer;
import com.mysite.demo.user.SiteUser;

import javassist.expr.NewArray;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper;
	
	//question 객체를 questionDTO로 변경.
//	private QuestionDTO of(Question question) {
//		return modelMapper.map(question, QuestionDTO.class);
//	}
//	
//	
//	public List<QuestionDTO> getList(){
//		List<Question> questionList = this.questionRepository.findAll();
//		List<QuestionDTO> questionDTOList = questionList.stream().map(q -> of(q))
//				.collect(Collectors.toList());
//		return questionDTOList;
//		
//	}
	public Page<Question> getList(int page, String kw){
		// page 객체(파라미터)는 해당 페이지의 정보를 받아오는 변수(조회할 페이지 번호)
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.questionRepository.findAll(spec, pageable);
		
	}
	//detail 조회 메서드
	public Question getQuestion(Integer id) {
		//optional: 해당 데이터의 유무를 검사하는 컬렉션 타입.
		Optional<Question> question = this.questionRepository.findById(id); 
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("없는데요 ㅋㅋ");
		}
		
	}
	
	// 질문등록 메서드
	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		this.questionRepository.save(q);
	}
	
	//질문 수정 메서드
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}
	//추천
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
	
    private Specification<Question> search(String kw) {
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }
		


	
	
}