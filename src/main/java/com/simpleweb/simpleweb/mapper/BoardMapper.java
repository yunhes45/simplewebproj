package com.simpleweb.simpleweb.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Bookmark;
import com.simpleweb.simpleweb.model.Comment;
import com.simpleweb.simpleweb.model.Follow;
import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

@Mapper
public interface BoardMapper {

	int insertPost(@Param("post") Post post);
	void insertPostImg(@Param("post_img") Post_img post_img);
	int getTotal_fileList(@Param("member_no") int member_no);
	List<Post> getPost_list_algo(@Param("startPage") int startPage, @Param("onePageCnt") int onePageCnt);
	
	List<Post> getPost_list(
			@Param("member_no") int member_no,
			@Param("startPage") int startPage, 
			@Param("onePageCnt") int onePageCnt);
	
	int getTotal_bookmarkList(@Param("member_no") int member_no);
	List<Bookmark> getBookmark_list(
			@Param("member_no") int member_no,
			@Param("startPage") int startPage, 
			@Param("onePageCnt") int onePageCnt);
	
	List<Like_stat> getLike_list(@Param("post_no") int post_no);
	List<Bookmark> getPost_Bookmark_list(@Param("post_no") int post_no);
	List<Comment> getComment_list(@Param("post_no") int post_no);
	
	Optional<Post> getMemberPost(@Param("post_no") int post_no, @Param("member_no") int member_no);
	Optional<Like_stat> getMemberLike_check(
			@Param("post_no") int post_no,
			@Param("member_no") int member_no);
	
	List<Follow> getFollow_my_list(@Param("member_no") int member_no);
	List<Follow> getFollow_me_list(@Param("member_no") int member_no);

}
