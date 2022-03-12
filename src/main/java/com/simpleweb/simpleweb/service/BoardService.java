package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

public interface BoardService {

	int insertPost(Post post);
	void insertPostImg(Post_img post_img);
	int getTotal_fileList(int member_no);
	List<Post> getPost_list(int member_no, int startPage, int onePageCnt);
	List<Post> getPost_list_algo(int startPage, int onePageCnt);
	List<Like_stat> getLike_list(int post_no);
	List<List<String>> getPost_Like_list(List<Integer> post_list_no);
	List<String> getLike_check(List<List<String>> post_Like_list, String member_id);
	List<Like_stat> ex(int post_list_no);

}
