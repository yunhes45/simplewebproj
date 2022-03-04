package com.simpleweb.simpleweb.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
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

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.service.BoardService;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
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
	public String mainboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		return "mainboard";
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model, HttpServletRequest request, @RequestParam(value="page" , required=false) String page ) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			// file list page
			int file_listtotalcount = boardservice.getTotal_fileList(session_info.get().getMember_no());
			int startPage   = 0;
			int onePageCnt  = 10;
			int count       = (int)Math.ceil((double)file_listtotalcount/(double)onePageCnt);
		
			model.addAttribute("file_listtotalcount", file_listtotalcount);
			
//			int page_count[] = new int[count+1];
//			for(int i=1; i<=count; i++) {
//				page_count[i] += i;
//				System.out.println("page_count : " + page_count[i]);
//			}

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
			
			return "mypage";
		}else {
			
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/writepost")
	public String writepost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {

			
		}else {
			return "redirect:/";
		}
	
		return "writepost";
	}
	
	@PostMapping("/writepost")
	public String post_writepost(Post post_form, Post_img post_img_form, HttpServletRequest request) throws Exception {
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
		
		return "redirect:mypage";
	}
	
}
