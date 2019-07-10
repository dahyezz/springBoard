package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.face.BoardDao;
import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardDao boardDao;
	
	@Override
	public List<Board> list(Paging paging) {
		return boardDao.selectAll(paging);
	}
	
	@Override
	public Paging getcurPage(String curPage) {
		
		int totalCount = boardDao.selectCntAll();
		int curpage = 0;
		
		if(curPage!=null && !"".equals(curPage)) {
			curpage = Integer.parseInt(curPage);
		} 
		
		Paging paging = new Paging(totalCount, curpage);
		
		return paging;
	}

	
	@Override
	public Board viewBoard(Board board) {
		
		boardDao.hit(board);
		
		return boardDao.selectBoardByBoardno(board);
	}
	
	@Override
	public void write(Board board) {
		boardDao.insertBoard(board);
	}
	
	@Override
	public void boardUpdate(Board board) {
		boardDao.updateBoard(board);
	}
	
	@Override
	public void boardDelete(Board board) {
		boardDao.deleteBoard(board);
	}
}
