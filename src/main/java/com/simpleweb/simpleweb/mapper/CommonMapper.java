package com.simpleweb.simpleweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;

@Mapper
public interface CommonMapper {
	Member_profileimg getMember_file_LoadPath(@Param("filename") String filename);
	Post_img getPost_file_LoadPath(@Param("filename") String filename);
	Chat_filelist getChat_file_LoadPath(@Param("filename") String filename);
	

}
