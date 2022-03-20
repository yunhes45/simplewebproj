package com.simpleweb.simpleweb.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;

public interface CommonService {

	String nowTime();

	Member_profileimg normalimglogic(int memberPK) throws Exception;
	Member_profileimg imglogic(int memberPK, MultipartFile files) throws Exception;
	Post_img post_imglogic(int postPK, MultipartFile files) throws Exception;
	
	int getMember_no(String member_id);

	ResponseEntity<Object> downloadFileLogic(Post_img post_img) throws IOException, URISyntaxException;

}
