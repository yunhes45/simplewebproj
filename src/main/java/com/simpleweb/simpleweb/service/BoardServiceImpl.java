package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardMapper;
import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Comment_like_stat;
import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.model.Post_hashtag;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardmapper;
	
	@Autowired
	MemberMapper membermapper;
	
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
	public void insertPostHashtag(Post_hashtag post_hashtag) {
		List<String> hashtag = posthashtagRegex(post_hashtag.getPost_hashtag_list());
		
		for(int i = 0; i < hashtag.size(); i++) {
			boardmapper.insertPostHashtag(post_hashtag.getPost_no(), hashtag.get(i), post_hashtag.getPost_hashtag_division());
		}
	}
	private List posthashtagRegex(String hashtagStr) {
		List<String> res = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*\\b");
		Matcher matcher = pattern.matcher(hashtagStr);
		
		while (matcher.find()){
		    System.out.println("regex : " + matcher.group(1)); 
		    res.add(matcher.group(1));
		} 		
			
		return res;
	}

	@Override
	public int getTotal_fileList(int member_no) {
		int getTotal_fileList = boardmapper.getTotal_fileList(member_no);
		
		return getTotal_fileList;
	}
	
	@Override
	public List<Post> getPost_list_algo(int startPage, int onePageCnt) {

		return boardmapper.getPost_list_algo(startPage, onePageCnt);
	}

	@Override
	public List<Post> getMyPost_list(int member_no, int startPage, int onePageCnt) {
		
		return boardmapper.getMyPost_list(member_no, startPage, onePageCnt);
	}
	
	@Override
	public List<Post> getPost_list_algo_search(int startPage, int onePageCnt, String search) {

		return boardmapper.getPost_list_algo_search(startPage, onePageCnt, search);
	}
	
	@Override
	public int getTotal_bookmarkList(int member_no) {
		int getTotal_bookmarkList = boardmapper.getTotal_bookmarkList(member_no);
		
		return getTotal_bookmarkList;
	}
	
	@Override
	public List<Bookmark> getBookmark_list(int member_no, int startPage, int onePageCnt) {

		return boardmapper.getBookmark_list(member_no, startPage, onePageCnt);
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
				List<Bookmark> bookmarkstat = boardmapper.getPost_Bookmark_list(post_list_no.get(i));
			
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
	public List<String> getComment_Like_check(List<Integer> comment_cnt, List<List<Comment>> post_Comment_list,
			int member_no) {
		List<String> comment_like_check    = new ArrayList<String>();
		List<Integer> post_Comment_list_no = new ArrayList<Integer>();
		
		for(int i = 0; i < comment_cnt.size(); i++) {
			for(int j = 0; j < post_Comment_list.get(i).size(); j++) {
				
				System.out.println("ddddddd : " + post_Comment_list.get(i).get(j).getComment_no());
				post_Comment_list_no.add(post_Comment_list.get(i).get(j).getComment_no());
			}
		}
		
		System.out.println(post_Comment_list_no);
		
		String check = null;
		
		for(int i = 0; i<post_Comment_list_no.size(); i++) {
			try {
				Optional<Comment_like_stat> getComment_like_stat = boardmapper.getComment_like_stat(member_no, post_Comment_list_no.get(i));
				System.out.println(getComment_like_stat);

				if(!getComment_like_stat.isEmpty()) {
					check = "O";
					comment_like_check.add(check);
				}else {
					check = "X";
					comment_like_check.add(check);
				}
			}catch(NoSuchElementException e) {

			}
		}
		
		System.out.println(comment_like_check);
			
		return comment_like_check;
	}
	
	@Override
	public List<List<Post_hashtag>> getPostMenuHashtag(List<Integer> post_list_no, int division) {
		
		List<List<Post_hashtag>> getPostMenuHashtag = new ArrayList<>();
		try {
			for(int i = 0; i < post_list_no.size(); i++) {
				List<Post_hashtag> comment = boardmapper.getHashtagList(post_list_no.get(i), division);
			
				getPostMenuHashtag.add(comment);
				
			}
		}catch(IndexOutOfBoundsException e) {
	
		}
		return getPostMenuHashtag;
	}

	@Override
	public List<Integer> getPost_menu_hashtag_cnt(List<List<Post_hashtag>> post_menu_hashtag) {
		List<Integer> Post_menu_hashtag_cnt = new ArrayList<Integer>();
		for(int i = 0; i < post_menu_hashtag.size(); i++) {
			Post_menu_hashtag_cnt.add(post_menu_hashtag.get(i).size());
		}
		
		return Post_menu_hashtag_cnt;
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
	public List<Bookmark> getMemberPost_Bookmark_list(int post_no) {

		return boardmapper.getPost_Bookmark_list(post_no);
	}

	@Override
	public String getMemberBookmark_check(int post_no, int member_no) {
		
		Optional<Bookmark> memberbookmark_check = boardmapper.getMemberBookmark_check(post_no, member_no);
		String check = null;
		
		try {
			System.out.println(memberbookmark_check.get().getPost_no());
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
	
	@Override
	public List<String> getMemberComment_Like_check(int post_no, int member_no) {
		
		List<String> membercomment_like_check    = new ArrayList<String>();
		List<Comment> post_comment_list = boardmapper.getMemberPostComment(post_no);
		
		String check = null;
		for(int i = 0; i < post_comment_list.size(); i++) {
			try {
					Optional<Comment_like_stat> getMemberComment_like_stat = boardmapper.getComment_like_stat(member_no, post_comment_list.get(i).getComment_no());
				
					if(!getMemberComment_like_stat.isEmpty()) {
						check = "O";
						membercomment_like_check.add(check);
					}else {
						check = "X";
						membercomment_like_check.add(check);
					}
			}catch(NoSuchElementException e) {
				
			}
		}
		
		return membercomment_like_check;
	}
	
	@Override
	public List<String> getMemberFollow_check(int member_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post_hashtag> getMemberPostMenuHashtag(int parseInt, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getFollow_my_list(int member_no) {
		List<Follow> follow_my_list_no = boardmapper.getFollow_my_list(member_no);
		List<Member> follow_my_list = new ArrayList<Member>();
	
		for(int i = 0; i < follow_my_list_no.size(); i++) {
			Optional<Member> follow_my_list_info = membermapper.getMyInfo(follow_my_list_no.get(i).getFollow_member_no());
			
			follow_my_list.add(follow_my_list_info.get());
		}
		
		return follow_my_list;
	}
	
	@Override
	public List<Member> getFollow_me_list(int member_no) {
		List<Follow> follow_me_list_no = boardmapper.getFollow_me_list(member_no);
		List<Member> follow_me_list = new ArrayList<Member>();
	
		for(int i = 0; i < follow_me_list_no.size(); i++) {
			System.out.println(follow_me_list_no.get(i).getMember_no());
			Optional<Member> follow_me_list_info = membermapper.getMyInfo(follow_me_list_no.get(i).getMember_no());
			
			follow_me_list.add(follow_me_list_info.get());
		}
		
		return follow_me_list;
	}

	@Override
	public List<String> getFollow_check(List<Post> post_list, int member_no) {
		List<String> follow_check = new ArrayList<String>();
		
		for(int i = 0; i < post_list.size(); i++) {
			Optional<Follow> check = boardmapper.followcheck(member_no, post_list.get(i).getMember_no());
			try {
				System.out.println(check.get().getFollow_no());
				follow_check.add("O");
						
			}catch(NoSuchElementException e) {
				follow_check.add("X");
			}
		}
			
		return follow_check;
	}

}
