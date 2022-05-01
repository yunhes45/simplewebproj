package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.ChatMapper;
import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;

@Service
public class ChatServiceImpl implements ChatService{
	
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
		
//		List<Chatroom_member> include_member_list = chatmapper.getChatroom_member_include_check(member_no, chatroom_no);
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

}
