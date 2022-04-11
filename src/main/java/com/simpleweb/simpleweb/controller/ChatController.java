package com.simpleweb.simpleweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.service.BoardService;
import com.simpleweb.simpleweb.service.ChatService;
import com.simpleweb.simpleweb.service.CommonService;

@Controller
public class ChatController {
	
	@Autowired
	BoardService boardservice;
	
	@Autowired
	ChatService chatservice;
	
	@Autowired
	CommonService commonservice;
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request, Member member_form) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		System.out.println(member_form.getMember_id());
		
		if(session_info != null) {
			if(member_form.getMember_id() != null) {
				
				Chatroom chatroom = new Chatroom();
				chatroom.setChatroom_name(member_form.getMember_id());
				
				// insert chatroom
				chatservice.insertChatroom(chatroom);			
				int chatroom_no = chatroom.getChatroom_no();

				// get invitemember id -> no
				int invitemember_no = commonservice.getMember_no(member_form.getMember_id());
				
				List<Integer> chatroom_member_belong = new ArrayList<>();
				chatroom_member_belong.add(session_info.get().getMember_no());
				chatroom_member_belong.add(invitemember_no);
				
				for(int i = 0; i < chatroom_member_belong.size(); i++) {
					Chatroom_member chatroom_member = new Chatroom_member();
					chatroom_member.setChatroom_no(chatroom_no);
					chatroom_member.setMember_no(chatroom_member_belong.get(i));
					
					chatservice.insertChatroom_member(chatroom_member);
				}
				
				return "redirect:chat";
			}
			
			// follow my list
			List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
			model.addAttribute("Follow_my_list", Follow_my_list);
			
			// get chatroom
			List<Chatroom_member> chatroom_list = chatservice.getChatroom_list(session_info.get().getMember_no());
			model.addAttribute("chatroom_list", chatroom_list);
			
			
		}else {
			
			return "redirect:/";
		}
		
		return "chat";
	}
	
	@RequestMapping("/chat/m/{chatroom_no}")
	public String chatroom(Model model, HttpServletRequest request,
			@PathVariable String chatroom_no) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		if(session_info != null) {
			
				
				// get chatroom
				List<Chatroom_member> chatroom_list = chatservice.getChatroom_list(session_info.get().getMember_no());
				model.addAttribute("chatroom_list", chatroom_list);
			
			return "chat";
		
		}else {
			return "redirect:/";
		}
	}
}
