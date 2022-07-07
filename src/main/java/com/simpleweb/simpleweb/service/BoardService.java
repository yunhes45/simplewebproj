package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;
import com.simpleweb.simpleweb.model.Post_hashtag;

public interface BoardService {

	int insertPost(Post post);
	void insertPostImg(Post_img post_img);
	void insertPostHashtag(Post_hashtag post_menu_hashtag);
	
	void deletePost(int post_no);
	
	// mainboard
	List<Post> getPost_list_algo(int startPage, int onePageCnt);
	List<Post> getPost_list_algo_search(int startPage, int onePageCnt, String search);
	
	List<List<Like_stat>> getPost_Like_list(List<Integer> post_list_no);
	List<Integer> getLike_cnt(List<List<Like_stat>> Post_Like_list);
	List<String> getLike_check(List<Integer> like_cnt, List<List<Like_stat>> post_Like_list, String member_id);
	
	List<List<Bookmark>> getPost_Bookmark_list(List<Integer> post_list_no);
	List<Integer> getBookmark_cnt(List<List<Bookmark>> post_Bookmark_list);
	List<String> getBookmark_check(List<Integer> bookmark_cnt, List<List<Bookmark>> post_Bookmark_list, String member_id);
	
	List<List<Comment>> getPost_Comment_list(List<Integer> post_list_no);
	List<Integer> getComment_cnt(List<List<Comment>> Post_Comment_list);
	List<String> getComment_Like_check(List<Integer> comment_cnt, List<List<Comment>> post_Comment_list,
			int member_no);	
	
	List<List<Post_hashtag>> getPostMenuHashtag(List<Integer> post_list_no, int i);
	List<Integer> getPost_menu_hashtag_cnt(List<List<Post_hashtag>> post_menu_hashtag);
	
	// mypage
	int getMyTotal_fileList(int member_no);
	List<Post> getMyPost_list(int member_no, int startPage, int onePageCnt);	
	int getMyTotal_bookmarkList(int member_no);
	List<Bookmark> getMyBookmark_list(int member_no, int startPage, int onePageCnt);
	
	// detail page
	Optional<Post> getMemberPost(int post_no, int member_no);
	
	List<Like_stat> getMemberPost_Like_list(int post_no);
	String getMemberLike_check(int post_no, int member_no);
	
	List<Bookmark> getMemberPost_Bookmark_list(int post_no);
	String getMemberBookmark_check(int post_no, int member_no);
	
	List<Comment> getMemberPost_Comment_list(int post_no);
	List<String> getMemberComment_Like_check(int post_no, int member_no);
	
//	List<String> getMemberFollow_check(int member_no);	
//	List<Post_hashtag> getMemberPostMenuHashtag(int parseInt, int i);
	
	
	// my follow info
	List<Member> getFollow_my_list(int member_no);
	List<Member> getFollow_me_list(int member_no);
	List<String> getFollow_check(List<Post> post_list, int member_no);
	
	// my page follow check
	String follow_check_mypage(int member_no , int mypage_follow_check_no);

}
