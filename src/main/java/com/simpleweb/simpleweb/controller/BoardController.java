package com.simpleweb.simpleweb.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.service.BoardFuncService;
import com.simpleweb.simpleweb.service.BoardService;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	@Autowired
	BoardFuncService boardfuncservice;
	
	@Autowired
	CommonService commonservice;
	
	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	public String getFullPath(String fileurl, String filename) {
		return fileDir + fileurl + filename;
	}
	
	@ResponseBody
	@GetMapping("/postimg/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException{
		String fileurl    = "postimg\\";
		return new UrlResource("file:///" + getFullPath(fileurl, filename));
		
	}
	
	@RequestMapping("/mainboard")
	public String mainboard(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			int file_listtotalcount = boardservice.getTotal_fileList(session_info.get().getMember_no());
			int startPage   = 0;
			int onePageCnt  = 5;
			int count       = (int)Math.ceil((double)file_listtotalcount/(double)onePageCnt);
			
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
			
			// comment logic
			List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
			model.addAttribute("Post_Comment_list", Post_Comment_list);
			
			// comment cnt
			List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
			model.addAttribute("Comment_cnt", Comment_cnt);
			
			for(int i = 0; i < post_list_no.size(); i++) {
				for(int j = 0; j < Comment_cnt.get(i); j++) {
					System.out.println("fsdfds : " + Post_Comment_list.get(i).get(j).getComment_no());
				}
			}
					
		}else {
			return "redirect:/";
		}
		
		return "mainboard";
	}
	@PostMapping("/mainboard")
	public String post_mainboard(Model model, HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@RequestParam(value="count", required=false) String count) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		int startPage   = 5;
		int onePageCnt  = Integer.parseInt(page);
		
		int page_postlist_algo = (startPage * Integer.parseInt(count)) - ((Integer.parseInt(count)-1) * 2); 
		
		List<Post> post_list = boardservice.getPost_list_algo(page_postlist_algo, onePageCnt);

		for(int i=0; i<post_list.size(); i++) {
			System.out.println(post_list.get(i).getPost_no());
		}
		
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
		
		// comment logic
		List<List<Comment>> Post_Comment_list = boardservice.getPost_Comment_list(post_list_no);
		model.addAttribute("Post_Comment_list", Post_Comment_list);
		
		// comment cnt
		List<Integer> Comment_cnt = boardservice.getComment_cnt(Post_Comment_list);
		model.addAttribute("Comment_cnt", Comment_cnt);
		
		for(int i = 0; i < post_list_no.size(); i++) {
			for(int j = 0; j < Comment_cnt.get(i); j++) {
				System.out.println("fsdfds : " + Post_Comment_list.get(i).get(j).getComment_no());
			}
		}
		
		return "maincontent_list_ajax";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model, HttpServletRequest request,
			@RequestParam(value="member") String member_id ,
			@RequestParam(value="page" , required=false) String page ) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			// file list page
			int file_listtotalcount = boardservice.getTotal_fileList(session_info.get().getMember_no());
			int startPage   = 0;
			int onePageCnt  = 10;
			int count       = (int)Math.ceil((double)file_listtotalcount/(double)onePageCnt);
		
			model.addAttribute("file_listtotalcount", file_listtotalcount);

			List<Integer> page_count = new ArrayList<>();
			
			for(int i=1; i<=count; i++) {
				page_count.add(i);
			}
			
			model.addAttribute("page_count", page_count);
			
			if(page != null) {			
				startPage = (Integer.parseInt(page) - 1)*onePageCnt;
				List<Post> post_list = boardservice.getPost_list(session_info.get().getMember_no(), startPage, onePageCnt); 
				model.addAttribute("post_list", post_list);
			}else {
				List<Post> post_list = boardservice.getPost_list(session_info.get().getMember_no(), startPage, onePageCnt); 
				model.addAttribute("post_list", post_list);
			}
			
			model.addAttribute("member_id", member_id);
			
			return "mypage";
		}else {
			
			return "redirect:/";
		}
		
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
	public String post_writepost(Post post_form, Post_img post_img_form, RedirectAttributes redirectattributes, HttpServletRequest request) throws Exception {
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
			
		}else {
			return "redirect:/";
		}
		
		redirectattributes.addAttribute("member", session_info.get().getMember_id());
		return "redirect:mypage";
	}
	
	@RequestMapping("/detailpost")
	public String detailpost() {
		
		return "detailpost";
	}
	
}
