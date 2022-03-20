package com.simpleweb.simpleweb.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Like_stat;

@Mapper
public interface BoardFuncMapper {

	Optional<Like_stat> validlikecheck(
			@Param("member_no")int member_no,
			@Param("post_no") int post_no,
			@Param("like_stat_check") int i);
	
	void insertLike_stat(
			@Param("member_no") int member_no,
			@Param("post_no") int post_no,
			@Param("like_stat_check")int i);

	void deleteLike_stat(
			@Param("member_no") int member_no,
			@Param("post_no") int post_no,
			@Param("like_stat_check")int i);

	int getLikeCount(@Param("post_no") int post_no);

	Optional<Bookmark> validbookmarkcheck(
			@Param("member_no") int member_no,
			@Param("post_no") int post_no,
			@Param("bookmark_check") int i);
	
	void insertBookmark(
			@Param("member_no") int member_no,
			@Param("post_no") int post_no,
			@Param("bookmark_check") int i,
			@Param("bookmark_date") String nowTime);
	
	void deleteBookmark(
			@Param("member_no") int member_no,
			@Param("post_no") int post_no,
			@Param("bookmark_check") int i);
	
	int getBookmarkCount(@Param("post_no") int post_no);
	
	int insertComment(@Param("comment") Comment comment);
	Optional<Comment> getComment_list(@Param("comment_no") int commentPK);
	int delcomment(@Param("comment_no") int comment_no);

}
