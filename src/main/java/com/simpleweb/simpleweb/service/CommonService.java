package com.simpleweb.simpleweb.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;

public interface CommonService {

	String nowTime();

	Member_profileimg normalimglogic(int memberPK) throws Exception;
	Member_profileimg imglogic(int memberPK, MultipartFile files) throws Exception;
	Post_img post_imglogic(int postPK, MultipartFile files) throws Exception;
	Chat_filelist chat_filelogic(Chat_filelist chat_filelist) throws Exception;
	
	int getMember_no(String member_id);

	ResponseEntity<Object> downloadFileLogic(Post_img post_img) throws IOException, URISyntaxException;
	ResponseEntity<Object> downloadChatFormFileLogic(Chat_filelist chat_filelist) throws IOException, URISyntaxException;

	Object fileDir_path();
	String getMember_file_FullPath(String filename);
	String getPost_file_FullPath(String filename);
	String getChat_file_FullPath(String filename);

}
