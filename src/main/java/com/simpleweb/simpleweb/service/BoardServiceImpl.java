package com.simpleweb.simpleweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardMapper;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardmapper;
	
	@Override
	public int insertPost(Post post) {
		
		int postPK = boardmapper.insertPost(post);
		
		return postPK;
	}

	@Override
	public void insertPostImg(Post_img post_img) {
		boardmapper.insertPostImg(post_img);
		
	}

}
