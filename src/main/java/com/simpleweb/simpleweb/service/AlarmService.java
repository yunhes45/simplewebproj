package com.simpleweb.simpleweb.service;

import java.util.List;

import com.simpleweb.simpleweb.model.Alarm_chat;

public interface AlarmService {
	// alarm chat
	void insertAlarm_chat(Alarm_chat alarm, String my_id);

	List<Alarm_chat> getAlarm(int member_no);

	int getAlarm_count(int member_no);

	Alarm_chat getAlarm_info(int alarm_no);

	void deleteAlarm(Alarm_chat alarm_chat);
}
