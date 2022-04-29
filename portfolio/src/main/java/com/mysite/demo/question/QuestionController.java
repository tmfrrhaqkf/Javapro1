package com.mysite.demo.question;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.demo.answer.AnswerForm;
import com.mysite.demo.user.SiteUser;
import com.mysite.demo.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor // final 속성이 붙은 생성자를 자동으로 생성하는역할
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="kw", defaultValue="") String kw) {
		// db에서 question 테이블의 조회결과를 questionList 인스턴스에 받아옴.
		Page<Question> paging =this.questionService.getList(page, kw);
		// view단으로 데이터를 전송하기위한 준비.
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "question_list";
		// 템플릿에서 페이징 처리시 필요한 속성들
		// paging.isEmpty : 페이지 존재 여부
		// paging.totalElements : 전체 게시물 개수
		// paging.totalPages : 전체 페이지 개수
		// paging.size : 페이지당 보여줄 게시물 개수
		// paging.number : 현재 페이지 번호
		// paging.hasPrevious :이전 페이지 존재 여부
		// paging.hasNext : 다음 페이지 존재 여부
	}
	//숙제
	//answercontroller 만들어보기
	// url : answer/list
	// 테스트 페이지 출력.
	
	@RequestMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	//valid : 만들어둔 form 클래스와 연동.
	//주의사항 : form에 만들어둔 필드 명칭과 동일.
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, 
			BindingResult bindingResult, Principal principal) {
		//만약 오류가 발생하면 다시 폼을 작성하는 화면을 랜더링 하는 조건.
		if(bindingResult.hasErrors()) {

			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, 
			BindingResult bindingResult,
			@PathVariable("id") Integer id,
			Principal principal) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.questionService.modify(question, questionForm.getSubject(),
				questionForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable Integer id) {
    	Question question = this.questionService.getQuestion(id);
    	if(!question.getAuthor().getUsername().equals(principal.getName())) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
    	}
    	this.questionService.delete(question);
    	return "redirect:/";
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable Integer id) {
    	Question question = this.questionService.getQuestion(id);
    	SiteUser siteUser = this.userService.getUser(principal.getName());
//    	if(!question.getAuthor().getUsername().equals(principal.getName())) {
//    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//    	}
    	this.questionService.vote(question, siteUser);
    	return String.format("redirect:/question/detail/%s", id);
    }
    
    
    
	
	// 자주 사용하는 타임리프의 속성
	
	// 1. 분기문 속성
	// th:if="${question != null}"
	// 2. 반복문 속성
	// th:each="question , loop : ${questionList}"
	// loop.index - 반복 순서, 0부터 1씩 증가
	// loop.count - 반복 순서, 1부터 1씩 증가
	// loop.size - 반복 객체의 요소 갯수 
	// loop.current - 현재 가져온 객체.
	// 3. 텍스트 속성.
	// 가져온 값 자체를 출력
	
	
	// 문제
	// 답변 데이터
	// view
	
	// 서비스단추가
	// why? -> controller로 다 처리 가능한데?
	// 1. 보안
	// 컨트롤러 <-> view 
	// 서비스단이 존재한다면.
	// 컨트롤러의 제어권이 뺏기더라도 리포지토리 접근은 막을수 있음.
	
	// 2. 엔티티, DTO 처리.
	
	// DTO(Data Transfer Object)
	// 데이터 통로
	
	// url 프리픽스
	// 해당 컨트롤러를 통한 url 요청을 받을시
	// 공통으로 들어갈 url을 설정하는 기법.
	
	// spring boot validation annotation
	// @Size  : 문자 길이 제한
	// @NotNull : Null을 허용하지 않는다.
	// @NotEmpty : Null or 빈 문자열 허용x
	// @Past : 과거 날짜만 등록가능
	// @Future : 미래 날짜만 등록가능
	// @FutureOfPresent : 오늘날짜와 미래만 가능
	// @Max : 최대값
	// @Min : 최소값
	// @Pattern : 정규식 검증
	
	// 스프링 시큐리티
	// 스프링 기반 프로젝트의 인증과 권한을 담당하는 스프링 아래의 프레임워크
	
	// 인증 -> 로그인을 의미
	// 권한 -> 인증된 사용자가 어떤것을 할수있는지를 의미

	// 게시물 번호 공식
	// 번호 = 전체 게시물 개수 - (현재 페이지 * 페이지당 게시물 개수) - 나열 인덱스
	
	
	// 수정과 삭제.

	
	
	
	
}