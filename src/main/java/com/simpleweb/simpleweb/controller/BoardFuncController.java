package com.simpleweb.simpleweb.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.service.BoardFuncService;
import com.simpleweb.simpleweb.service.CommonService;
import com.simpleweb.simpleweb.service.MemberService;

@Controller
public class BoardFuncController {
	
	@Autowired
	private BoardFuncService boardfuncservice;

	@Autowired
	private MemberService memberservice;
	
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
	
	@ResponseBody
	@PostMapping("/bookmark")
	public Map post_bookmark(HttpServletRequest request,
			@RequestParam("post_no") String post_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_post_no = post_no.trim();
		
		Map<String, Integer> bookmarklogic = new HashMap<String, Integer>();
		bookmarklogic = boardfuncservice.BookmarkLogic(session_info.get().getMember_no(), 
				Integer.parseInt(trim_post_no), 1, commonservice.nowTime());
		
		return bookmarklogic;
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
		
		System.out.println("commentlogic : " + commentlogic.get().getComment_no());

		return "ajaxtemplates/comment_ajax";
	}
	
	@ResponseBody
	@PostMapping("/delcomment")
	public String post_delcomment(@RequestParam("comment_no") String comment_no) {
		
		String trim_comment_no = comment_no.trim();
		
		int delcomment = boardfuncservice.delcomment(Integer.parseInt(trim_comment_no));
		
		return comment_no;
	}
	
	@ResponseBody
	@PostMapping("/likecomment")
	public Map post_likecomment(HttpServletRequest request,
			@RequestParam("comment_no") String comment_no,
			@RequestParam("post_no") String post_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_comment_no = comment_no.trim();
		String trim_post_no = post_no.trim();
		
		Map<String, Integer> likecommentlogic = new HashMap<String, Integer>(); 
		likecommentlogic = boardfuncservice.LikeCommentLogic(session_info.get().getMember_no(), Integer.parseInt(trim_comment_no), Integer.parseInt(trim_post_no), 1, commonservice.nowTime());
		
		System.out.println("lcl : " + likecommentlogic);
		
		return likecommentlogic;		
	}
		
	@ResponseBody
	@PostMapping("/follow")
	public Map post_follow(Model model, HttpServletRequest request,
			@RequestParam("follow_member_no") String follow_member_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_member_no = follow_member_no.trim();
	
		Map<String, Object> followlogic = new HashMap<>();
		
		int followcheck = boardfuncservice.FollowCheck(session_info.get().getMember_no(), Integer.parseInt(trim_member_no), commonservice.nowTime());
		Optional<Member> follow_member_info = memberservice.getMyInfo(Integer.parseInt(trim_member_no));
		
		followlogic.put("followcheck", followcheck);
		followlogic.put("follow_member_info", follow_member_info);
		
		return followlogic;
	}
	
	@PostMapping("/downloadFile")
	public ResponseEntity<Object> downloadFile(Post_img post_img_form, RedirectAttributes redirectAttributes)
	throws IOException, URISyntaxException {
		Post_img post_img = new Post_img();
		post_img.setPost_no(post_img_form.getPost_no());
		post_img.setPost_img_original_filename(post_img_form.getPost_img_original_filename());
		post_img.setPost_img_filename(post_img_form.getPost_img_filename());
		
		ResponseEntity<Object> res = new ResponseEntity<Object>(null, HttpStatus.OK);
		res = commonservice.downloadFileLogic(post_img);
		
		return res;
	}
	
	@GetMapping("/search")
	public String post_search(HttpServletRequest request) {
		String search = request.getParameter("search");
		
		return "redirect:mainboard?search=" + search;
	}

}
