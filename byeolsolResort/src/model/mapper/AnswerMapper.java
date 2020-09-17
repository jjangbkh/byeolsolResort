package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Answer;

public interface AnswerMapper {
	
	public void insertAnswer(Answer answer);
	
	public void updateAnswer(Answer answer);
	
	public void deleteAnswer(int id);
	
	public List<Answer> selectAnswerByQuestionIdWithLimit(@Param("questionId")int questionId, @Param("firstRow")int firstRow, @Param("answerCountPerPage")int answerCountPerPage);
	
	public int countAnswer(int questionId);
	
	public Answer selectAnswerById(int id);
	
	public void deleteAnswerByQuestionId(int questionId);

	public void deleteAnswerByWriter(String writer);
	
}
