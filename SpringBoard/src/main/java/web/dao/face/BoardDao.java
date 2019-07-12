package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Recommend;
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

	public int insertBoard(Board board);

	public void updateBoard(Board board);

	public void deleteBoard(Board board);

	/**
	 * 파일 insert
	 * 
	 * @param boardfile
	 */
	public void insertBoardFile(Boardfile boardfile);

	public Boardfile selectBoardfileByBoardno(Board board);

	public Boardfile selectBoardfileByFileno(Boardfile boardfile);

	public void deleteFile(Board board);

	public int selectCntRecommend(Recommend recommend);

	public void insertRecommend(Recommend recommend);

	public void deleteRecommend(Recommend recommend);

	public int selectCntAllRecommend(Recommend recommend);
	
	

}
