package com.simpleweb.simpleweb.model;

import org.springframework.web.multipart.MultipartFile;

public class Post_img {
	private int post_img_no;
	private int post_no;
	private String post_img_filename;
	private String post_img_original_filename;
	private String post_img_url;
	private String post_img_date;
	
	public int getPost_img_no() {
		return post_img_no;
	}
	public void setPost_img_no(int post_img_no) {
		this.post_img_no = post_img_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getPost_img_filename() {
		return post_img_filename;
	}
	public void setPost_img_filename(String post_img_filename) {
		this.post_img_filename = post_img_filename;
	}
	public String getPost_img_original_filename() {
		return post_img_original_filename;
	}
	public void setPost_img_original_filename(String post_img_original_filename) {
		this.post_img_original_filename = post_img_original_filename;
	}
	public String getPost_img_url() {
		return post_img_url;
	}
	public void setPost_img_url(String post_img_url) {
		this.post_img_url = post_img_url;
	}
	public String getPost_img_date() {
		return post_img_date;
	}
	public void setPost_img_date(String post_img_date) {
		this.post_img_date = post_img_date;
	}
	
	MultipartFile postimg;

	public MultipartFile getPostimg() {
		return postimg;
	}
	public void setPostimg(MultipartFile postimg) {
		this.postimg = postimg;
	}
	
	
}
