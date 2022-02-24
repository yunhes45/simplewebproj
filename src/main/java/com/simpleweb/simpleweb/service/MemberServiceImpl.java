package com.simpleweb.simpleweb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
			throw new IllegalStateException("�̹� �����ϴ� ���̵� �Դϴ�.");
		});
		res = "�̹� �����ϴ� ���̵� �Դϴ�.";
		
		membermapper.getByEmail(member.getMember_email())
		.ifPresent(m -> {
			throw new IllegalStateException("�̹� �����ϴ� �̸��� �Դϴ�.");
		});
		res = "�̹� �����ϴ� �̸��� �Դϴ�.";
		
		return res;
	}
	
	@Override
	public String getMemberLogin(Member member) {
		String res = LoginCheck(member);
		
		return res;
	}
	private String LoginCheck(Member member) {
		Optional<Member> checkId = membermapper.getById(member.getMember_id());
		String res = null;
		
		try {
			if(member.getMember_id().equals(checkId.get().getMember_id()) && 
			   member.getMember_pwd().equals(checkId.get().getMember_pwd())) {
				res = "success";
			}else {
				res = "fail";
			}
		}catch(NoSuchElementException e) {
			res = "fail";
		}
		
		return res;
		
	}

}
