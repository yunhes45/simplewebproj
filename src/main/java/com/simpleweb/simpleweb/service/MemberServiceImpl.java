package com.simpleweb.simpleweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper membermapper;
	
	@Override
	public int insertMember(Member member) {
		validateDuplicateMember(member);
		int memberPK = membermapper.insertMember(member);
		
		return memberPK;
	}
	private String validateDuplicateMember(Member member) {
		String res = null;
		
		membermapper.getById(member.getMember_id())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 아이디 입니다.");
		});
		res = "이미 존재하는 아이디 입니다.";
		
		membermapper.getByEmail(member.getMember_email())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 이메일 입니다.");
		});
		res = "이미 존재하는 이메일 입니다.";
		
		return res;
	}

}
