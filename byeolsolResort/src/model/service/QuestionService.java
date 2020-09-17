package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.dto.Question;
import model.mapper.AnswerMapper;
import model.mapper.QuestionMapper;
import model.view.QuestionView;

@Service("questionService")
public class QuestionService {

	private static final int QUESTION_COUNT_PER_PAGE = 5;

	@Autowired
	QuestionMapper questionMapper;

	public QuestionView getQuestionView(int pageNum, String writer) {
		QuestionView questionView = null;

		int firstRow = 0;
		List<Question> questionList = null;
		// 작성자의 question의 수
		int questionCnt = questionMapper.countQuestion(writer);

		if (questionCnt > 0) {
			firstRow = (pageNum - 1) * QUESTION_COUNT_PER_PAGE;
			questionList = questionMapper.selectQuestionListByWriterWithLimit(writer, firstRow,
					QUESTION_COUNT_PER_PAGE);
		} else {
			pageNum = 0;
		}

		questionView = new QuestionView(questionCnt, pageNum, firstRow, QUESTION_COUNT_PER_PAGE, questionList);
		return questionView;
	}

	public void addQuestion(Question question) {
		questionMapper.insertQuestion(question);
	}

	public Question selectQuestionById(int id) {
		return questionMapper.selectQuestion(id);
	}

	public void updateQuestion(Question question) {
		questionMapper.updateQuestion(question);
	}

	@Autowired
	AnswerMapper answerMapper;

	@Transactional
	// question을 삭제 할 때 그 question의 답변도 같이 삭제 
	public void deleteQuestion(int id) {
		Question question = questionMapper.selectQuestion(id);
		answerMapper.deleteAnswerByQuestionId(id);
		questionMapper.deleteQuestion(id);
	}
	
	// 페이지 번호를 받아 quesion을 가져옴 
	public QuestionView getQuestionViewWithState(int pageNum) {
		QuestionView questionView = null;
		int firstRow = 0;
		List<Question> questionList = null;
		// state에 따른 question의 수
		int questionCnt = questionMapper.countQuestionByState();
		if (questionCnt > 0) {
			firstRow = (pageNum - 1) * QUESTION_COUNT_PER_PAGE;
			questionList = questionMapper.selectQuestionListByStateWithLimit(firstRow, QUESTION_COUNT_PER_PAGE);
		} else {
			pageNum = 0;
		}
		questionView = new QuestionView(questionCnt, pageNum, firstRow, QUESTION_COUNT_PER_PAGE, questionList);
		return questionView;
	}

	public boolean nullCheck(Question question) {
		if(question.getDivision()!=null && question.getMessage()!=null && question.getTitle() != null
				&& !question.getDivision().trim().equals("") && !question.getMessage().trim().equals("") && !question.getTitle().trim().equals("")) {
			return true;
		}else return false;
	}
	
}
