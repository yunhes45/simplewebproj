package com.simpleweb.simpleweb.service;

import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

public interface BoardService {

	int insertPost(Post post);
	void insertPostImg(Post_img post_img);

}
