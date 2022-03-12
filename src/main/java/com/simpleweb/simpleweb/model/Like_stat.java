package com.simpleweb.simpleweb.model;

public class Like_stat {
	private int like_stat_no;
	private int member_no;
	private int post_no;
	private int like_stat_check;
	private int like_stat_count;
	
	public int getLike_stat_no() {
		return like_stat_no;
	}
	public void setLike_stat_no(int like_stat_no) {
		this.like_stat_no = like_stat_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getLike_stat_check() {
		return like_stat_check;
	}
	public void setLike_stat_check(int like_stat_check) {
		this.like_stat_check = like_stat_check;
	}
	public int getLike_stat_count() {
		return like_stat_count;
	}
	public void setLike_stat_count(int like_stat_count) {
		this.like_stat_count = like_stat_count;
	}
	
	private Member member;
	private Member_profileimg member_profileimg;
	private Post post;

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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

	
	
}
