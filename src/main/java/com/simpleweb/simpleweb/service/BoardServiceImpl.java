package com.simpleweb.simpleweb.service;

import java.util.List;

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

	@Override
	public int getTotal_fileList(int member_no) {
		int getTotal_fileList = boardmapper.getTotal_fileList(member_no);
		
		return getTotal_fileList;
	}

	@Override
	public List<Post> getPost_list(int member_no, int startPage, int onePageCnt) {
		
		return boardmapper.getPost_list(member_no, startPage, onePageCnt);
	}

	@Override
	public List<Post> getPost_listAll(int startPage, int onePageCnt) {
		
		return boardmapper.getPost_listAll(startPage, onePageCnt);
	}

}
