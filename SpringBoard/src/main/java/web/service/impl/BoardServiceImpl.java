package web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.dao.face.BoardDao;
import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Recommend;
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
	public void write(Board board, MultipartFile fileupload, ServletContext context) {
		
		//파일이 저장될 경로
		String storedPath = context.getRealPath("upload");
		
		//UUID
		String uId = UUID.randomUUID().toString().split("-")[4];
		
		//저장될 파일의 이름 (원본이름 + UUID)
		String name = fileupload.getOriginalFilename()+"_"+uId;
		
		//저장될 파일 객체
		File dest = new File(storedPath, name);
		
		//파일 저장
		try {
			fileupload.transferTo(dest); //실제 저장
		
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		//board 삽입
		boardDao.insertBoard(board);
		
		//DB에 저장(업로드 정보 기록)
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardno(board.getBoardno());
		boardfile.setOriginname(fileupload.getOriginalFilename());
		boardfile.setStoredname(name);
		boardfile.setFilesize((int)fileupload.getSize());
		
		boardDao.insertBoardFile(boardfile);
	}
	
	@Override
	public void boardUpdate(Board board, MultipartFile fileupload, ServletContext context) {

		if(fileupload.getOriginalFilename()!=null) {
			boardDao.deleteFile(board);
			
			//파일이 저장될 경로
			String storedPath = context.getRealPath("upload");
			
			//UUID
			String uId = UUID.randomUUID().toString().split("-")[4];
			
			//저장될 파일의 이름 (원본이름 + UUID)
			String name = fileupload.getOriginalFilename()+"_"+uId;
			
			//저장될 파일 객체
			File dest = new File(storedPath, name);
			
			//파일 저장
			try {
				fileupload.transferTo(dest); //실제 저장
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			//DB에 저장(업로드 정보 기록)
			Boardfile boardfile = new Boardfile();
			boardfile.setBoardno(board.getBoardno());
			boardfile.setOriginname(fileupload.getOriginalFilename());
			boardfile.setStoredname(name);
			boardfile.setFilesize((int)fileupload.getSize());
			
			boardDao.insertBoardFile(boardfile);
		}
		
		boardDao.updateBoard(board);
		
	}
	
	@Override
	public void boardDelete(Board board) {
		boardDao.deleteFile(board);
		boardDao.deleteBoard(board);
	}
	
	@Override
	public Boardfile getBoardFile(Board board) {
		return boardDao.selectBoardfileByBoardno(board);
	}
	
	@Override
	public Boardfile getFile(Boardfile boardfile) {
		return boardDao.selectBoardfileByFileno(boardfile);
	}
	
	@Override
	public boolean checkRecommend(Recommend recommend) {
		
		if(boardDao.selectCntRecommend(recommend) > 0)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean recommend(Recommend recommend) {
		
		if(boardDao.selectCntRecommend(recommend) > 0) {
			boardDao.deleteRecommend(recommend);
			
			return false;
		} else {
			boardDao.insertRecommend(recommend);
			
			return true;
		}
		
	}
	
	@Override
	public int getTotalCntRecommend(Recommend recommend) {
		return boardDao.selectCntAllRecommend(recommend);
	}
}
