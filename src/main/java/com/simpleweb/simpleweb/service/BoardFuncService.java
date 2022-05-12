package com.simpleweb.simpleweb.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Comment_like_stat;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Follow;

public interface BoardFuncService {

	Map<String, Integer> LikeLogic(Like_stat like_stat);
	Map<String, Integer> BookmarkLogic(Bookmark bookmark);
	Optional<Comment> CommentLogic(Comment comment);
	int delcomment(int comment_no);
	Map<String, Integer> LikeCommentLogic(Comment_like_stat comment_like_stat);
	int FollowCheck(int member_no, int follow_member_no, String nowTime);

}
