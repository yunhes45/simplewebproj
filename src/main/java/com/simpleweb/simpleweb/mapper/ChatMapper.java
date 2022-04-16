package com.simpleweb.simpleweb.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Chatlog;
import com.simpleweb.simpleweb.model.Chatroom;
import com.simpleweb.simpleweb.model.Chatroom_member;

@Mapper
public interface ChatMapper {

	int insertChatroom(@Param("chatroom") Chatroom chatroom);

	void insertChatroom_member(@Param("chatroom_member") Chatroom_member chatroom_member);

	List<Chatroom_member> getChatroom_list(@Param("member_no") int member_no);

	List<Chatroom_member> getChatroom_member_list(@Param("chatroom_no") int chatroom_no);

	void insertLog(@Param("chatlog") Chatlog insertLog);

}
