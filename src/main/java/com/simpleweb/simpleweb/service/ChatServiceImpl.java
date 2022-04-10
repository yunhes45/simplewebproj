package com.simpleweb.simpleweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.ChatMapper;
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

}
