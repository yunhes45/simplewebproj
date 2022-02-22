package com.simpleweb.simpleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@RequestMapping("/")
	public String test() {
		
		return "index";
	}
	
	@RequestMapping("/welcomepage")
	public String notest() {
		
		return "welcomepage";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		
		return "signup";
	}
	@PostMapping("/signup")
	public String post_signup(Member member_form) {
		Member member = new Member();
		
		member.setMember_id          (member_form.getMember_id());
		member.setMember_pwd         (member_form.getMember_pwd());
		member.setMember_email       (member_form.getMember_email());
		member.setMember_job         (member_form.getMember_job());
		member.setMember_mobile      (member_form.getMember_mobile());
		member.setMember_gender      (member_form.getMember_gender());
		member.setMember_introduce   (member_form.getMember_introduce());
		
		try {
			memberservice.insertMember(member);
		}catch(IllegalStateException e) {
			if(e.getMessage().equals("이미 존재하는 아이디 입니다.")) {
				return "redirect:/signup?message=duplicateId";
			}else if(e.getMessage().equals("이미 존재하는 이메일 입니다.")) {
				return "redirect:/signup?message=duplicateEmail";
			}
		}
		
		
		return "redirect:welcomepage#one";
	}
	
	@PostMapping("/signin")
	public String post_signin(Member member_form) {
		Member member = new Member();
		
		member.setMember_id    (member_form.getMember_id());
		member.setMember_pwd   (member_form.getMember_pwd());
		
		
		
		return "redirect:/";
	}
	
}
