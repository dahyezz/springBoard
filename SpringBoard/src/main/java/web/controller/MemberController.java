package web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dto.Member;
import web.service.face.MemberService;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired MemberService memberService;
	
	@RequestMapping(value="/member/main", method=RequestMethod.GET)
	public void main() { }
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public void login() { }
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String loginProc(Member member, HttpSession session) {
		
		String redirectUrl = "";
		
		if(memberService.login(member)) {
			
			member = memberService.getMemberInfo(member);
			
			session.setAttribute("login", true);
			session.setAttribute("id", member.getId());
			session.setAttribute("nick", member.getNick());
			
			redirectUrl = "/member/main";
		} else {
			
			redirectUrl = "/member/login";
		}
		
		logger.info(member.toString());
		
		return "redirect:"+redirectUrl;
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/member/main";
		
	}
	
	@RequestMapping(value="/member/join", method=RequestMethod.GET)
	public void join() { }
	
	@RequestMapping(value="/member/join", method=RequestMethod.POST)
	public String joinProc(Member member) {
		
		memberService.join(member);
		
		return "redirect:/member/main";
	}
	

	
}
