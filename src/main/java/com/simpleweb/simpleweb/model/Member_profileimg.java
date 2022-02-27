package com.simpleweb.simpleweb.model;

import org.springframework.web.multipart.MultipartFile;

public class Member_profileimg {
	private int member_profileimg_no;
	private int member_no;
	private String member_profileimg_filename;
	private String member_profileimg_original_filename;
	private String member_profileimg_url;
	private String member_profileimg_date;
	
	public int getMember_profileimg_no() {
		return member_profileimg_no;
	}
	public void setMember_profileimg_no(int member_profileimg_no) {
		this.member_profileimg_no = member_profileimg_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_profileimg_filename() {
		return member_profileimg_filename;
	}
	public void setMember_profileimg_filename(String member_profileimg_filename) {
		this.member_profileimg_filename = member_profileimg_filename;
	}
	public String getMember_profileimg_original_filename() {
		return member_profileimg_original_filename;
	}
	public void setMember_profileimg_original_filename(String member_profileimg_original_filename) {
		this.member_profileimg_original_filename = member_profileimg_original_filename;
	}
	public String getMember_profileimg_url() {
		return member_profileimg_url;
	}
	public void setMember_profileimg_url(String member_profileimg_url) {
		this.member_profileimg_url = member_profileimg_url;
	}
	public String getMember_profileimg_date() {
		return member_profileimg_date;
	}
	public void setMember_profileimg_date(String member_profileimg_date) {
		this.member_profileimg_date = member_profileimg_date;
	}

	MultipartFile memberimg;
	
	public MultipartFile getMemberimg() {
		return memberimg;
	}
	public void setMemberimg(MultipartFile memberimg) {
		this.memberimg = memberimg;
	}

}
