package com.simpleweb.simpleweb.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.simpleweb.simpleweb.mapper.BoardMapper;
import com.simpleweb.simpleweb.mapper.ChatMapper;
import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;
import com.simpleweb.simpleweb.model.Follow;

@Service
public class ChatServiceImpl implements ChatService{

	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	@Autowired
	BoardMapper boardmapper;
	
	@Autowired
	ChatMapper chatmapper;

	@Override
	public int insertChatroom(Chatroom chatroom) {
		int chatroom_no = chatmapper.insertChatroom(chatroom);
		
		return chatroom_no;
	}

	@Override
	public void insertChatroom_member(Chatroom_member chatroom_member) {
		chatmapper.insertChatroom_member(chatroom_member);
		
	}

	@Override
	public List<Chatroom_member> getChatroom_list(int member_no) {

		return chatmapper.getChatroom_list(member_no);
	}

	@Override
	public List<Chatroom_member> getChatroom_member_list(int member_no, int chatroom_no) {
		List<Chatroom_member> exceptmeList = new ArrayList<>();
		
		List<Chatroom_member> getChatroom_member_list = chatmapper.getChatroom_member_list(chatroom_no);
		
		for(int i = 0; i < getChatroom_member_list.size(); i++) {
			if(getChatroom_member_list.get(i).getMember_no() != member_no) {
				exceptmeList.add(getChatroom_member_list.get(i));
			}
		}
		
		return exceptmeList;
	}

	@Override
	public void insertLog(Chatlog insertLog) {
		
		chatmapper.insertLog(insertLog);
	}

	@Override
	public List<Chatlog> getChat_log(int chatroom_no) {

		return chatmapper.getChat_log(chatroom_no);
	}

	@Override
	public Optional<Chatroom> getChatroom_info(int chatroom_no) {
		
		return chatmapper.getChatroom_info(chatroom_no);
	}

	@Override
	public String getChatroom_member_include_check(int member_no, int chatroom_no) {
		String res = chatroom_member_include_check(member_no, chatroom_no);
		
		return res;
	}
	private String chatroom_member_include_check(int member_no, int chatroom_no) {
		String res = null;
		
		List<Chatroom_member> include_member_list = chatmapper.getChatroom_member_list(chatroom_no);
		int member_no_list[] = new int[include_member_list.size()];
		
		for(int i = 0; i < include_member_list.size(); i++) {
			member_no_list[i] = include_member_list.get(i).getMember_no();
		}
		
		int member_match = 0;
		
		while(true) {
			if(member_no != member_no_list[member_match]) {
				member_match++;
				if(member_match == include_member_list.size()) {
					res = "reject";
					break;
				}
			}else {
				res = "ok";
				break;
			}
		}
		System.out.println("check : " + res);
		
		return res;
	}
	
	@Override
	public int insertChatfile(Chat_filelist chat_filelist) {
		Chat_filelist chat_file = new Chat_filelist();
		
		MultipartFile files = chat_filelist.getChat_file();
		
		String originalfilename            = files.getOriginalFilename();
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		 String fileurl    = "chatfile\\";
		// String fileurl    = "chatfile/";
		String savePath   = fileDir + fileurl;
		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
		}while(destinationfile.exists());
		
		try {
			files.transferTo(destinationfile);
		}catch(IOException e) {
			
		}

		chat_file.setChat_filelist_filename(destinationfilename);
		chat_file.setChat_filelist_original_filename(originalfilename);
		chat_file.setChat_filelist_url(savePath);
		chat_file.setMember_no(chat_filelist.getMember_no());
		chat_file.setChatroom_no(chat_filelist.getChatroom_no());
		chat_file.setChat_filelist_date(chat_filelist.getChat_filelist_date());
		
		chatmapper.insertChatfile(chat_file);
		int chatfilePK = chat_file.getChat_filelist_no();
		
		return chatfilePK;
	}

	@Override
	public Map getChat_fileinfo(int chatfilePK) {
		Optional<Chat_filelist> filelist = chatmapper.getChat_fileinfo(chatfilePK);
		
		Map<String, String> fileinfo = new HashMap<>();
		
		fileinfo.put("chat_filelist_no", Integer.toString(filelist.get().getChat_filelist_no()));
		fileinfo.put("chat_filelist_original_filename", filelist.get().getChat_filelist_original_filename());
		fileinfo.put("chat_filename", filelist.get().getChat_filelist_filename());
		
		return fileinfo;
	}

	@Override
	public List<Chatroom_member> getInvite_member_list(int member_no, int chatroom_no) {
		List<String> exceptChatroomList = new ArrayList<>();
		
		List<Follow> getFollow_my_list = boardmapper.getFollow_my_list(member_no);
		List<Chatroom_member> getChatroom_member_list = chatmapper.getChatroom_member_list(chatroom_no);
		
		List<String> getFollow_my_list_str = new ArrayList<>();
		List<String> getChatroom_member_list_str = new ArrayList<>();
		
		for(int i = 0; i < getFollow_my_list.size(); i++) {
			getFollow_my_list_str.add(Integer.toString(getFollow_my_list.get(i).getFollow_member_no()));
			System.out.println("1 : " + getFollow_my_list_str.get(i));
		}
		
		for(int i = 0; i < getChatroom_member_list.size(); i++) {
			getChatroom_member_list_str.add(Integer.toString(getChatroom_member_list.get(i).getMember_no()));
			System.out.println("2 : " + getChatroom_member_list_str.get(i));
		}
		
		for(int i = 0; i < getFollow_my_list_str.size(); i++) {
			
			for(int j = 0; j < getChatroom_member_list_str.size(); j++) {
				if(!getFollow_my_list_str.get(i).contains(getChatroom_member_list_str.get(j))) {
					exceptChatroomList.add(getFollow_my_list_str.get(i));
				}
			}
		}
		
		for(int i = 0; i < exceptChatroomList.size(); i++) {
			System.out.println("fff : " + exceptChatroomList.get(i));
		}

		
		return null;
	}

}
