package com.simpleweb.simpleweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Post;
import com.simpleweb.simpleweb.model.Post_img;

@Mapper
public interface BoardMapper {

	int insertPost(@Param("post") Post post);
	void insertPostImg(@Param("post_img") Post_img post_img);

}
