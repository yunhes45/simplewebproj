package com.simpleweb.simpleweb.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
