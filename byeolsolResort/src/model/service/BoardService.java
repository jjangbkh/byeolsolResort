package model.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import model.dto.Board;
import model.mapper.BoardMapper;
import model.mapper.CommentMapper;
import model.view.BoardView;

@Service("boardService")
public class BoardService {
	// 페이지 당 보여줄 게시글의 수
	private static final int BOARD_COUNT_PER_PAGE = 5;
	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	// board 전체 가져오기
	public List<Board> getBoardList(){
		return boardMapper.selectBoardList();
	}
	
	// 유저의 아이디를 갖고 리스트 찾기
	public List<Board> selectBoardListByUserId(String userId) {
		return boardMapper.selectBoardListByUserId(userId);
		
	}
	
	// board 추가
	public void addBoard(Board board) {
		boardMapper.insertBoard(board);
	}

	// 수정
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}
	
	// id로 조회
	public Board selectBoard(int id) {
		return boardMapper.selectBoard(id);
	}
	
	@Autowired
	FtpService ftpService;
	
	
	public String deleteBoard(int id , String userId) {
		Board b = boardMapper.selectBoard(id);	// 삭제할 board 가져와서 유저 아이디 매칭 & 관리자인지 확인
		if(b.getUserId().equals(userId) || userId.equals("admin")) {
			commentMapper.deleteBoardComment(id); 	// 댓글 삭제
			boardMapper.deleteBoard(id);		// 게시글 삭제
			String Time = "";
			if(b.getFirstPath()!=null) {			// 게시글의 이미지 패스가 비어 있지 않다면 ftp 서버에 있는  해당 이미지 삭제
			 Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
					b.getFirstPath().lastIndexOf('/'));
			 ftpService.ftpdelete(b.getFirstPath(), Time); // ftp서버에 있는 이미지 삭제
			}
			else if(b.getSecondPath()!=null) {
				Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
						b.getFirstPath().lastIndexOf('/'));
				ftpService.ftpdelete(b.getSecondPath(), Time);
			}else if(b.getThirdPath()!=null) {
				Time = b.getFirstPath().substring(b.getFirstPath().indexOf('/', b.getFirstPath().indexOf("board")) + 1, // 이벤트 추가시 이미지 저장 폴더의 이름
						b.getFirstPath().lastIndexOf('/'));
				ftpService.ftpdelete(b.getThirdPath(), Time);
			}
			return "삭제 완료";
		}else {
			return "게시글을 삭제할 권한이 없음";
		}
		
	}
	
	// 이미지를 ftp 서버에 올리고 게시글의 path에 경로 셋팅
	public Board imgUpAndSetPath(Board board , int i , MultipartFile uploadFile, String addTime, String count) {
		
		// ftp에 img올리기
		String isSuccess = ftpService.ftpImg(uploadFile, addTime, count);
		
		switch (i) {
		case 0:	
			// 이미지 올리기가 성공이면 board에 경로 셋팅
			if(isSuccess.equals("성공")) {
			board.setFirstPath("https://gyonewproject.000webhostapp.com/byeolsolResort/board/" + addTime + "/"
										+ "first" + uploadFile.getOriginalFilename());
			}else {
				board.setFirstPath(null);
			}
			break;
		case 1:	
			if(isSuccess.equals("성공")) {
			board.setSecondPath("https://gyonewproject.000webhostapp.com/byeolsolResort/board/" + addTime + "/"
										+ "second" + uploadFile.getOriginalFilename());
			}else {
				board.setSecondPath(null);
			}
			break;
		case 2:	
			if(isSuccess.equals("성공")) {
			board.setThirdPath("https://gyonewproject.000webhostapp.com/byeolsolResort/board/" + addTime + "/"
										+ "third" + uploadFile.getOriginalFilename());
			}else {
				board.setThirdPath(null);
			}
			break;	
		}
		
		return board;
	}
	
	
	// adminView 를 가져오기
	public BoardView getAdminBoardView(int pageNum) {
		
		BoardView boardView = null;
		int firstRow = 0;
		// admin board의 수 가져오기 
		List<Board> boardList = null;
		int boardCnt = boardMapper.countAdminBoard();
		if(boardCnt>0) {
			firstRow = (pageNum-1)*BOARD_COUNT_PER_PAGE;
			boardList = boardMapper.selectBoardListWithAdminPage(firstRow,BOARD_COUNT_PER_PAGE);
		}else {
			pageNum=0;
		}
		
		boardView = new BoardView(boardCnt, pageNum, firstRow, BOARD_COUNT_PER_PAGE, boardList);
		
		return boardView;
	}
	
	public BoardView getView(int pageNum) {
		BoardView boardView = null;
		int firstRow = 0;
		List<Board> boardList = null;
		int boardCnt = boardMapper.countBoard(); 
		if(boardCnt>0) {
			firstRow = (pageNum-1)*BOARD_COUNT_PER_PAGE;
			boardList = boardMapper.selectBoardListWithPage(firstRow, BOARD_COUNT_PER_PAGE);
		}else {
			pageNum=0;
		}
		int endRow = firstRow+BOARD_COUNT_PER_PAGE;
		
		boardView = new BoardView(boardCnt, pageNum, firstRow, BOARD_COUNT_PER_PAGE, boardList);
		
		return boardView;
	}
	
	public List<Board> getAdBoard(){
		return boardMapper.selectBoardListAdmain();
	}
	
	// 이미지 타입 확인
	public boolean checkImg(MultipartFile uploadFile) {
		String fileType = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf('.'),
				uploadFile.getOriginalFilename().length());
		fileType = fileType.toLowerCase(); // 이미지 소문자로 변경 후 파일 타입 체크
		if (fileType.equals(".jpg") || fileType.equals(".png") || fileType.equals(".jpeg")) {
			return true;
		} else
			return false;

	}
	
	
	public boolean nullCheck(Board board) {
		if(board.getTitle().trim() != null && board.getContent().trim() != null && !board.getTitle().trim().equals("") && !board.getContent().trim().equals("")) {
			return true;
		}else return false;
	}
	
}
