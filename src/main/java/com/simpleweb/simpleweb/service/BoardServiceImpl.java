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
	public List<List<Like_stat>> getPost_Like_list(List<Integer> post_list_no) {
		
		List<List<Like_stat>> getpost_like_list = new ArrayList<>();
		try {
			for(int i = 0; i < post_list_no.size(); i++) {
				List<Like_stat> likestat = boardmapper.getLike_list(post_list_no.get(i));
			
				getpost_like_list.add(likestat);
				
			}
		}catch(IndexOutOfBoundsException e) {
	
		}
		return getpost_like_list;
	}

	@Override
	public List<Integer> getLike_cnt(List<List<Like_stat>> post_Like_list) {
		List<Integer> Like_cnt = new ArrayList<Integer>();
		for(int i = 0; i < post_Like_list.size(); i++) {
			Like_cnt.add(post_Like_list.get(i).size());
		}
		
		return Like_cnt;
	}
	
	@Override
	public List<String> getLike_check(List<Integer> like_cnt, List<List<Like_stat>> post_Like_list, String member_id) {
		List<String> like_check    = new ArrayList<String>();
		
		for(int i = 0; i < like_cnt.size(); i++) {
			String check = null;
			
			for(int j = 0; j < post_Like_list.get(i).size(); j++) {
				if(post_Like_list.get(i).get(j).getMember().getMember_id().contains(member_id)) {
					check = "O";
				}
			}
			like_check.add(check);
			System.out.println("like_check : " + like_check);
		}
			
		return like_check;
	}

}
