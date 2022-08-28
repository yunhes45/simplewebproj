package com.simpleweb.simpleweb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper membermapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	@Override
	public void insertMemberimg(Member_profileimg member_img) {
		
		membermapper.insertMemberImg(member_img);
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
			   passwordEncoder.matches(member.getMember_pwd(), checkId.get().getMember_pwd())){
			 //  member.getMember_pwd().equals(checkId.get().getMember_pwd())) {
				res = "success";
			}else {
				res = "fail";
			}
		}catch(NoSuchElementException e) {
			res = "fail";
		}
		
		return res;
		
	}
	
	@Override
	public Optional<Member> getMyInfo(int member_no) {
		Optional<Member> session = membermapper.getMyInfo(member_no);
		
		return session;
	}
	
	@Override
	public int updateMember(Member member) {
		update_validateDuplicateMember(member);
		int memberPK = membermapper.updateMember(member);
		
		return memberPK;
		
	}
	private String update_validateDuplicateMember(Member member) {
		String res = null;

		membermapper.getByEmail(member.getMember_email())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 이메일 입니다.");
		});
		res = "이미 존재하는 이메일 입니다.";
		
		return res;
	}
		
	@Override
	public void updateMemberimg(Member_profileimg member_img) {
		
		membermapper.updateMemberImg(member_img);
	}
	
	@Override
	public void deleteMember(int member_no) {
		
		membermapper.deleteMember(member_no);
	}
	@Override
	public String findid(Member member) {
		String res = null;
		
		try {
			Optional<Member> checkId = membermapper.findid(member);
			res = checkId.get().getMember_id();
		}catch(NoSuchElementException e) {
			res = "0";
		}
		
		return res;
	}
	
}
