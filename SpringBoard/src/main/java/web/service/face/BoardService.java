package web.service.face;

import java.util.List;

import web.dto.Board;
import web.util.Paging;

public interface BoardService {
	
	/**
	 * 현재 페이지 번호 알아오기
	 * 
	 * @param curPage - 파라미터로 넘겨준 페이지 번호
	 * @return Paging - 페이징 객체
	 * 
	 * 2019.07.09 강사님코드 보고 수정 필요
	 */
	public Paging getcurPage(String curPage);

	/**
	 * 게시글 리스트 불러오기
	 * 
	 * @param paging - 페이징 객체
	 * @return List<Board> - 게시글 리스트
	 */
	public List<Board> list(Paging paging);


	/**
	 * 게시글 조회
	 * 
	 * @param board - 조회하려는 게시글 번호
	 * @return Board -  조회된 게시글 객체
	 */
	public Board viewBoard(Board board);

	/**
	 * 글쓰기
	 * 
	 * @param board - 업로드하려는 게시글 객체
	 */
	public void write(Board board);

	/**
	 * 게시글 수정
	 * 
	 * @param board - 수정하려는 게시글의 정보
	 */
	public void boardUpdate(Board board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제하려는 게시글 객체
	 */
	public void boardDelete(Board board);

	
}
