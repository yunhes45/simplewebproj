package com.simpleweb.simpleweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Member;

@Mapper
public interface MemberMapper {

	int insertMember(@Param("member") Member member);

}
