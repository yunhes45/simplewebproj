package com.simpleweb.simpleweb.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class CommonController {
	
	@Autowired
	CommonService commonservice;
	
	@ResponseBody
	@GetMapping("/memberimg/{filename}")
	public Resource member_downloadImage(@PathVariable String filename) throws MalformedURLException{

		return new UrlResource("file:///" + commonservice.getMember_file_FullPath(filename));
		
	}
	
	@ResponseBody
	@GetMapping("/postimg/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException{

		return new UrlResource("file:///" + commonservice.getPost_file_FullPath(filename));
		
	}
	
	@ResponseBody
	@GetMapping("/chatfile/{filename}")
	public Resource chat_downloadImage(@PathVariable String filename) throws MalformedURLException{
		
		return new UrlResource("file:///" + commonservice.getChat_file_FullPath(filename));
		
	}
	
}
