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

import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post_img;
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

	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	public String getFullPath(String fileurl, String filename) {
		return fileDir + fileurl + filename;
	}
	
	@ResponseBody
	@GetMapping("/chatfile/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException{
		String fileurl    = "chatfile\\";
		return new UrlResource("file:///" + getFullPath(fileurl, filename));
		
	}
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request, Member member_form) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		System.out.println(member_form.getMember_id());
		
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
			@RequestParam(value = "original_file_name", required=false) String socket_original_file_name,
			@RequestParam(value = "division", required=false) String socket_division,
			@RequestParam(value = "nowTimesdate", required=false) String socket_nowTimesdate,
			@RequestParam(value = "nowTimestime", required=false) String socket_nowTimestime,
			@RequestParam(value = "nowTimes", required=false) String socket_nowTimes  ) {
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");
		
		System.out.println("err : " + chatroom_no);
		
		if(session_info != null) {
			// 비정상적 접근 제어
			String chatroom_member_include_check = chatservice.getChatroom_member_include_check(session_info.get().getMember_no(), Integer.parseInt(chatroom_no));
			if(chatroom_member_include_check.equals("reject")) {
				return "redirect:/chat";
			}
			
				// my info
				model.addAttribute("session_info", session_info);
				
				// chatroom_no
				model.addAttribute("chatroom_no", chatroom_no);
				
				// this chatroom info
				Optional<Chatroom> chatroom_info = chatservice.getChatroom_info(Integer.parseInt(chatroom_no));
				model.addAttribute("chatroom_info", chatroom_info);
				
				System.out.println(chatroom_info.get().getChatroom_name());
				
				// follow my list
				List<Member> Follow_my_list = boardservice.getFollow_my_list(session_info.get().getMember_no());
				model.addAttribute("Follow_my_list", Follow_my_list);
			
				// get chatroom
				List<Chatroom_member> chatroom_list = chatservice.getChatroom_list(session_info.get().getMember_no());
				model.addAttribute("chatroom_list", chatroom_list);
				
				// get chatroom_member(except me)
				List<Chatroom_member> chatroom_member_list = chatservice.getChatroom_member_list(session_info.get().getMember_no(), Integer.parseInt(chatroom_no));
				model.addAttribute("chatroom_member_list", chatroom_member_list);
				
				// get log
				List<Chatlog> getchat_Log = chatservice.getChat_log(Integer.parseInt(chatroom_no));
				model.addAttribute("getchat_Log", getchat_Log);
				
				for(int i = 0; i < getchat_Log.size(); i++) {
					System.out.println("fff : " + getchat_Log.get(i).getChat_filelist().getChat_filelist_original_filename());
				}
				
				// log insert Text
				Chatlog insertLog = new Chatlog();
				
				// get chatroom_member(except chatroom member)
				List<Chatroom_member> invite_member_list = chatservice.getInvite_member_list(session_info.get().getMember_no(), Integer.parseInt(chatroom_no));
				model.addAttribute("invite_member_list", invite_member_list);
				
				if(socket_msg != null) {
					System.out.println("insert");
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
				}
			
			return "chat";
		
		}else {
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@PostMapping("upload_chat_file")
	public Map post_upload_chat_file(HttpServletRequest request,
			@RequestParam("chatroom_no") String chatroom_no,
			@RequestParam("chat_file") MultipartFile chat_file) {

		String trim_chatroom_no = chatroom_no.trim();
		
		HttpSession session = request.getSession();
		Optional<Member> session_info = (Optional<Member>) session.getAttribute("session_info");

		Chat_filelist chat_filelist = new Chat_filelist();
		chat_filelist.setMember_no(session_info.get().getMember_no());
		chat_filelist.setChatroom_no(Integer.parseInt(trim_chatroom_no));
		chat_filelist.setChat_file(chat_file);
		chat_filelist.setChat_filelist_date(commonservice.nowTime());
		
		int chatfilePK = chatservice.insertChatfile(chat_filelist);	
		
		Map<String, String> chat_filename = new HashMap<>();
		chat_filename = chatservice.getChat_fileinfo(chatfilePK);

		
		return chat_filename;
	}
	
	@PostMapping("/downloadChatFormFile")
	public ResponseEntity<Object> downloadChatFormFile(Chat_filelist chat_filelist_form, RedirectAttributes redirectAttributes)
	throws IOException, URISyntaxException {
		
		ResponseEntity<Object> res = new ResponseEntity<Object>(null, HttpStatus.OK);
		Chat_filelist chat_filelist = new Chat_filelist();
		chat_filelist.setChat_filelist_original_filename(chat_filelist_form.getChat_filelist_original_filename());
		chat_filelist.setChat_filelist_filename(chat_filelist_form.getChat_filelist_filename());
		
		res = commonservice.downloadChatFormFileLogic(chat_filelist);
		
		return res;
	}
	
	@PostMapping("/chatinvite")
	public String post_chatinvite() {
		
		return null;
	}
	
}
