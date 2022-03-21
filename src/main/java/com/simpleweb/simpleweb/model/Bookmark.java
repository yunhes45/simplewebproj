package com.simpleweb.simpleweb.model;

public class Bookmark {
	private int bookmark_no;
	private int member_no;
	private int post_no;
	private int bookmark_check;
	private String bookmark_date;
	private int bookmark_count;
	
	public int getBookmark_no() {
		return bookmark_no;
	}
	public void setBookmark_no(int bookmark_no) {
		this.bookmark_no = bookmark_no;
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
	public int getBookmark_check() {
		return bookmark_check;
	}
	public void setBookmark_check(int bookmark_check) {
		this.bookmark_check = bookmark_check;
	}
	public String getBookmark_date() {
		return bookmark_date;
	}
	public void setBookmark_date(String bookmark_date) {
		this.bookmark_date = bookmark_date;
	}
	public int getBookmark_count() {
		return bookmark_count;
	}
	public void setBookmark_count(int bookmark_count) {
		this.bookmark_count = bookmark_count;
	}
	
	private Member member;
	private Member_profileimg member_profileimg;
	private Post post;
	private Post_img post_img;

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
	public Post_img getPost_img() {
		return post_img;
	}
	public void setPost_img(Post_img post_img) {
		this.post_img = post_img;
	}
	
	
}
