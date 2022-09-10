package com.simpleweb.simpleweb.model;

public class Alarm {
	private int alarm_no;
	private int member_no;
	private String alarm_division;
	private int alarm_contents_pk;
	
	public int getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(int alarm_no) {
		this.alarm_no = alarm_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
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
