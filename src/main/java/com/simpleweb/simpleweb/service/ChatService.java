package com.simpleweb.simpleweb.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;
import com.simpleweb.simpleweb.model.Member;

public interface ChatService {

	int insertChatroom(Chatroom chatroom);
	void insertChatroom_member(Chatroom_member chatroom_member);
	List<Chatroom_member> getChatroom_list(int member_no);
	List<Chatroom_member> getChatroom_member_list(int member_no, int chatroom_no);
	List<Member> getInvite_member_list(List<Member> follow_my_list, List<Chatroom_member> chatroom_member_list);
	
	void insertLog(Chatlog insertLog);
	List<Chatlog> getChat_log(int chatroom_no);
	Optional<Chatroom> getChatroom_info(int chatroom_no);
	String chatroom_check(int chatroom_no);
	String getChatroom_member_include_check(int member_no, int chatroom_no);
	
	
	int insertChat_file(Chat_filelist chat_filelist);
	
	Map getChat_fileinfo(int chatfilePK);
	void updateAlarm(String my_id, Chatroom_member chatroom_member);

}
