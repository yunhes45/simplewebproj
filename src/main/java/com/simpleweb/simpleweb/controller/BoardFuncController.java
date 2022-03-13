package com.simpleweb.simpleweb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.service.BoardFuncService;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class BoardFuncController {
	
	@Autowired
	private BoardFuncService boardfuncservice;
	
	@Autowired
	private CommonService commonservice;

	@ResponseBody
	@PostMapping("/like")
	public Map post_like(HttpServletRequest request,
			@RequestParam("post_no") String post_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_post_no = post_no.trim();
		
		Map<String, Integer> likelogic = new HashMap<String, Integer>(); 
		likelogic = boardfuncservice.LikeLogic(session_info.get().getMember_no(), Integer.parseInt(trim_post_no), 1);

		return likelogic;
	}
	
	@PostMapping("/comment")
	public String post_comment(Model model, HttpServletRequest request,
			@RequestParam("post_no") String post_no,
			@RequestParam("comment_text") String comment_text) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_post_no = post_no.trim();
		
		Optional<Comment> commentlogic = boardfuncservice.CommentLogic(session_info.get().getMember_no(), Integer.parseInt(trim_post_no), comment_text, commonservice.nowTime());
		model.addAttribute("commentlogic", commentlogic);
		
		return "ajaxtemplates/comment_ajax";
	}
	
	@ResponseBody
	@PostMapping("/delcomment")
	public String post_delcomment(@RequestParam("comment_no") String comment_no) {
		
		String trim_comment_no = comment_no.trim();
		
		int delcomment = boardfuncservice.delcomment(Integer.parseInt(trim_comment_no));
		
		return comment_no;
	}

}
