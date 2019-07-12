package web.service.face;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Recommend;
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
	 * @param context - 파일업로드 위한 서블릿 컨텍스트 정보 (07.11 추가)
	 * @param fileupload - 업로드 하려는 파일 (07.11)
	 */
	public void write(Board board, MultipartFile fileupload, ServletContext context);

	/**
	 * 게시글 수정
	 * 
	 * @param board - 수정하려는 게시글의 정보
	 * @param context 
	 * @param fileupload 
	 */
	public void boardUpdate(Board board, MultipartFile fileupload, ServletContext context);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제하려는 게시글 객체
	 */
	public void boardDelete(Board board);

	/**
	 * 게시글 뷰 - 업로드된 첨부파일 보여주기
	 * 
	 * @param board - 게시글 번호
	 * @return Boardfile - 업로드된 첨부파일 객체
	 */
	public Boardfile getBoardFile(Board board);

	/**
	 * 파일 다운로드 - 파일번호 이용하여 파일 가져오기
	 * 
	 * @param boardfile - 파일 번호
	 * @return Boardfile - 조회된 객체
	 */
	public Boardfile getFile(Boardfile boardfile);

	/**
	 * 내가 어떠한 게시글을 추천했는지 체크
	 * 
	 * @param recommend
	 * @return
	 */
	public boolean checkRecommend(Recommend recommend);

	/**
	 * 추천 상태에 따라 추천/ 추천 취소
	 * 
	 * @param recommend - 추천/추천 취소 하려는 board_no, id 
	 * @return boolean - 추천한 상태면 false
	 */
	public boolean recommend(Recommend recommend);

	/**
	 * 게시글당 전체 추천수 가져오기
	 * 
	 * @param recommend - 게시글 번호
	 * @return int - 게시글 번호에 해당하는 추천수 
	 */
	public int getTotalCntRecommend(Recommend recommend);

	
}
