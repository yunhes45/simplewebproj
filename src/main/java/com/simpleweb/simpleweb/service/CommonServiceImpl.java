package com.simpleweb.simpleweb.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simpleweb.simpleweb.mapper.MemberMapper;
import com.simpleweb.simpleweb.model.Member;
import com.simpleweb.simpleweb.model.Member_profileimg;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	MemberMapper membermapper;
	
	@Autowired
	ServletContext application;
	
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
		String fileurl            = "/filestorage/";
		
		memberimg.setMember_profileimg_filename            (originalfilename);
		memberimg.setMember_profileimg_original_filename   (originalfilename);
		memberimg.setMember_profileimg_url                 (application.getRealPath(fileurl));
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
		String fileurl    = "/filestorage/memberimg/";
		String savePath   = application.getRealPath(fileurl);
//		String savePath = new ClassPathResource(fileurl).getFile().getAbsolutePath();
//		String savePath = System.getProperty("user.dir");
//		String savePath = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path(fileurl).toUriString();
		
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
	
}
