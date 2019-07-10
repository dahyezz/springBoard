package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.util.Paging;

public interface BoardDao {

	public List<Board> selectAll(Paging paging);

	public int selectCntAll();

	public Board selectBoardByBoardno(Board board);

	/**
	 * 조회수 업데이트
	 * 
	 * @param board - 게시글 번호 있는 객체
	 */
	public void hit(Board board);

	public void insertBoard(Board board);

	public void updateBoard(Board board);

	public void deleteBoard(Board board);
	
	

}
