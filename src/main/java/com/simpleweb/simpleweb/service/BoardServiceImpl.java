package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardMapper;
import com.simpleweb.simpleweb.model.Like_stat;
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
	public List<Post> getPost_list_algo(int startPage, int onePageCnt) {

		return boardmapper.getPost_list_algo(startPage, onePageCnt);
	}

	@Override
	public List<Like_stat> getLike_list(int post_no) {

		return boardmapper.getLike_list(post_no);
	}

	@Override
	public List<List<String>> getPost_Like_list(List<Integer> post_list_no) {
		List<List<String>> like_list_id = new ArrayList<>();
		try {
			for(int i = 0; i < post_list_no.size(); i++) {
				// get post like list
				List<Like_stat> likestat = boardmapper.getLike_stat(post_list_no.get(i));
				List<String> post_like_list = new ArrayList<>();
				
				for(int j = 0; j < likestat.size(); j++) {
					// get post one like list
					post_like_list.add(likestat.get(j).getMember().getMember_id());
					
				}
				
				like_list_id.add(post_like_list);

			}
		}catch(IndexOutOfBoundsException e) {
	
		}
		
		return like_list_id;
	}

	@Override
	public List<String> getLike_check(List<List<String>> post_Like_list, String member_id) {
		List<String> ex1 = new ArrayList<String>();
		for(int i = 0; i < post_Like_list.size(); i++) {
			String ex = null;
			for(int j = 0; j < post_Like_list.get(i).size(); j++) {
				if(post_Like_list.get(i).get(j).contains(member_id)) {
					ex = "O";
				}
			}
			ex1.add(ex);
			System.out.println(ex1);
		}
			
		return ex1;
	}


}
