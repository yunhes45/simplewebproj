package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

public interface BoardService {

	int insertPost(Post post);
	void insertPostImg(Post_img post_img);
	int getTotal_fileList(int member_no);
	List<Post> getPost_list(int member_no, int startPage, int onePageCnt);
	List<Post> getPost_list_algo(int startPage, int onePageCnt);
	
	List<List<Like_stat>> getPost_Like_list(List<Integer> post_list_no);
	List<Integer> getLike_cnt(List<List<Like_stat>> Post_Like_list);
	List<String> getLike_check(List<Integer> like_cnt, List<List<Like_stat>> post_Like_list, String member_id);
	
	List<List<Comment>> getPost_Comment_list(List<Integer> post_list_no);
	List<Integer> getComment_cnt(List<List<Comment>> Post_Comment_list);
	
	Optional<Post> getMemberPost(int post_no, int member_no);
	List<Like_stat> getMemberPost_Like_list(int post_no);
	String getMemberLike_check(int post_no, int member_no);
	List<Comment> getMemberPost_Comment_list(int post_no);

}
