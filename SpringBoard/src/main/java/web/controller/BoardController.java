package web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import web.dto.Board;
import web.dto.Boardfile;
import web.dto.Recommend;
import web.service.face.BoardService;
import web.util.Paging;
 
@Controller 
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired ServletContext context; // 파일 업로드 처리 위한 context 정보
	@Autowired BoardService boardService;
	
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public void getList(Model model, String curPage) {
 
		Paging paging = boardService.getcurPage(curPage);
		
		List<Board> boardList = boardService.list(paging);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value="/board/view", method=RequestMethod.GET)
	public void view(Board board, Boardfile boardfile, Recommend recommend, Model model,HttpSession session) {
		
		board = boardService.viewBoard(board);
		logger.info(board.toString());
		
		boardfile = boardService.getBoardFile(board);
		model.addAttribute("file", boardfile);
		
		model.addAttribute("board", board);
		
		recommend.setBoard_no(board.getBoardno());
		recommend.setId(session.getAttribute("id").toString());
		boolean isRecommend = boardService.checkRecommend(recommend);
		model.addAttribute("isRecommend", isRecommend);
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public void write() { }
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeProc(
			Board board,
			@RequestParam(value="file") MultipartFile fileupload
			) {
		logger.info(board.toString());
		logger.info("파일 : " + fileupload.getOriginalFilename());
		logger.info(context.getRealPath("upload"));
		
		boardService.write(board, fileupload, context);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.GET)
	public void update(Board board, Boardfile boardfile, Model model) {
		logger.info(board.toString());
		
		board = boardService.viewBoard(board);
		boardfile = boardService.getBoardFile(board);
		
		model.addAttribute("board", board);
		model.addAttribute("file", boardfile);
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateProc(
			Board board,
			@RequestParam(value="file") MultipartFile fileupload
		) {
		
		logger.info(board.toString());
		boardService.boardUpdate(board, fileupload, context);
	
		
		return "redirect:/board/view?boardno="+board.getBoardno();
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public String delete(Board board) {
		
		boardService.boardDelete(board);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/download", method=RequestMethod.GET)
	public ModelAndView download(
			Boardfile boardfile,
			ModelAndView mav
		) {
		
		logger.info("파일번호 : " + boardfile.getFileno());
		
		//viewName 지정
		mav.setViewName("down");
		
		//파일번호에 해당하는 파일 정보 가져오기
		boardfile = boardService.getFile(boardfile);
		logger.info("조회된 파일 정보 : " + boardfile);
		
		//파일정보 Model로 값 넘기기
		mav.addObject("downFile", boardfile);
		
		return mav;
	}
	
	
}
