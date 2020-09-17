package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Board;

public interface BoardMapper {

	public void insertBoard(Board board);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int id);
	
	public List<Board> selectBoardList();
	
	public Board selectBoard(int id);
	
	public int countBoard();
	
	public List<Board> selectBoardListWithPage(@Param("firstRow")int firstRow,@Param("boardPerPageCount")int boardPerPageCount);
	
	public List<Board> selectBoardListByUserIdWithPage(@Param("firstRow")int firstRow,
			@Param("boardPerPageCount")int boardPerPageCount, @Param("userId")String userId);
	
	public List<Board> selectBoardListAdmain();
	
	public void deleteBoardByUserId(String userId);

	public List<Board> selectBoardListByUserId(String userId);

	public List<Board> selectBoardListWithAdminPage(@Param("firstRow")int firstRow, @Param("boardCountPerPage")int boardCountPerPage);
	
	public int countAdminBoard();
	
}
