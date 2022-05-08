package com.simpleweb.simpleweb.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	@Autowired
	MemberMapper membermapper;
	
	@Override
	public int getMember_no(String member_id) {
		Optional<Member> member_no = membermapper.getById(member_id); 
		
		return member_no.get().getMember_no();
	}

	@Override
	public String nowTime() {
		Date dateformat = new Date();
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String nowtime = simpledateformat.format(dateformat);
		
		return nowtime;
	}

	@Override
	public Member_profileimg normalimglogic(int memberPK) throws Exception{
		Member_profileimg memberimg = new Member_profileimg();
		String originalfilename   = "normal_img.png";
		// String fileurl    = "memberimg\\";
		String fileurl    = "memberimg/";
		String savePath   = fileDir + fileurl;
		
		memberimg.setMember_profileimg_filename            (originalfilename);
		memberimg.setMember_profileimg_original_filename   (originalfilename);
		memberimg.setMember_profileimg_url                 (savePath);
		memberimg.setMember_no                             (memberPK);
		memberimg.setMember_profileimg_date                (nowTime());
		
		return memberimg;
	}

	@Override
	public Member_profileimg imglogic(int memberPK, @RequestPart MultipartFile files) throws Exception {
		Member_profileimg memberimg = new Member_profileimg();
		
		String originalfilename            = files.getOriginalFilename();
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		// String fileurl    = "memberimg\\";
		String fileurl    = "memberimg/";
		String savePath   = fileDir + fileurl;

		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
		}while(destinationfile.exists());
		
		try {
			files.transferTo(destinationfile);
		}catch(IOException e) {
			
		}
		
		memberimg.setMember_profileimg_filename            (destinationfilename);
		memberimg.setMember_profileimg_original_filename   (originalfilename);
		memberimg.setMember_profileimg_url                 (savePath);
		memberimg.setMember_no                             (memberPK);
		memberimg.setMember_profileimg_date                (nowTime());
		
		return memberimg;
	}

	@Override
	public Post_img post_imglogic(int postPK, @RequestPart MultipartFile files) throws Exception {
		Post_img post_img = new Post_img();
		
		String originalfilename            = files.getOriginalFilename();
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		// String fileurl    = "postimg\\";
		String fileurl    = "postimg/";
		String savePath   = fileDir + fileurl;
		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
		}while(destinationfile.exists());
		
		try {
			files.transferTo(destinationfile);
		}catch(IOException e) {
			
		}
		
		post_img.setPost_img_filename            (destinationfilename);
		post_img.setPost_img_original_filename   (originalfilename);
		post_img.setPost_img_url                 (savePath);
		post_img.setPost_no                      (postPK);
		post_img.setPost_img_date                (nowTime());
		
		return post_img;
	}

	@Override
	public ResponseEntity<Object> downloadFileLogic(Post_img post_img) throws IOException, URISyntaxException{	
		// String fileurl    = "postimg\\";
		String fileurl    = "postimg/";
		String savePath   = fileDir + fileurl + post_img.getPost_img_filename();
		
		try {
			Path filePath = Paths.get(savePath);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			String original_filename = post_img.getPost_img_original_filename();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(original_filename, StandardCharsets.UTF_8).build());
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("no");
			return new ResponseEntity<Object>(null, HttpStatus.SEE_OTHER);
		}
	}
	
	@Override
	public ResponseEntity<Object> downloadChatFormFileLogic(Chat_filelist chat_filelist) throws IOException, URISyntaxException{	
		// String fileurl    = "chatfile\\";
		String fileurl    = "chatfile/";
		String savePath   = fileDir + fileurl + chat_filelist.getChat_filelist_filename();
		
		try {
			Path filePath = Paths.get(savePath);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			String original_filename = chat_filelist.getChat_filelist_original_filename();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(original_filename, StandardCharsets.UTF_8).build());
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("no");
			return new ResponseEntity<Object>(null, HttpStatus.SEE_OTHER);
		}
	}
	
}
