package com.simpleweb.simpleweb.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpleweb.simpleweb.model.Alarm_chat;
import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.service.AlarmService;
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
	
	@Autowired
	AlarmService alarmservice;
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request, Member member_form) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");

		if(session_info != null) {
			
			// my info
			model.addAttribute("session_info", session_info);
			
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
			@PathVariable String chatroom_no,
			@RequestParam(value = "member_no", required=false) String socket_member_no,
			@RequestParam(value = "chatroom_no", required=false) String socket_chatroom_no,
			@RequestParam(value = "msg", required=false) String socket_msg,
			@RequestParam(value = "division", required=false) String socket_division,
			@RequestParam(value = "nowTimesdate", required=false) String socket_nowTimesdate,
			@RequestParam(value = "nowTimestime", required=false) String socket_nowTimestime,
			@RequestParam(value = "nowTimes", required=false) String socket_nowTimes,
			@RequestParam(value = "chat_alarm_cnt", required=false) String socket_chat_alarm_cnt  ) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
	
		if(session_info != null) {
			// 없는 chatroom_no 에 대한 redirect
			String chatroom_no_check = chatservice.chatroom_check(Integer.parseInt(chatroom_no));

			if(chatroom_no_check.equals("0")) {
				return "redirect:/chat";
			}
			
			// 비정상적 접근 제어 (채팅방 미포함 member 접근 시 redirect)
			String chatroom_member_include_check = chatservice.getChatroom_member_include_check(session_info.get().getMember_no(), Integer.parseInt(chatroom_no));
			if(chatroom_member_include_check.equals("reject")) {
				return "redirect:chat";
			}
			
				// my info
				model.addAttribute("session_info", session_info);
				
				// chatroom_no
				model.addAttribute("chatroom_no", chatroom_no);
				
				// this chatroom info
				Optional<Chatroom> chatroom_info = chatservice.getChatroom_info(Integer.parseInt(chatroom_no));
				model.addAttribute("chatroom_info", chatroom_info);
		
				// follow my list
				List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
				model.addAttribute("Follow_my_list", Follow_my_list);
			
				// get chatroom
				List<Chatroom_member> chatroom_list = chatservice.getChatroom_list(session_info.get().getMember_no());
				model.addAttribute("chatroom_list", chatroom_list);
				
				// get chatroom_member(except me)
				List<Chatroom_member> chatroom_member_list = chatservice.getChatroom_member_list(session_info.get().getMember_no(), Integer.parseInt(chatroom_no));
				model.addAttribute("chatroom_member_list", chatroom_member_list);
				
				// get not invite follow member
				List<Member> not_invite_member = chatservice.getInvite_member_list(Follow_my_list, chatroom_member_list);
				model.addAttribute("not_invite_member", not_invite_member);
				
				// get log
				List<Chatlog> getchat_Log = chatservice.getChat_log(Integer.parseInt(chatroom_no));
				model.addAttribute("getchat_Log", getchat_Log);
	
				// log insert Text
				Chatlog insertLog = new Chatlog();

				if(socket_msg != null) {

					try {
							insertLog.setMember_no(Integer.parseInt(socket_member_no));
							insertLog.setChatroom_no(Integer.parseInt(socket_chatroom_no));
							insertLog.setChatlog_log(socket_msg);
							insertLog.setChatlog_division(socket_division);
							insertLog.setChatlog_split_date(socket_nowTimesdate);
							insertLog.setChatlog_split_time(socket_nowTimestime);
							insertLog.setChatlog_date(socket_nowTimes);
							
							chatservice.insertLog(insertLog);

					}catch(NullPointerException e) {
						
					}
					
					// chat header alarm insert
					Alarm_chat alarm = new Alarm_chat();
					
					try {
						alarm.setAlarm_member_no(Integer.parseInt(socket_member_no));
						alarm.setAlarm_division("chat");
						alarm.setAlarm_contents_pk(insertLog.getChatlog_no());
						alarm.setChatroom_no(Integer.parseInt(socket_chatroom_no));
						
						alarmservice.insertAlarm_chat(alarm, session_info.get().getMember_id());

					}catch(NumberFormatException e) {
						
					}
					
				}
				
				// chat alarm update
				try {
					Chatroom_member chatroom_member = new Chatroom_member();
					chatroom_member.setMember_no(Integer.parseInt(socket_member_no));
					chatroom_member.setChatroom_no(Integer.parseInt(socket_chatroom_no));
					chatroom_member.setChatroom_alarm(Integer.parseInt(socket_chat_alarm_cnt));
					
					chatservice.updateAlarm(session_info.get().getMember_id() ,chatroom_member);
				
				}catch(NumberFormatException e){
					
				}
				
				// alarm delete
//  				try {
//					Alarm_chat alarm_chat = new Alarm_chat();
//					alarm_chat.setAlarm_member_no(session_info.get().getMember_no());
//					alarm_chat.setChatroom_no(Integer.parseInt(socket_chatroom_no));
//					
//					alarmservice.deleteAlarm_chat(alarm_chat);
//					
//				}catch(NumberFormatException e) {
//					
//				}
				
			return "chat";
		
		}else {
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@PostMapping("upload_chat_file")
	public Map post_upload_chat_file(HttpServletRequest request,
			@RequestParam("chatroom_no") String chatroom_no,
			@RequestParam("chat_file") MultipartFile chat_file) throws Exception {

		String trim_chatroom_no = chatroom_no.trim();
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");

		Chat_filelist chat_filelist = new Chat_filelist();
		chat_filelist.setMember_no(session_info.get().getMember_no());
		chat_filelist.setChatroom_no(Integer.parseInt(trim_chatroom_no));
		chat_filelist.setChat_file(chat_file);
		chat_filelist.setChat_filelist_date(commonservice.nowTime());
		
		chat_filelist = commonservice.chat_filelogic(chat_filelist);	
		chatservice.insertChat_file(chat_filelist);
		
		int chatfilePK = chat_filelist.getChat_filelist_no();
		
		Map<String, String> chat_filename = new HashMap<>();
		chat_filename = chatservice.getChat_fileinfo(chatfilePK);

		return chat_filename;
	}
	
	@PostMapping("/chatinvite")
	public String post_chatinvite() {
		
		return null;
	}
	
	@PostMapping("/move_detail_chat")
	public String post_move_detail_chat(HttpServletRequest request, Alarm_chat alarm_form) {
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		Alarm_chat alarm_chat = new Alarm_chat();
		alarm_chat.setAlarm_member_no(session_info.get().getMember_no());
		alarm_chat.setChatroom_no(alarm_form.getChatroom_no());
		
		alarmservice.deleteAlarm_chat(alarm_chat);
		
		
		return "redirect:chat/m/"+alarm_form.getChatroom_no();
	}
	
}
