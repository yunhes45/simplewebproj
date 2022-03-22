package com.simpleweb.simpleweb.model;

public class Follow {
	private int follow_no;
	private int member_no;
	private int follow_member_no;
	private String follow_date;
	
	public int getFollow_no() {
		return follow_no;
	}
	public void setFollow_no(int follow_no) {
		this.follow_no = follow_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getFollow_member_no() {
		return follow_member_no;
	}
	public void setFollow_member_no(int follow_member_no) {
		this.follow_member_no = follow_member_no;
	}
	public String getFollow_date() {
		return follow_date;
	}
	public void setFollow_date(String follow_date) {
		this.follow_date = follow_date;
	}
	
	private Member member;
	private Member_profileimg member_profileimg;

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Member_profileimg getMember_profileimg() {
		return member_profileimg;
	}
	public void setMember_profileimg(Member_profileimg member_profileimg) {
		this.member_profileimg = member_profileimg;
	}	
	
}
