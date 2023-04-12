package com.simpleweb.simpleweb.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Alarm_chat;
import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.model.Post_hashtag;
import com.simpleweb.simpleweb.service.AlarmService;
import com.simpleweb.simpleweb.service.BoardFuncService;
import com.simpleweb.simpleweb.service.BoardService;
import com.simpleweb.simpleweb.service.CommonService;
import com.simpleweb.simpleweb.service.MemberService;

@Controller
public class BoardController {
	
	@Autowired
	MemberService memberservice;
	@Autowired
	BoardService boardservice;
	@Autowired
	BoardFuncService boardfuncservice;
	@Autowired
	AlarmService alarmservice;
	
	@Autowired
	CommonService commonservice;
	
	@RequestMapping("/mainboard")
	public String mainboard(@RequestParam(value="search", required=false) String search,
			Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			int startPage   = 0;
			int onePageCnt  = 5;
			
			if(search == null) {
				
				// header alarm
				List<Alarm_chat> alarm = alarmservice.getAlarm_chat(session_info.get().getMember_no());
				
				int alarm_count = alarmservice.getAlarm_chat_count(session_info.get().getMember_no());
				
				// int alarm_count = alarm.get(0).getAlarm_count();
				
				model.addAttribute("alarm", alarm);
				model.addAttribute("alarm_count", alarm_count);
				
				List<Post> post_list = boardservice.getPost_list_algo(startPage, onePageCnt);
				model.addAttribute("post_list", post_list);

				// get post_no
				List<Integer> post_list_no = new ArrayList<Integer>();
				for(int i = 0; i < post_list.size(); i++) {
					post_list_no.add(post_list.get(i).getPost_no());
				}

				// like logic
				List<List<Like_stat>> Post_Like_list = boardservice.getPost_Like_list(post_list_no);
				model.addAttribute("Post_Like_list", Post_Like_list);
				
				// like cnt
				List<Integer> Like_cnt = boardservice.getLike_cnt(Post_Like_list);
				model.addAttribute("Like_cnt", Like_cnt);
				
				// like check logic
				List<String> like_check = boardservice.getLike_check(Like_cnt, Post_Like_list, session_info.get().getMember_id());
				model.addAttribute("like_check", like_check);
				
				// bookmark logic
				List<List<Bookmark>> Post_Bookmark_list = boardservice.getPost_Bookmark_list(post_list_no);
				model.addAttribute("Post_Bookmark_list", Post_Bookmark_list);
				
				// bookmark cnt
				List<Integer> Bookmark_cnt = boardservice.getBookmark_cnt(Post_Bookmark_list);
				model.addAttribute("Bookmark_cnt", Bookmark_cnt);
				
				// bookmark check logic
				List<String> bookmark_check = boardservice.getBookmark_check(Bookmark_cnt, Post_Bookmark_list, session_info.get().getMember_id());
				model.addAttribute("bookmark_check", bookmark_check);
				
				// comment logic
				List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
				model.addAttribute("Post_Comment_list", Post_Comment_list);
				
				// comment cnt
				List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
				model.addAttribute("Comment_cnt", Comment_cnt);
				
				// comment like check logic
				List<String> Comment_Like_check = boardservice.getComment_Like_check(Comment_cnt, Post_Comment_list, session_info.get().getMember_no());
				model.addAttribute("comment_like_check", Comment_Like_check);
				
				// follow check logic
				List<String> follow_check = boardservice.getFollow_check(post_list, session_info.get().getMember_no());
				model.addAttribute("follow_check", follow_check);
				
				// follow my list
				List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
				model.addAttribute("Follow_my_list", Follow_my_list);
				
				// follow me list
				List<Member> Follow_me_list = boardservice.getFollow_me_list(session_info.get().getMember_no());
				model.addAttribute("Follow_me_list", Follow_me_list);
			
				// hashtag(menu)
				List<List<Post_hashtag>> post_menu_hashtag = boardservice.getPostMenuHashtag(post_list_no, 2);
				model.addAttribute("post_menu_hashtag", post_menu_hashtag);

				// hashtag(menu) cnt
				List<Integer> post_menu_hashtag_cnt = boardservice.getPost_menu_hashtag_cnt(post_menu_hashtag);
				model.addAttribute("post_menu_hashtag_cnt", post_menu_hashtag_cnt);
				
			}else {
				List<Post> post_list = boardservice.getPost_list_algo_search(startPage, onePageCnt, search);
				model.addAttribute("post_list", post_list);

				// get post_no
				List<Integer> post_list_no = new ArrayList<Integer>();
				for(int i = 0; i < post_list.size(); i++) {
					post_list_no.add(post_list.get(i).getPost_no());
				}
				
				// like logic
				List<List<Like_stat>> Post_Like_list = boardservice.getPost_Like_list(post_list_no);
				model.addAttribute("Post_Like_list", Post_Like_list);
				
				// like cnt
				List<Integer> Like_cnt = boardservice.getLike_cnt(Post_Like_list);
				model.addAttribute("Like_cnt", Like_cnt);
				
				// like check logic
				List<String> like_check = boardservice.getLike_check(Like_cnt, Post_Like_list, session_info.get().getMember_id());
				model.addAttribute("like_check", like_check);
				
				// bookmark logic
				List<List<Bookmark>> Post_Bookmark_list = boardservice.getPost_Bookmark_list(post_list_no);
				model.addAttribute("Post_Bookmark_list", Post_Bookmark_list);
				
				// bookmark cnt
				List<Integer> Bookmark_cnt = boardservice.getBookmark_cnt(Post_Bookmark_list);
				model.addAttribute("Bookmark_cnt", Bookmark_cnt);
				
				// bookmark check logic
				List<String> bookmark_check = boardservice.getBookmark_check(Bookmark_cnt, Post_Bookmark_list, session_info.get().getMember_id());
				model.addAttribute("bookmark_check", bookmark_check);
				
				// comment logic
				List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
				model.addAttribute("Post_Comment_list", Post_Comment_list);
				
				// comment cnt
				List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
				model.addAttribute("Comment_cnt", Comment_cnt);

				// comment like check logic
				List<String> Comment_Like_check = boardservice.getComment_Like_check(Comment_cnt, Post_Comment_list, session_info.get().getMember_no());
				model.addAttribute("comment_like_check", Comment_Like_check);
				
				// follow check logic
				List<String> follow_check = boardservice.getFollow_check(post_list, session_info.get().getMember_no());
				model.addAttribute("follow_check", follow_check);
				
				// follow my list
				List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
				model.addAttribute("Follow_my_list", Follow_my_list);
				
				// follow me list
				List<Member> Follow_me_list = boardservice.getFollow_me_list(session_info.get().getMember_no());
				model.addAttribute("Follow_me_list", Follow_me_list);	
				
				// hashtag(menu)
				List<List<Post_hashtag>> post_menu_hashtag = boardservice.getPostMenuHashtag(post_list_no, 2);
				model.addAttribute("post_menu_hashtag", post_menu_hashtag);

				// hashtag(menu) cnt
				List<Integer> post_menu_hashtag_cnt = boardservice.getPost_menu_hashtag_cnt(post_menu_hashtag);
				model.addAttribute("post_menu_hashtag_cnt", post_menu_hashtag_cnt);
				
			}
			
		}else {
			return "redirect:/";
		}
		
