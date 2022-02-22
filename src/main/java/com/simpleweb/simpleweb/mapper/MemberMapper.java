package com.simpleweb.simpleweb.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Member;

@Mapper
public interface MemberMapper {

	int insertMember(@Param("member") Member member);

	Optional<Member> getById(@Param("member_id") String member_id);
	Optional<Member> getByEmail(@Param("member_email") String member_email);
	
}
