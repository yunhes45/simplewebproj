package com.simpleweb.simpleweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardFuncMapper;
import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Comment_like_stat;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Follow;

@Service
public class BoardFuncServiceImpl implements BoardFuncService{

	@Autowired
	BoardFuncMapper boardfuncmapper;
	
	@Override
	public Map LikeLogic(int member_no, int post_no, int i) {
		Optional<Like_stat> validlikecheck = boardfuncmapper.validlikecheck(member_no, post_no, i);
		Map<String, Integer> likelogic = new HashMap<String, Integer>();
		int likestat = -1;
		
		try {
			int excep = validlikecheck.get().getLike_stat_no();
			boardfuncmapper.deleteLike_stat(member_no, post_no, i);
			
			likestat = 1;
			if(likestat == 1) {
				likelogic.put("likestat", likestat);
				likelogic.put("likecount", getLikeCount(post_no));
			}
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertLike_stat(member_no, post_no, i);			
			
			likestat = 0;
			if(likestat == 0) {
				likelogic.put("likestat", likestat);
				likelogic.put("likecount", getLikeCount(post_no));
			}
		}
		
		return likelogic;

	}
	private int getLikeCount(int post_no) {
		int getlikecount = boardfuncmapper.getLikeCount(post_no);
		
		return getlikecount;
	}
	
	@Override
	public Map<String, Integer> BookmarkLogic(int member_no, int post_no, int i, String nowTime) {
		Optional<Bookmark> validbookmarkcheck = boardfuncmapper.validbookmarkcheck(member_no, post_no, i);
		Map<String, Integer> bookmarklogic = new HashMap<String, Integer>();
		int bookmarkstat = -1;

		try {
			int excep = validbookmarkcheck.get().getBookmark_no();
			boardfuncmapper.deleteBookmark(member_no, post_no, i);
			
			bookmarkstat = 1;
			if(bookmarkstat == 1) {
				bookmarklogic.put("bookmarkstat", bookmarkstat);
				bookmarklogic.put("bookmarkcount", getBookmarkCount(post_no));
			}
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertBookmark(member_no, post_no, i, nowTime);			
			
			bookmarkstat = 0;
			if(bookmarkstat == 0) {
				bookmarklogic.put("bookmarkstat", bookmarkstat);
				bookmarklogic.put("bookmarkcount", getBookmarkCount(post_no));
			}
		}
		
		return bookmarklogic;
	}
	private int getBookmarkCount(int post_no) {
		int getbookmarkcount = boardfuncmapper.getBookmarkCount(post_no);
		
		return getbookmarkcount;		
	}
	
	@Override
	public Optional<Comment> CommentLogic(int member_no, int post_no, String comment_text, String comment_date) {
		Comment comment = new Comment();
		comment.setMember_no(member_no);
		comment.setPost_no(post_no);
		comment.setComment_text(comment_text);
		comment.setComment_date(comment_date);
		
		boardfuncmapper.insertComment(comment);

		int commentPK = comment.getComment_no();
		
		Optional<Comment> comment_list = boardfuncmapper.getComment_list(commentPK);
		
		return comment_list;
	}
	@Override
	public int delcomment(int comment_no) {
		
		return boardfuncmapper.delcomment(comment_no);
	}
	
	@Override
	public Map<String, Integer> LikeCommentLogic(int member_no, int comment_no, int i, String nowTime) {
		Optional<Comment_like_stat> validcommentlikecheck = boardfuncmapper.validcommentlikecheck(member_no, comment_no, i);
		Map<String, Integer> likelogic = new HashMap<String, Integer>();
		int comment_likestat = -1;
		
		try {
			int excep = validcommentlikecheck.get().getComment_like_stat_no();
			boardfuncmapper.deleteComment_Like_stat(member_no, comment_no, i);
			
			comment_likestat = 1;
			if(comment_likestat == 1) {
				likelogic.put("comment_likestat", comment_likestat);
				likelogic.put("comment_likecount", getCommentLikeCount(comment_no));
			}
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertComment_Like_stat(member_no, comment_no, i, nowTime);			
			
			comment_likestat = 0;
			if(comment_likestat == 0) {
				likelogic.put("comment_likestat", comment_likestat);
				likelogic.put("comment_likecount", getCommentLikeCount(comment_no));
			}
		}
		
		return likelogic;
	}
	private int getCommentLikeCount(int comment_no) {
		int getcommentlikecount = boardfuncmapper.getCommentLikeCount(comment_no);
		
		return getcommentlikecount;
	}
	
	@Override
	public int FollowCheck(int member_no, int follow_member_no, String nowTime) {
		Follow follow = new Follow();
		follow.setMember_no(member_no);
		follow.setFollow_member_no(follow_member_no);
		follow.setFollow_date(nowTime);
		
		Optional<Follow> validfollowcheck = boardfuncmapper.validfollowcheck(follow);

		int followcheck = -1;

		try {
			int excep = validfollowcheck.get().getFollow_no();
			boardfuncmapper.deleteFollow(follow);
			
			followcheck = 1;
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertFollow(follow);			
			
			followcheck = 0;
		}
		
		return followcheck;
	}

}
