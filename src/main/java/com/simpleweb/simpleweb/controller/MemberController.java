package com.simpleweb.simpleweb.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.service.CommonService;
import com.simpleweb.simpleweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private CommonService commonservice;
	
	@RequestMapping("/welcomepage")
	public String wel(Model model) throws IOException {
		
		return "welcomepage";
	}
	
	@RequestMapping("/")
	public String test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
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
	public String post_signup(Member member_form, Member_profileimg member_profileimg_form) throws Exception {
		Member member = new Member();
		
		member.setMember_id          (member_form.getMember_id());
		member.setMember_pwd         (member_form.getMember_pwd());
		member.setMember_email       (member_form.getMember_email());
		member.setMember_nickname    (member_form.getMember_nickname());
		member.setMember_job         (member_form.getMember_job());
		member.setMember_mobile      (member_form.getMember_mobile());
		member.setMember_gender      (member_form.getMember_gender());
		member.setMember_introduce   (member_form.getMember_introduce());
		member.setMember_date        (commonservice.nowTime());
		
		try {
			memberservice.insertMember(member);
		}catch(IllegalStateException e) {
			if(e.getMessage().equals("이미 존재하는 아이디 입니다.")) {
				return "redirect:/signup?message=duplicateId";
			}else if(e.getMessage().equals("이미 존재하는 이메일 입니다.")) {
				return "redirect:/signup?message=duplicateEmail";
			}
		}
		
		int memberPK = member.getMember_no();
		Member_profileimg member_img = new Member_profileimg();
		
		if(member_profileimg_form.getMemberimg().getOriginalFilename().equals("")) {
			member_img = commonservice.normalimglogic(memberPK);
			memberservice.insertMemberImg(member_img);
		}else {
			member_img = commonservice.imglogic(memberPK, member_profileimg_form.getMemberimg());
			memberservice.insertMemberImg(member_img);
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
			int session_no   = commonservice.getMember_no(member_form.getMember_id());
			Optional<Member> session_info   = memberservice.getMyInfo(session_no);
			
			HttpSession session = request.getSession();
			session.setAttribute("session_info", session_info);
			
			return "redirect:mainboard";
		}
	}
	
	@PostMapping("/signout")
	public String post_signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/";
	}

}
