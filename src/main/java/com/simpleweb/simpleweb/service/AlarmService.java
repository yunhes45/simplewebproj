package com.simpleweb.simpleweb.service;

import java.util.List;

import com.simpleweb.simpleweb.model.Alarm;

public interface AlarmService {
	// alarm chat
	void insertAlarm_chat(Alarm alarm, int chatroom_no, String my_id);

	List<Alarm> getAlarm(int member_no);
}
