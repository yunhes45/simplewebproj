package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardMapper;
import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
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
		}
			
		return like_check;
	}
	
	@Override
	public List<List<Bookmark>> getPost_Bookmark_list(List<Integer> post_list_no) {
		List<List<Bookmark>> getpost_bookmark_list = new ArrayList<>();
		try {
			for(int i = 0; i < post_list_no.size(); i++) {
				List<Bookmark> bookmarkstat = boardmapper.getBookmark_list(post_list_no.get(i));
			
				getpost_bookmark_list.add(bookmarkstat);
				
			}
		}catch(IndexOutOfBoundsException e) {
	
		}
		return getpost_bookmark_list;
	}

	@Override
	public List<Integer> getBookmark_cnt(List<List<Bookmark>> post_Bookmark_list) {
		List<Integer> Bookmark_cnt = new ArrayList<Integer>();
		for(int i = 0; i < post_Bookmark_list.size(); i++) {
			Bookmark_cnt.add(post_Bookmark_list.get(i).size());
		}
		
		return Bookmark_cnt;
	}

	@Override
	public List<String> getBookmark_check(List<Integer> bookmark_cnt, List<List<Bookmark>> post_Bookmark_list,
			String member_id) {
		List<String> bookmark_check    = new ArrayList<String>();
		
		for(int i = 0; i < bookmark_cnt.size(); i++) {
			String check = null;
			
			for(int j = 0; j < post_Bookmark_list.get(i).size(); j++) {
				if(post_Bookmark_list.get(i).get(j).getMember().getMember_id().contains(member_id)) {
					check = "O";
				}
			}
			bookmark_check.add(check);
		}
			
		return bookmark_check;
	}

	@Override
	public List<List<Comment>> getPost_Comment_list(List<Integer> post_list_no) {
		
		List<List<Comment>> getpost_comment_list = new ArrayList<>();
		try {
			for(int i = 0; i < post_list_no.size(); i++) {
				List<Comment> comment = boardmapper.getComment_list(post_list_no.get(i));
			
				getpost_comment_list.add(comment);
				
			}
		}catch(IndexOutOfBoundsException e) {
	
		}
		return getpost_comment_list;
	}

	@Override
	public List<Integer> getComment_cnt(List<List<Comment>> Post_Comment_list) {
		List<Integer> Comment_cnt = new ArrayList<Integer>();
		for(int i = 0; i < Post_Comment_list.size(); i++) {
			Comment_cnt.add(Post_Comment_list.get(i).size());
		}
		
		return Comment_cnt;
	}

	@Override
	public Optional<Post> getMemberPost(int post_no, int member_no) {
	
		return boardmapper.getMemberPost(post_no, member_no);
	}

	@Override
	public List<Like_stat> getMemberPost_Like_list(int post_no) {

		return boardmapper.getLike_list(post_no);
	}

	@Override
	public String getMemberLike_check(int post_no, int member_no) {
		
		Optional<Like_stat> memberlike_check = boardmapper.getMemberLike_check(post_no, member_no);
		String check = null;
		
		try {
			System.out.println(memberlike_check.get().getPost_no());
			check = "O";
		}catch(NoSuchElementException e) {
			check = "X";
		}
		
		return check;
	}

	@Override
	public List<Comment> getMemberPost_Comment_list(int post_no) {

		return boardmapper.getComment_list(post_no);
	}

}
