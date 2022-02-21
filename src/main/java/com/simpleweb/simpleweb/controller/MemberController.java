package com.simpleweb.simpleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping("/welcomepage")
	public String notest() {
		
		return "welcomepage";
	}
	
	@RequestMapping("/")
	public String test() {
		
		return "index";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		
		return "signup";
	}
}
