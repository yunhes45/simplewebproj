package com.simpleweb.simpleweb.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Follow;

public interface BoardFuncService {

	Map<String, Integer> LikeLogic(int member_no, int post_no, int i);
	Map<String, Integer> BookmarkLogic(int member_no, int post_no, int i, String nowTime);
	Optional<Comment> CommentLogic(int member_no, int post_no, String comment_text, String nowTime);
	int delcomment(int comment_no);
	Map<String, Integer> LikeCommentLogic(int member_no, int comment_no, int i, String nowTime);
	int FollowCheck(int member_no, int follow_member_no, String nowTime);

}
