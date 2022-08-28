package com.simpleweb.simpleweb.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;

@Mapper
public interface MemberMapper {

	int insertMember(@Param("member") Member member);

	Optional<Member> getById(@Param("member_id") String member_id);
	Optional<Member> getByEmail(@Param("member_email") String member_email);

	void insertMemberImg(@Param("member_profileimg") Member_profileimg member_img);
	
	Optional<Member> getMyInfo(@Param("member_no") int member_no);

	int updateMember(@Param("member") Member member);
	void updateMemberImg(@Param("member_profileimg") Member_profileimg member_img);

	void deleteMember(@Param("member_no") int member_no);

	Optional<Member> findid(@Param("member") Member member);
	
}
