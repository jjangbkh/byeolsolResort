package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Comment;

public interface CommentMapper {

	public void insertComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void deleteComment(int id);
	
	public Comment selectCommentWithId(int id);
	
	public List<Comment> selectCommentWithBoardId(int boardId);
	
	public void deleteBoardComment(int boardId);
	
	public int commentCount(int boardId);
	
	public List<Comment> commentListView(@Param("boardId") int boardId , @Param("firstRow")int firstRow, @Param("commentCountPerPage") int commentCountPerPage);

	public void deleteCommentByUserId(String userId);
}


