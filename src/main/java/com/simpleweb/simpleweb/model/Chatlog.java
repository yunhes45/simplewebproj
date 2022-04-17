package com.simpleweb.simpleweb.model;

public class Chatlog {
	private int chatlog_no;
	private int member_no;
	private int chatroom_no;
	private String chatlog_log;
	private String chatlog_division;
	private String chatlog_split_date;
	private String chatlog_split_time;
	private String chatlog_date;
	
	public int getChatlog_no() {
		return chatlog_no;
	}
	public void setChatlog_no(int chatlog_no) {
		this.chatlog_no = chatlog_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(int chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public String getChatlog_log() {
		return chatlog_log;
	}
	public void setChatlog_log(String chatlog_log) {
		this.chatlog_log = chatlog_log;
	}
	public String getChatlog_division() {
		return chatlog_division;
	}
	public void setChatlog_division(String chatlog_division) {
		this.chatlog_division = chatlog_division;
	}
	public String getChatlog_split_date() {
		return chatlog_split_date;
	}
	public void setChatlog_split_date(String chatlog_split_date) {
		this.chatlog_split_date = chatlog_split_date;
	}
	public String getChatlog_split_time() {
		return chatlog_split_time;
	}
	public void setChatlog_split_time(String chatlog_split_time) {
		this.chatlog_split_time = chatlog_split_time;
	}
	public String getChatlog_date() {
		return chatlog_date;
	}
	public void setChatlog_date(String chatlog_date) {
		this.chatlog_date = chatlog_date;
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