		return "mainboard";
	}
	@PostMapping("/mainboard")
	public String infinite_scroll_post_mainboard(Model model, HttpServletRequest request,
			@RequestParam(value="param", required=false) String search,
			@RequestParam(value="page", required=false) String page,
			@RequestParam(value="count", required=false) String count) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		System.out.println("page : " + page);
		System.out.println("couont : " + count);
		
		int startPage   = (5 * Integer.parseInt(count)) - (Integer.parseInt(count)*2) + 2 ;
		int onePageCnt  = Integer.parseInt(page);

		if(search == null) {
		
			List<Post> post_list = boardservice.getPost_list_algo(startPage, onePageCnt);
			model.addAttribute("post_list", post_list);

			// get post_no
			List<Integer> post_list_no = new ArrayList<Integer>();
			for(int i = 0; i < post_list.size(); i++) {
				post_list_no.add(post_list.get(i).getPost_no());
			}
			
			// like logic
			List<List<Like_stat>> Post_Like_list = boardservice.getPost_Like_list(post_list_no);
			model.addAttribute("Post_Like_list", Post_Like_list);
			
			// like cnt
			List<Integer> Like_cnt = boardservice.getLike_cnt(Post_Like_list);
			model.addAttribute("Like_cnt", Like_cnt);
			
			// like check logic
			List<String> like_check = boardservice.getLike_check(Like_cnt, Post_Like_list, session_info.get().getMember_id());
			model.addAttribute("like_check", like_check);
			
			// bookmark logic
			List<List<Bookmark>> Post_Bookmark_list = boardservice.getPost_Bookmark_list(post_list_no);
			model.addAttribute("Post_Bookmark_list", Post_Bookmark_list);
			
			// bookmark cnt
			List<Integer> Bookmark_cnt = boardservice.getBookmark_cnt(Post_Bookmark_list);
			model.addAttribute("Bookmark_cnt", Bookmark_cnt);
			
			// bookmark check logic
			List<String> bookmark_check = boardservice.getBookmark_check(Bookmark_cnt, Post_Bookmark_list, session_info.get().getMember_id());
			model.addAttribute("bookmark_check", bookmark_check);
			
			// comment logic
			List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
			model.addAttribute("Post_Comment_list", Post_Comment_list);
			
			// comment cnt
			List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
			model.addAttribute("Comment_cnt", Comment_cnt);
			
			// comment like check logic
			List<String> Comment_Like_check = boardservice.getComment_Like_check(Comment_cnt, Post_Comment_list, session_info.get().getMember_no());
			model.addAttribute("comment_like_check", Comment_Like_check);
			
			// follow check logic
			List<String> follow_check = boardservice.getFollow_check(post_list, session_info.get().getMember_no());
			model.addAttribute("follow_check", follow_check);
			
			// follow my list
			List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
			model.addAttribute("Follow_my_list", Follow_my_list);
			
			// follow me list
			List<Member> Follow_me_list = boardservice.getFollow_me_list(session_info.get().getMember_no());
			model.addAttribute("Follow_me_list", Follow_me_list);
		
			// hashtag(menu)
			List<List<Post_hashtag>> post_menu_hashtag = boardservice.getPostMenuHashtag(post_list_no, 2);
			model.addAttribute("post_menu_hashtag", post_menu_hashtag);

			// hashtag(menu) cnt
			List<Integer> post_menu_hashtag_cnt = boardservice.getPost_menu_hashtag_cnt(post_menu_hashtag);
			model.addAttribute("post_menu_hashtag_cnt", post_menu_hashtag_cnt);
			
			
		}else {
			List<Post> post_list = boardservice.getPost_list_algo_search(startPage, onePageCnt, search);
			model.addAttribute("post_list", post_list);

			// get post_no
			List<Integer> post_list_no = new ArrayList<Integer>();
			for(int i = 0; i < post_list.size(); i++) {
				post_list_no.add(post_list.get(i).getPost_no());
			}
			
			// like logic
			List<List<Like_stat>> Post_Like_list = boardservice.getPost_Like_list(post_list_no);
			model.addAttribute("Post_Like_list", Post_Like_list);
			
			// like cnt
			List<Integer> Like_cnt = boardservice.getLike_cnt(Post_Like_list);
			model.addAttribute("Like_cnt", Like_cnt);
			
			// like check logic
			List<String> like_check = boardservice.getLike_check(Like_cnt, Post_Like_list, session_info.get().getMember_id());
			model.addAttribute("like_check", like_check);
			
			// bookmark logic
			List<List<Bookmark>> Post_Bookmark_list = boardservice.getPost_Bookmark_list(post_list_no);
			model.addAttribute("Post_Bookmark_list", Post_Bookmark_list);
			
			// bookmark cnt
			List<Integer> Bookmark_cnt = boardservice.getBookmark_cnt(Post_Bookmark_list);
			model.addAttribute("Bookmark_cnt", Bookmark_cnt);
			
			// bookmark check logic
			List<String> bookmark_check = boardservice.getBookmark_check(Bookmark_cnt, Post_Bookmark_list, session_info.get().getMember_id());
			model.addAttribute("bookmark_check", bookmark_check);
			
			// comment logic
			List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
			model.addAttribute("Post_Comment_list", Post_Comment_list);
			
			// comment cnt
			List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
			model.addAttribute("Comment_cnt", Comment_cnt);

			// comment like check logic
			List<String> Comment_Like_check = boardservice.getComment_Like_check(Comment_cnt, Post_Comment_list, session_info.get().getMember_no());
			model.addAttribute("comment_like_check", Comment_Like_check);
			
			// follow check logic
			List<String> follow_check = boardservice.getFollow_check(post_list, session_info.get().getMember_no());
			model.addAttribute("follow_check", follow_check);
			
			// follow my list
			List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
			model.addAttribute("Follow_my_list", Follow_my_list);
			
			// follow me list
			List<Member> Follow_me_list = boardservice.getFollow_me_list(session_info.get().getMember_no());
			model.addAttribute("Follow_me_list", Follow_me_list);	
			
			// hashtag(menu)
			List<List<Post_hashtag>> post_menu_hashtag = boardservice.getPostMenuHashtag(post_list_no, 2);
			model.addAttribute("post_menu_hashtag", post_menu_hashtag);

			// hashtag(menu) cnt
			List<Integer> post_menu_hashtag_cnt = boardservice.getPost_menu_hashtag_cnt(post_menu_hashtag);
			model.addAttribute("post_menu_hashtag_cnt", post_menu_hashtag_cnt);
			
		}
			
		return "maincontent_list_ajax";
	}
	
	@RequestMapping("/mypage/{member}")
	public String mypage(Model model, HttpServletRequest request,
			@PathVariable(value="member") String member_id,
			@RequestParam(value="postpage" , required=false) String postpage,
			@RequestParam(value="bookmarkpage", required=false) String bookmarkpage) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		int member_no = commonservice.getMember_no(member_id);
		Optional<Member> member_info = memberservice.getMyInfo(member_no); 
		
		if(session_info != null) {
			int file_listtotalcount = boardservice.getMyTotal_fileList(member_info.get().getMember_no());
			int startPage   = 0;
			int onePageCnt  = 10;
			
			// 페이징 이전,다음
			int count       = (int)Math.ceil((double)file_listtotalcount/(double)onePageCnt);
			model.addAttribute("maxcount", count);
			model.addAttribute("mincount", count-3);
			
			// file list page
			model.addAttribute("file_listtotalcount", file_listtotalcount);	

			if(postpage == null && bookmarkpage == null) {		
				List<Post> post_list = boardservice.getMyPost_list(member_info.get().getMember_no(), startPage, onePageCnt); 
				model.addAttribute("post_list", post_list);
				
			}else if(postpage != null && bookmarkpage == null){
				// 페이징 처리
				List<Integer> paging = new ArrayList<>();

				if(Integer.parseInt(postpage) <= 0) {
						return "redirect:/mypage/" + member_id + "?postpage=1";
					
				}else if(Integer.parseInt(postpage) <= 3 && Integer.parseInt(postpage) >= 1) {
					for(int i = 1; i <= 3; i++) {
						if(i <= count) {
							paging.add(i);
						}else {
							break;
						}
					}
				}else if(Integer.parseInt(postpage) >= 4){
					int startPaging = Integer.parseInt(postpage);
					int endPaging = startPaging + 2;
					
						for(int i = startPaging; i <= endPaging; i++) {
							if(i <= count) {
								paging.add(i);
							}else {
								break;
							}
						}
				}
				model.addAttribute("page_count", paging);

				startPage = (Integer.parseInt(postpage) - 1)*onePageCnt;
				List<Post> post_list = boardservice.getMyPost_list(member_info.get().getMember_no(), startPage, onePageCnt); 
				model.addAttribute("post_list", post_list);

			}else if(postpage == null && bookmarkpage != null) {
				
			// bookmark list page
			int bookmark_listtotalcount = boardservice.getMyTotal_bookmarkList(member_info.get().getMember_no());
			int bookmark_startPage   = 0;
			int bookmark_onePageCnt  = 10;
			int bookmark_count       = (int)Math.ceil((double)bookmark_listtotalcount/(double)bookmark_onePageCnt);
			
			// 북마크페이징 이전,다음
			model.addAttribute("bookmark_maxcount", bookmark_count);
			model.addAttribute("bookmark_mincount", bookmark_count-3);
			
			// 페이징 처리
			List<Integer> paging = new ArrayList<>();

			if(Integer.parseInt(bookmarkpage) <= 0) {
					return "redirect:/mypage/" + member_id + "?bookmarkpage=1";
				
			}else if(Integer.parseInt(bookmarkpage) <= 3 && Integer.parseInt(bookmarkpage) >= 1) {
				for(int i = 1; i <= 3; i++) {
					if(i <= bookmark_count) {
						paging.add(i);
					}else {
						break;
					}
				}
			}else if(Integer.parseInt(bookmarkpage) >= 4){
				int startPaging = Integer.parseInt(bookmarkpage);
				int endPaging = startPaging + 2;
				
					for(int i = startPaging; i <= endPaging; i++) {
						if(i <= bookmark_count) {
							paging.add(i);
						}else {
							break;
						}
					}
			}
			model.addAttribute("bookmark_page_count", paging);

			bookmark_startPage = (Integer.parseInt(bookmarkpage) - 1)*bookmark_onePageCnt;
			List<Bookmark> bookmark_list = boardservice.getMyBookmark_list(member_info.get().getMember_no(), bookmark_startPage, bookmark_onePageCnt); 
			model.addAttribute("bookmark_list", bookmark_list);
			
			}
			
			// follow check
			int mypage_follow_check_no = commonservice.getMember_no(member_id);
			
			String follow_check = boardservice.follow_check_mypage(session_info.get().getMember_no() ,mypage_follow_check_no);
			model.addAttribute("follow_check", follow_check);
			
			model.addAttribute("session_info", session_info);
			model.addAttribute("member_info", member_info);
			
			return "mypage";
		}else {
			
			return "redirect:/";
		}
		
		// visible on off
		
