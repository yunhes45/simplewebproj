package com.simpleweb.simpleweb.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Comment_like_stat;
import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.model.Like_stat;

@Mapper
public interface BoardFuncMapper {

	Optional<Like_stat> validlikecheck(@Param("like_stat") Like_stat like_stat);
	void insertLike_stat(@Param("like_stat") Like_stat like_stat);
	void deleteLike_stat(@Param("like_stat") Like_stat like_stat);
	int getLikeCount(@Param("post_no") int post_no);

	Optional<Bookmark> validbookmarkcheck(@Param("bookmark") Bookmark bookmark);	
	void insertBookmark(@Param("bookmark") Bookmark bookmark);	
	void deleteBookmark(@Param("bookmark") Bookmark bookmark);
	int getBookmarkCount(@Param("post_no") int post_no);
	
	int insertComment(@Param("comment") Comment comment);
	Optional<Comment> getComment_list(@Param("comment_no") int commentPK);
	int delcomment(@Param("comment_no") int comment_no);
	
	Optional<Comment_like_stat> validcommentlikecheck(@Param("comment_like_stat") Comment_like_stat comment_like_stat);
	
	void insertComment_Like_stat(@Param("comment_like_stat") Comment_like_stat comment_like_stat);
	void deleteComment_Like_stat(@Param("comment_like_stat") Comment_like_stat comment_like_stat);
	int getCommentLikeCount(@Param("comment_no") int comment_no);
	
	Optional<Follow> validfollowcheck(@Param("Follow") Follow follow);
	
	void insertFollow(@Param("Follow") Follow follow);
	void deleteFollow(@Param("Follow") Follow follow);

}
