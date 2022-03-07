package com.simpleweb.simpleweb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
