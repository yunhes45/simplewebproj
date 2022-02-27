package com.simpleweb.simpleweb.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpleweb.simpleweb.model.Member;

@Controller
public class BoardController {
	
	@RequestMapping("/mainboard")
	public String mainboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		return "mainboard";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
		}else {
			
		}
		
		return "mypage";
	}
	
}
