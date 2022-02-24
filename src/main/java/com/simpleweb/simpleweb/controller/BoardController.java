package com.simpleweb.simpleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/mainboard")
	public String mainboard() {
		
		return "mainboard";
	}
	
	@RequestMapping("/mypage")
	public String mypage() {
		
		return "mypage";
	}
	
}
