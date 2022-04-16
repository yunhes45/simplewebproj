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

}
