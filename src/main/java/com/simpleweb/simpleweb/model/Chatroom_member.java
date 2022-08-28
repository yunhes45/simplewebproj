package com.simpleweb.simpleweb.model;

public class Chatroom_member {
	private int chatroom_member_no;
	private int chatroom_no;
	private int member_no;
	private int chatroom_alarm;
	
	public int getChatroom_member_no() {
		return chatroom_member_no;
	}
	public void setChatroom_member_no(int chatroom_member_no) {
		this.chatroom_member_no = chatroom_member_no;
	}
	public int getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(int chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getChatroom_alarm() {
		return chatroom_alarm;
	}
	public void setChatroom_alarm(int chatroom_alarm) {
		this.chatroom_alarm = chatroom_alarm;
	}


	private Chatroom chatroom;

	public Chatroom getChatroom() {
		return chatroom;
	}
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
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
