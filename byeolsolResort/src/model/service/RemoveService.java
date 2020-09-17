package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.dto.Remove;
import model.mapper.RemoveMapper;
import model.view.RemoveView;

@Service("removeService")
public class RemoveService {
	private static final int REMOVE_COUNT_PER_PAGE = 10;
	

	@Autowired
	RemoveMapper removeMapper;
	
	public RemoveView getRemoveView(int pageNum) {
		RemoveView removeView = null;
		
		int firstRow = 0;
		List<Remove> removeList = null;
		// remove의 수
		int removeCnt = removeMapper.countRemove();
		
		if (removeCnt>0) {
			firstRow = (pageNum-1)*REMOVE_COUNT_PER_PAGE;
			removeList = removeMapper.selectRemoveListWithLimi(firstRow, REMOVE_COUNT_PER_PAGE);
		}else {
			pageNum = 0;
		}
		removeView = new RemoveView(removeCnt, pageNum, firstRow, REMOVE_COUNT_PER_PAGE, removeList);
		
		return removeView;
	}
	
	public void removeRemove(int id) {
		removeMapper.deleteRemove(id);
	}
	
	
}
