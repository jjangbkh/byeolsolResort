package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Remove;

public interface RemoveMapper {
	
	public void insertRemove(Remove remove);
	
	public void updateRemoveState(@Param("id")int id, @Param("state")String state);
	
	public void deleteRemove(int id);
	
	public List<Remove> selectRemoveListWithLimi(@Param("firstRow")int firstRow, @Param("removeCountPerPage")int removeCountPerPage);
	
	public List<Remove> selectRemoveList();
	
	public Remove selectRemoveById(int id);
	
	public int countRemove();
	
}
