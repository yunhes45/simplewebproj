package com.simpleweb.simpleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpleweb.simpleweb.model.Member;

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
	@PostMapping("/signup")
	public String post_signup(Member member_form) {
		Member member = new Member();
		
		System.out.println(member_form.getMember_id());
		System.out.println(member_form.getMember_pwd());
		System.out.println(member_form.getMember_email());
		System.out.println(member_form.getMember_job());
		System.out.println(member_form.getMember_mobile());
		System.out.println(member_form.getMember_gender());
		System.out.println(member_form.getMember_introduce());
		
		member.setMember_id(member_form.getMember_id());
		member.setMember_pwd(member_form.getMember_pwd());
		member.setMember_email(member_form.getMember_email());
		member.setMember_job(member_form.getMember_job());
		member.setMember_mobile(member_form.getMember_mobile());
		member.setMember_gender(member_form.getMember_gender());
		member.setMember_introduce(member_form.getMember_introduce());
		
		return "redirect:welcomepage#one";
	}
	
}