//		if(session_info != null) {
//			int file_listtotalcount = boardservice.getTotal_fileList(member_info.get().getMember_no());
//			int startPage   = 0;
//			int onePageCnt  = 10;
//			int count       = (int)Math.ceil((double)file_listtotalcount/(double)onePageCnt);
//		
//			// file list page
//			model.addAttribute("file_listtotalcount", file_listtotalcount);
//
//			List<Integer> page_count = new ArrayList<>();
//			
//			for(int i=1; i<=count; i++) {
//				page_count.add(i);
//			}
//			
//			model.addAttribute("page_count", page_count);
//			
//			System.out.println("postpage : " + postpage);
//			System.out.println("bookmarkpage : " + bookmarkpage);
//			
//			if(postpage != null) {			
//				startPage = (Integer.parseInt(postpage) - 1)*onePageCnt;
//				List<Post> post_list = boardservice.getMyPost_list(member_info.get().getMember_no(), startPage, onePageCnt); 
//				model.addAttribute("post_list", post_list);
//
//			}else {
//				List<Post> post_list = boardservice.getMyPost_list(member_info.get().getMember_no(), startPage, onePageCnt); 
//				model.addAttribute("post_list", post_list);
//
//			}
//			
//			// bookmark list page
//			int bookmark_listtotalcount = boardservice.getTotal_bookmarkList(member_info.get().getMember_no());
//			int bookmark_startPage   = 0;
//			int bookmark_onePageCnt  = 10;
//			int bookmark_count       = (int)Math.ceil((double)bookmark_listtotalcount/(double)bookmark_onePageCnt);
//			
//			model.addAttribute("bookmark_listtotalcount", bookmark_listtotalcount);
//
//			List<Integer> bookmark_page_count = new ArrayList<>();
//			
//			for(int i=1; i<=bookmark_count; i++) {
//				bookmark_page_count.add(i);
//			}
//			
//			if(bookmarkpage != null) {	
//				
//				model.addAttribute("bookmark_page_count", bookmark_page_count);
//				
//				bookmark_startPage = (Integer.parseInt(bookmarkpage) - 1)*bookmark_onePageCnt;
//				List<Bookmark> bookmark_list = boardservice.getBookmark_list(member_info.get().getMember_no(), bookmark_startPage, bookmark_onePageCnt); 
//				model.addAttribute("bookmark_list", bookmark_list);
//			}else {
////				List<Bookmark> bookmark_list = boardservice.getBookmark_list(member_info.get().getMember_no(), bookmark_startPage, bookmark_onePageCnt); 
////				model.addAttribute("bookmark_list", bookmark_list);
//			}			
//			
//			model.addAttribute("member_info", member_info);
//			
//			return "mypage";
//		}else {
//			
//			return "redirect:/";
//		}
		
	}
	
	@RequestMapping("/writepost")
	public String writepost(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
			model.addAttribute("session_info", session_info);
		}else {
			return "redirect:/";
		}
	
		return "writepost";
	}
	
	@PostMapping("/writepost")
	public String post_writepost(Post post_form, Post_img post_img_form, Post_hashtag post_hashtag_form,
			RedirectAttributes redirectattributes, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
			Post post = new Post();
			post.setPost_title(post_form.getPost_title());
			post.setPost_subtitle(post_form.getPost_subtitle());
			post.setPost_contents(post_form.getPost_contents());
			post.setMember_no(session_info.get().getMember_no());
			post.setPost_date(commonservice.nowTime());	
			
			boardservice.insertPost(post);
			
			int postPK = post.getPost_no();
			Post_img post_img = new Post_img();
			
			post_img = commonservice.post_imglogic(postPK, post_img_form.getPostimg());
			boardservice.insertPostImg(post_img);	
			
			// post content hashtag
			Post_hashtag post_content_hashtag = new Post_hashtag();
			post_content_hashtag.setPost_no(postPK);
			post_content_hashtag.setPost_hashtag_list(post.getPost_contents());
			post_content_hashtag.setPost_hashtag_division(0);
			
			boardservice.insertPostHashtag(post_content_hashtag);
			
			// post menu hashtag
			Post_hashtag post_menu_hashtag = new Post_hashtag();
			post_menu_hashtag.setPost_no(postPK);
			post_menu_hashtag.setPost_hashtag_list(post_hashtag_form.getPost_hashtag_list());
			post_menu_hashtag.setPost_hashtag_division(2);
			
			boardservice.insertPostHashtag(post_menu_hashtag);
		
		}else {
			return "redirect:/";
		}
		
