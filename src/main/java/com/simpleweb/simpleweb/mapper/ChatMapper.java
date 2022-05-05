package com.simpleweb.simpleweb.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Chat_filelist;
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

	List<Chatlog> getChat_log(@Param("chatroom_no") int chatroom_no);

	Optional<Chatroom> getChatroom_info(@Param("chatroom_no") int chatroom_no);

	List<Chatroom_member> getChatroom_member_include_check(@Param("member_no") int member_no, @Param("chatroom_no") int chatroom_no);

	int insertChatfile(@Param("chat_filelist") Chat_filelist chat_file);

	Optional<Chat_filelist> getChat_filename(@Param("chat_filelist_no") int chatfilePK);

}
