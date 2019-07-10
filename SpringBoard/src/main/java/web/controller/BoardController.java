package web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;
 
@Controller 
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired BoardService boardService;
	
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public void getList(Model model, String curPage) {
 

		Paging paging = boardService.getcurPage(curPage);
		
		List<Board> boardList = boardService.list(paging);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping(value="/board/view", method=RequestMethod.GET)
	public void view(Board board, Model model) {
		
		board = boardService.viewBoard(board);
		logger.info(board.toString());
		
		model.addAttribute("board", board);
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public void write() { }
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeProc(Board board) {
		logger.info(board.toString());
		
		boardService.write(board);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.GET)
	public void update(Board board, Model model) {
		logger.info(board.toString());
		
		board = boardService.viewBoard(board);
		model.addAttribute("board", board);
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateProc(Board board) {
		
		logger.info(board.toString());
		boardService.boardUpdate(board);
	
		
		return "redirect:/board/view?boardno="+board.getBoardno();
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public String delete(Board board) {
		
		boardService.boardDelete(board);
		
		return "redirect:/board/list";
	}
	
	
}