//		redirectattributes.addAttribute("member", session_info.get().getMember_id());
		return "redirect:mypage/" + session_info.get().getMember_id();
	}
	
	@RequestMapping("/modifypost")
	public String modifypost(Model model, HttpServletRequest request, Post post_form, Post_img post_img_form) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
			Optional<Post> post_info = boardservice.getMemberPost(post_form.getPost_no(), session_info.get().getMember_no());
			model.addAttribute("fileDir", commonservice.fileDir_path());
			model.addAttribute("session_info", session_info);
			model.addAttribute("post_info", post_info);
			
		}else {
			return "redirect:/";
		}
	
		return "modifypost";
	}
	
	@PostMapping("/modifypost_back")
	public String post_modifypost(HttpServletRequest request, Post post_form, Post_img post_img_form) throws Exception {
		HttpSession session = request.getSession();
		
		Post post = new Post();
		post.setPost_no(post_form.getPost_no());
		post.setPost_title(post_form.getPost_title());
		post.setPost_subtitle(post_form.getPost_subtitle());
		post.setPost_contents(post_form.getPost_contents());
		post.setPost_date(commonservice.nowTime());
		
		boardservice.updatePost(post);
		
		int postPK = post.getPost_no();
		Post_img post_img = new Post_img();
		
		post_img = commonservice.post_imglogic(postPK, post_img_form.getPostimg());
		boardservice.updatePostImg(post_img);
		
		return "redirect:mainboard";
	}
	
	@PostMapping("/deletepost")
	public String deletepost(Model model, HttpServletRequest request, Post post_form) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
			boardservice.deletePost(post_form.getPost_no());
		}else {
			return "redirect:/";
		}
		
		return "redirect:mainboard";
	}
	
	@RequestMapping("/detailpost")
	public String detailpost(Model model, HttpServletRequest request,
			@RequestParam("name") String member_id,
			@RequestParam("post") String post_no) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			int member_no = commonservice.getMember_no(member_id);
			
			Optional<Post> memberPost = boardservice.getMemberPost(Integer.parseInt(post_no), member_no);
			model.addAttribute("memberPost", memberPost);
			
			// like logic
			List<Like_stat> memberPost_Like_list = boardservice.getMemberPost_Like_list(Integer.parseInt(post_no));
			model.addAttribute("memberPost_Like_list", memberPost_Like_list);
			
			// like check logic
			String memberlike_check = boardservice.getMemberLike_check(Integer.parseInt(post_no), session_info.get().getMember_no());
			model.addAttribute("memberlike_check", memberlike_check);
			
			// bookmark logic
			List<Bookmark> memberPost_Bookmark_list = boardservice.getMemberPost_Bookmark_list(Integer.parseInt(post_no));
			model.addAttribute("memberPost_Bookmark_list", memberPost_Bookmark_list);
			
			// bookmark check logic
			String memberbookmark_check = boardservice.getMemberBookmark_check(Integer.parseInt(post_no), session_info.get().getMember_no());
			model.addAttribute("memberbookmark_check", memberbookmark_check);
			
			// comment logic
			List<Comment> memberPost_Comment_list = boardservice.getMemberPost_Comment_list(Integer.parseInt(post_no));
			model.addAttribute("memberPost_Comment_list", memberPost_Comment_list);
			
			// comment like check logic
			List<String> memberComment_Like_check = boardservice.getMemberComment_Like_check(Integer.parseInt(post_no), session_info.get().getMember_no());
			model.addAttribute("memberComment_Like_check", memberComment_Like_check);
			
//			// follow check logic
//			List<String> memberfollow_check = boardservice.getMemberFollow_check(session_info.get().getMember_no());
//			model.addAttribute("memberfollow_check", memberfollow_check);
			
			return "detailpost";
		
		}else {
			return "redirect:/";
		}
	}
	
}
