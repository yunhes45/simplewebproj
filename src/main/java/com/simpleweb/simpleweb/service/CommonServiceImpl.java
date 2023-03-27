package com.simpleweb.simpleweb.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

import com.simpleweb.simpleweb.mapper.CommonMapper;
import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Chat_filelist;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;
import com.simpleweb.simpleweb.model.Post_img;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	MemberMapper membermapper;
	
	@Autowired
	CommonMapper commonmapper;

	@Value("${spring.servlet.multipart.location}")
	private String fileDir;
	
	public Object fileDir_path() {

		return fileDir;
	}
	
	private String dateFoler() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();
		 String str = sdf.format(date);
		 
		  return str.replace("-", File.separator) + "\\";
		 // return str.replace("-", File.separator) + "/";
	}
	
	private String member_file_path() {
		  String fileurl    = "memberimg\\";
		 // String fileurl    = "memberimg/";
		 
		 return fileurl;
	}
	private String post_file_path() {
		 String fileurl    = "postimg\\";
		// String fileurl    = "postimg/";	
		
		return fileurl;
	}
	private String chat_file_path() {
		 String fileurl    = "chatfile\\";
		// String fileurl    = "chatfile/";
		
		return fileurl;
	}
	
	public String getMember_file_FullPath(String filename) {
		return fileDir + member_file_path() + dateFoler() + filename;
	}
	public String getPost_file_FullPath(String filename) {
		return fileDir + post_file_path() + dateFoler() + filename;
	}
	public String getChat_file_FullPath(String filename) {
		return fileDir + chat_file_path() + dateFoler() + filename;
	}
	
	@Override
	public String getMember_file_LoadPath(String filename) {
		Member_profileimg filepath = commonmapper.getMember_file_LoadPath(filename);
		
		return filepath.getMember_profileimg_url() + filename;
	}
	
	@Override
	public String getPost_file_LoadPath(String filename) {
		Post_img filepath = commonmapper.getPost_file_LoadPath(filename);
		
		return filepath.getPost_img_url() + filename;
	}
	
	@Override
	public String getChat_file_LoadPath(String filename) {
		Chat_filelist filepath = commonmapper.getChat_file_LoadPath(filename);
		
		return filepath.getChat_filelist_url() + filename;
	}

	
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
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		String fileurl    = member_file_path();
		String savePath   = fileDir + fileurl + dateFoler();
		
		File files = new File(fileDir + member_file_path() + originalfilename);
		
		do {
			destinationfilename   = "_normalimg_" + RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
			
		}while(destinationfile.exists());
			if(destinationfile.exists() == false) {
				destinationfile.mkdirs();
			}
		try {
			Files.copy(files.toPath(), destinationfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
	public Member_profileimg imglogic(int memberPK, @RequestPart MultipartFile files) throws Exception {
		Member_profileimg memberimg = new Member_profileimg();
		
		String originalfilename            = files.getOriginalFilename();
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		String fileurl    = member_file_path();
		String savePath   = fileDir + fileurl + dateFoler();

		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
			
		}while(destinationfile.exists());
			if(destinationfile.exists() == false) {
				destinationfile.mkdirs();
			}
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
		String fileurl    = post_file_path();
		String savePath   = fileDir + fileurl + dateFoler();
		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
		}while(destinationfile.exists());
			if(destinationfile.exists() == false) {
				destinationfile.mkdirs();
			}		
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
	public Chat_filelist chat_filelogic(Chat_filelist chat_filelist) throws Exception {
		Chat_filelist chat_file = new Chat_filelist();
		
		String originalfilename            = chat_filelist.getChat_file().getOriginalFilename();
		String originalfilenameExtension   = FilenameUtils.getExtension(originalfilename).toLowerCase();
		File destinationfile;
		String destinationfilename;
		String fileurl    = chat_file_path();
		String savePath   = fileDir + fileurl + dateFoler();
		do {
			destinationfilename   = RandomStringUtils.randomAlphanumeric(32) + "." + originalfilenameExtension;
			destinationfile       = new File(savePath, destinationfilename);
		}while(destinationfile.exists());
			if(destinationfile.exists() == false) {
				destinationfile.mkdirs();
			}		
		try {
			chat_filelist.getChat_file().transferTo(destinationfile);
		}catch(IOException e) {
			
		}
		
		chat_file.setMember_no                       (chat_filelist.getMember_no());
		chat_file.setChatroom_no                     (chat_filelist.getChatroom_no());
		chat_file.setChat_filelist_filename          (destinationfilename);
		chat_file.setChat_filelist_original_filename (originalfilename);
		chat_file.setChat_filelist_url               (savePath);
		chat_file.setChat_filelist_date              (chat_filelist.getChat_filelist_date());
		
		return chat_file;		
	}

	@Override
	public ResponseEntity<Object> downloadFileLogic(Post_img post_img) throws IOException, URISyntaxException{	
		String fileurl    = post_file_path();
		String savePath   = fileDir + fileurl + dateFoler() + post_img.getPost_img_filename();
		
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
		String fileurl    = chat_file_path();
		String savePath   = fileDir + fileurl + dateFoler() + chat_filelist.getChat_filelist_filename();
		
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
