package com.simpleweb.simpleweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Like_stat;
import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

@Mapper
public interface BoardMapper {

	int insertPost(@Param("post") Post post);
	void insertPostImg(@Param("post_img") Post_img post_img);
	int getTotal_fileList(@Param("member_no") int member_no);
	List<Post> getPost_list(@Param("member_no") int member_no,
			@Param("startPage") int startPage, 
			@Param("onePageCnt") int onePageCnt);
	List<Post> getPost_list_algo(@Param("startPage") int startPage, @Param("onePageCnt") int onePageCnt);
	List<Like_stat> getLike_list(@Param("post_no") int post_no);
	List<Like_stat> getLike_stat(@Param("post_list_no") int post_list_no);

}
