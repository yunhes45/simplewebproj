package com.simpleweb.simpleweb.service;

import java.util.List;

import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

public interface BoardService {

	int insertPost(Post post);
	void insertPostImg(Post_img post_img);
	int getTotal_fileList(int member_no);
	List<Post> getPost_list(int member_no, int startPage, int onePageCnt);

}
