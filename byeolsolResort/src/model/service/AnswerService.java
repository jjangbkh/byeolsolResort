package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dto.Answer;
import model.dto.Question;
import model.mapper.AnswerMapper;
import model.mapper.QuestionMapper;
import model.view.AnswerView;

@Service("answerService")
public class AnswerService {
	// 페이지당 보여줄 답변의 수를 상수 5로 초기화
	private static final int ANSWER_COUNT_PER_PAGE = 5;
	
	// answer를 db에 crud할 맵퍼를 autowired로 컴파일 시 자동 매칭되게 함
	@Autowired
	AnswerMapper answerMapper;
	
	// question을 db에 crud할 맵퍼를 autowired로 컴파일 시 자동 매칭되게 함
	@Autowired
	QuestionMapper questionMapper;
	
	// 현재 페이지 넘버와 questionId를 인자로 갖고 있는 AnswerView를 반환 하는 메서드
	public AnswerView getAnswerView(int pageNum, int questionId) {
		AnswerView answerView = null;
		int firstRow = 0;
		List<Answer> answerList = null;
		// answer의 총 수를 answerMapper를 통해 db에서 select count를 함
		int answerCnt = answerMapper.countAnswer(questionId);
		// answer의 수 가 0보다 크다면
		if(answerCnt>0) {
			// firstRow 를 (현재 페이지 -1) * 페이지당 보여 줄 answer의 수 
			firstRow = (pageNum-1)*ANSWER_COUNT_PER_PAGE;
			answerList = answerMapper.selectAnswerByQuestionIdWithLimit(questionId, firstRow, ANSWER_COUNT_PER_PAGE);
		}else {
			pageNum = 0;
		}
		answerView = new AnswerView(answerCnt, pageNum, firstRow, ANSWER_COUNT_PER_PAGE, answerList);
		return answerView;
	}

	public void addAnswer(Answer answer) {
		// 답변을 추가 하기 위해 답변을 달 question을 가져옴
		Question question = questionMapper.selectQuestion(answer.getQuestionId());
		if(answer.getWriter().equals("admin")) { // 만약 답변의 작성자가 관리자 이면 게시글의 상태를 cea로
			questionMapper.updateQuestionByIdWithState(question.getId(),"cea");
		}else if(answer.getWriter().equals(question.getWriter())) {
			// 아니라면 yet으로
			questionMapper.updateQuestionByIdWithState(question.getId(), "yet");
		}
		// answer db에 추가
		answerMapper.insertAnswer(answer);
	}
	
	// answer를 가져id로 가져오기
	public Answer getAnswerById(int id) {
		return answerMapper.selectAnswerById(id);
	}

	// answer 수정
	public void updateAnswer(Answer answer) {
		answerMapper.updateAnswer(answer);
	}

	// 삭제
	public void deleteAnswer(int id) {
		answerMapper.deleteAnswer(id);
	}
	
	// 질문이 삭제 되면 답변도 삭제 해야함
	public void deleteAnswerByQuestionId(int questionId) {
		answerMapper.deleteAnswerByQuestionId(questionId);
	}
	
	public boolean nullCheck(Answer answer) {
		if(answer.getMessage().trim()!=null && !answer.getMessage().trim().equals("")) {
			return true;
		}else return false;
	}
	
}
