package model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.dto.Board;
import model.dto.Comment;
import model.mapper.CommentMapper;
import model.view.BoardView;
import model.view.CommentView;

@Service("commentService")
public class CommentService {
	
	private static final int COMMENT_COUNT_PER_PAGE = 5;

	@Autowired
	CommentMapper commentMapepr; 
	
	// comment 추가 하기
	public void addComment(Comment comment) {
		commentMapepr.insertComment(comment);
	}
	
	// comment 수정하기
	public void updateComment(Comment comment) {
		commentMapepr.updateComment(comment);
	}
	
	// comment 삭제 하기
	public String deleteComment(int id, String userId) {
		Comment comment = commentMapepr.selectCommentWithId(id);
		
		// 관리자 이거나 글쓴이라면 삭제
		if(userId.equals("admin") || comment.getUserId().equals(userId)) {
			commentMapepr.deleteComment(id);
			return "삭제";
		}else return "권한이 없습니다";
		
	}
	// 게시글 하나의 댓글 view
	public CommentView getView(int pageNum , int boardId) {
		CommentView commentView = null;
		int firstRow = 0;
		List<Comment> commentList = null;
		// 이 게시글의 comment 수 가져오기
		int commentCnt = commentMapepr.commentCount(boardId); 
		if(commentCnt>0) {
			firstRow = (pageNum-1)*COMMENT_COUNT_PER_PAGE;
			commentList = commentMapepr.commentListView(boardId, firstRow, COMMENT_COUNT_PER_PAGE);
		}else {
			pageNum=0;
		}
		commentView = new CommentView(commentCnt, pageNum, firstRow, COMMENT_COUNT_PER_PAGE, commentList);
		return commentView;
	}
	
	public boolean nullCheck(Comment comment) {
		if(comment.getMessage()!=null && comment.getBoardId()>0 && !comment.getMessage().trim().equals("")) {
			return true;
		}else return false;
	}
	
	
	public Comment getCommentWithId(int id) {
		return commentMapepr.selectCommentWithId(id);
	}
	
	
}
