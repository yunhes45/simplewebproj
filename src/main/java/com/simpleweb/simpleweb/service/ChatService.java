package com.simpleweb.simpleweb.service;

import java.util.List;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;

public interface ChatService {

	int insertChatroom(Chatroom chatroom);
	void insertChatroom_member(Chatroom_member chatroom_member);
	List<Chatroom_member> getChatroom_list(int member_no);

}
