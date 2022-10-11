package com.simpleweb.simpleweb.model;

public class Alarm_chat {
	private int alarm_no;
	private int alarm_member_no;
	private String alarm_division;
	private int alarm_contents_pk;
	private int chatroom_no;
	private int alarm_group_count;
	private int alarm_count;
	
	public int getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(int alarm_no) {
		this.alarm_no = alarm_no;
	}
	public int getAlarm_member_no() {
		return alarm_member_no;
	}
	public void setAlarm_member_no(int alarm_member_no) {
		this.alarm_member_no = alarm_member_no;
	}
	public String getAlarm_division() {
		return alarm_division;
	}
	public void setAlarm_division(String alarm_division) {
		this.alarm_division = alarm_division;
	}
	public int getAlarm_contents_pk() {
		return alarm_contents_pk;
	}
	public void setAlarm_contents_pk(int alarm_contents_pk) {
		this.alarm_contents_pk = alarm_contents_pk;
	}
	public int getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(int chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public int getAlarm_group_count() {
		return alarm_group_count;
	}
	public void setAlarm_group_count(int alarm_group_count) {
		this.alarm_group_count = alarm_group_count;
	}
	public int getAlarm_count() {
		return alarm_count;
	}
	public void setAlarm_count(int alarm_count) {
		this.alarm_count = alarm_count;
	}

	private Member member;
	private Chatlog chatlog;
	private Like_stat like_stat;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Chatlog getChatlog() {
		return chatlog;
	}
	public void setChatlog(Chatlog chatlog) {
		this.chatlog = chatlog;
	}
	public Like_stat getLike_stat() {
		return like_stat;
	}
	public void setLike_stat(Like_stat like_stat) {
		this.like_stat = like_stat;
	}
	
}
