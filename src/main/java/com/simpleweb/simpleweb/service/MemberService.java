package com.simpleweb.simpleweb.service;

import java.util.Optional;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;

public interface MemberService {

	int insertMember(Member member);
	void insertMemberimg(Member_profileimg memberimg);
	
	String getMemberLogin(Member member);
	
	Optional<Member> getMyInfo(int member_no);
	
	int updateMember(Member member);
	void updateMemberimg(Member_profileimg member_img);
	
	void deleteMember(int member_no);

}
