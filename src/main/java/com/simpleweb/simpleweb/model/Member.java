package com.simpleweb.simpleweb.model;

public class Member {
	private int    member_no;
	private String member_id;
	private String member_pwd;
	private String member_email;
	private String member_nickname;
	private String member_mobile;
	private String member_job;
	private String member_gender;
	private String member_introduce;
	private String member_date;
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getMember_mobile() {
		return member_mobile;
	}
	public void setMember_mobile(String member_mobile) {
		this.member_mobile = member_mobile;
	}
	public String getMember_job() {
		return member_job;
	}
	public void setMember_job(String member_job) {
		this.member_job = member_job;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_introduce() {
		return member_introduce;
	}
	public void setMember_introduce(String member_introduce) {
		this.member_introduce = member_introduce;
	}
	public String getMember_date() {
		return member_date;
	}
	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}


	private Member_profileimg member_profileimg;

	public Member_profileimg getMember_profileimg() {
		return member_profileimg;
	}
	public void setMember_profileimg(Member_profileimg member_profileimg) {
		this.member_profileimg = member_profileimg;
	}
	
}
