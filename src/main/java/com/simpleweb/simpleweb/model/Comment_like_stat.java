package com.simpleweb.simpleweb.model;

public class Comment_like_stat {
	private int comment_like_stat_no;
	private int member_no;
	private int comment_no;
	private int comment_like_stat_check;
	private String comment_like_stat_date;
	
	public int getComment_like_stat_no() {
		return comment_like_stat_no;
	}
	public void setComment_like_stat_no(int comment_like_stat_no) {
		this.comment_like_stat_no = comment_like_stat_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getComment_like_stat_check() {
		return comment_like_stat_check;
	}
	public void setComment_like_stat_check(int comment_like_stat_check) {
		this.comment_like_stat_check = comment_like_stat_check;
	}
	public String getComment_like_stat_date() {
		return comment_like_stat_date;
	}
	public void setComment_like_stat_date(String comment_like_stat_date) {
		this.comment_like_stat_date = comment_like_stat_date;
	}

	private Member member;
	private Comment comment;

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
	
}
