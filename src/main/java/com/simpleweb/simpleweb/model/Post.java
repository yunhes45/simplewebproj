package com.simpleweb.simpleweb.model;

public class Post {
	private int post_no;
	private int member_no;
	private String post_title;
	private String post_subtitle;
	private String post_contents;
	private String post_date;
	
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_subtitle() {
		return post_subtitle;
	}
	public void setPost_subtitle(String post_subtitle) {
		this.post_subtitle = post_subtitle;
	}
	public String getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	private Member member;
	private Post_img post_img;
	private Like_stat like_stat;
	private Bookmark bookmark;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Post_img getPost_img() {
		return post_img;
	}
	public void setPost_img(Post_img post_img) {
		this.post_img = post_img;
	}
	public Like_stat getLike_stat() {
		return like_stat;
	}
	public void setLike_stat(Like_stat like_stat) {
		this.like_stat = like_stat;
	}
	public Bookmark getBookmark() {
		return bookmark;
	}
	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
	
	
}
