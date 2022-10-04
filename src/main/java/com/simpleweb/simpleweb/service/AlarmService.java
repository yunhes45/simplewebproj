package com.simpleweb.simpleweb.service;

import java.util.List;

import com.simpleweb.simpleweb.model.Alarm;

public interface AlarmService {
	// alarm chat
	void insertAlarm_chat(Alarm alarm, int chatroom_no, String my_id);

	List<Alarm> getAlarm(int member_no);

	int getAlarm_count(int member_no);

	Alarm getAlarm_info(int alarm_no);

	void deleteAlarm(int alarm_no);
}
