package com.simpleweb.simpleweb.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.service.BoardService;

@Controller
public class ChatController {
	
	@Autowired
	BoardService boardservice;
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			// follow my list
			List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
			model.addAttribute("Follow_my_list", Follow_my_list);
			
		}else {
			
			return "redirect:/";
		}
		
		return "chat";
	}
}
