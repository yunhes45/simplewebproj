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

import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Comment_like_stat;
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
		
		Like_stat like_stat = new Like_stat();
		like_stat.setMember_no(session_info.get().getMember_no());
		like_stat.setPost_no(Integer.parseInt(trim_post_no));
		like_stat.setLike_stat_check(1);
		
		Map<String, Integer> likelogic = new HashMap<String, Integer>(); 
		likelogic = boardfuncservice.LikeLogic(like_stat);

		return likelogic;
	}
	
	@ResponseBody
	@PostMapping("/bookmark")
	public Map post_bookmark(HttpServletRequest request,
			@RequestParam("post_no") String post_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_post_no = post_no.trim();
		
		Bookmark bookmark = new Bookmark();
		bookmark.setMember_no(session_info.get().getMember_no());
		bookmark.setPost_no(Integer.parseInt(trim_post_no));
		bookmark.setBookmark_check(1);
		bookmark.setBookmark_date(commonservice.nowTime());
		
		Map<String, Integer> bookmarklogic = new HashMap<String, Integer>();
		bookmarklogic = boardfuncservice.BookmarkLogic(bookmark);
		
		return bookmarklogic;
	}
	
	@PostMapping("/comment")
	public String post_comment(Model model, HttpServletRequest request,
			@RequestParam("post_no") String post_no,
			@RequestParam("comment_text") String comment_text) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_post_no = post_no.trim();
		
		Comment comment = new Comment();
		comment.setMember_no(session_info.get().getMember_no());
		comment.setPost_no(Integer.parseInt(trim_post_no));
		comment.setComment_text(comment_text);
		comment.setComment_date(commonservice.nowTime());
		
		Optional<Comment> commentlogic = boardfuncservice.CommentLogic(comment);
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
	
	@ResponseBody
	@PostMapping("/likecomment")
	public Map post_likecomment(HttpServletRequest request,
			@RequestParam("comment_no") String comment_no,
			@RequestParam("post_no") String post_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		String trim_comment_no = comment_no.trim();
		String trim_post_no = post_no.trim();
		
		Comment_like_stat comment_like_stat = new Comment_like_stat();
		comment_like_stat.setMember_no(session_info.get().getMember_no());
		comment_like_stat.setPost_no(Integer.parseInt(trim_post_no));
		comment_like_stat.setComment_no(Integer.parseInt(trim_comment_no));
		comment_like_stat.setComment_like_stat_check(1);
		comment_like_stat.setComment_like_stat_date(commonservice.nowTime());
		
		
		Map<String, Integer> likecommentlogic = new HashMap<String, Integer>(); 
		likecommentlogic = boardfuncservice.LikeCommentLogic(comment_like_stat);

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
	
	@GetMapping("/search")
	public String post_search(HttpServletRequest request) {
		String search = request.getParameter("search");
		
		return "redirect:mainboard?search=" + search;
	}

}
