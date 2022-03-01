package com.simpleweb.simpleweb.controller;

import java.net.MalformedURLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.service.BoardService;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class BoardController {
	
	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	public String getFullPath(String filename) {
		return fileDir + filename;
	}
	
	@Autowired
	BoardService boardservice;
	
	@Autowired
	CommonService commonservice;
	
	@RequestMapping("/mainboard")
	public String mainboard(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		return "mainboard";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
		}else {
			
		}
		
		return "mypage";
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
	
	@ResponseBody
	@GetMapping("/images1/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException{
		String fileurl    = "\\memberimg\\";
		return new UrlResource("file:///" + getFullPath(filename));
		
	}
	
}
