package com.simpleweb.simpleweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Alarm_chat;

@Mapper
public interface AlarmMapper {
	void insertAlarm_chat(@Param("alarm") Alarm_chat alarm, @Param("member_no") int member_no);

	List<Alarm_chat> getAlarm_chat(@Param("member_no") int member_no);

	int getAlarm_chat_count(@Param("member_no") int member_no);

	Alarm_chat getAlarm_chat_info(@Param("alarm_no") int alarm_no);

	void deleteAlarm_chat(@Param("alarm_chat") Alarm_chat alarm_chat);
	
}
