package com.simpleweb.simpleweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Alarm;

@Mapper
public interface AlarmMapper {
	void insertAlarm_chat(@Param("alarm") Alarm alarm, @Param("member_no") int member_no);

	List<Alarm> getAlarm(@Param("member_no") int member_no);
	
}
