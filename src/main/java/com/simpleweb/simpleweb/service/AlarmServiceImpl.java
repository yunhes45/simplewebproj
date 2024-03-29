package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.AlarmMapper;
import com.simpleweb.simpleweb.mapper.ChatMapper;
import com.simpleweb.simpleweb.model.Alarm_chat;
import com.simpleweb.simpleweb.model.Chatroom_member;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	AlarmMapper alarmmapper;
	@Autowired
	ChatMapper chatmapper;
	
	@Override
	public void insertAlarm_chat(Alarm_chat alarm, String my_id) {
		List<Integer> include_chatroom_member = new ArrayList<>();
		List<Chatroom_member> get_chatroom_member = chatmapper.getChatroom_member_list(alarm.getChatroom_no());
		
		for(int i = 0; i < get_chatroom_member.size(); i++) {
			if(!get_chatroom_member.get(i).getMember().getMember_id().contains(my_id)) {
				include_chatroom_member.add(get_chatroom_member.get(i).getMember_no());
			}
		}
		
		for(int j = 0; j < include_chatroom_member.size(); j++) {
			alarmmapper.insertAlarm_chat(alarm, include_chatroom_member.get(j));
		}
		
	}

	@Override
	public List<Alarm_chat> getAlarm_chat(int member_no) {

		return alarmmapper.getAlarm_chat(member_no);
	}

	@Override
	public int getAlarm_chat_count(int member_no) {

		return alarmmapper.getAlarm_chat_count(member_no);
	}

	@Override
	public Alarm_chat getAlarm_chat_info(int alarm_no) {
		
		return alarmmapper.getAlarm_chat_info(alarm_no);
	}

	@Override
	public void deleteAlarm_chat(Alarm_chat alarm_chat) {
		
		alarmmapper.deleteAlarm_chat(alarm_chat);
	}
	
}
