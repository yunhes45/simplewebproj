package com.simpleweb.simpleweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@RequestMapping("/")
	public String test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("session_id");
		
		if(session_id != null) {
			return "mainBoard";
		}else {
			return "index";
		}
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
		
		
		return "redirect:/#one";
	}
	
	@PostMapping("/signin")
	public String post_signin(RedirectAttributes redirectattributes, Member member_form, HttpServletRequest request) {
		Member member = new Member();
		
		member.setMember_id    (member_form.getMember_id());
		member.setMember_pwd   (member_form.getMember_pwd());
		
		String res = memberservice.getMemberLogin(member);
		
		if(res.equals("fail")) {
			return "redirect:/?message=failed#one";
		}else {
			String session_id   = member_form.getMember_id();
			HttpSession session = request.getSession();
			session.setAttribute("session_id", session_id);
			
			return "redirect:mainboard";
		}
	}
	
	
	
}
