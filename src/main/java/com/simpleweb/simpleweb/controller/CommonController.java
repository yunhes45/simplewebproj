package com.simpleweb.simpleweb.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class CommonController {
	
	@Autowired
	CommonService commonservice;
	
	@ResponseBody
	@GetMapping("/memberimg/{filename}")
	public Resource member_downloadImage(@PathVariable String filename) throws MalformedURLException{
		String filepath = commonservice.getMember_file_LoadPath(filename);

		return new UrlResource("file:///" + filepath);
		
	}
	
	@ResponseBody
	@GetMapping("/postimg/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException{
		String filepath = commonservice.getPost_file_LoadPath(filename);
		
		return new UrlResource("file:///" + filepath);
		
	}
	
	@ResponseBody
	@GetMapping("/chatfile/{filename}")
	public Resource chat_downloadImage(@PathVariable String filename) throws MalformedURLException{
		String filepath = commonservice.getChat_file_LoadPath(filename);
		
		return new UrlResource("file:///" + filepath);
		
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
	
	@PostMapping("/downloadChatFormFile")
	public ResponseEntity<Object> downloadChatFormFile(Chat_filelist chat_filelist_form, RedirectAttributes redirectAttributes)
	throws IOException, URISyntaxException {
		Chat_filelist chat_filelist = new Chat_filelist();
		chat_filelist.setChat_filelist_original_filename(chat_filelist_form.getChat_filelist_original_filename());
		chat_filelist.setChat_filelist_filename(chat_filelist_form.getChat_filelist_filename());
		
		ResponseEntity<Object> res = new ResponseEntity<Object>(null, HttpStatus.OK);
		res = commonservice.downloadChatFormFileLogic(chat_filelist);
		
		return res;
	}
	
}
