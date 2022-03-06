package com.simpleweb.simpleweb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleweb.simpleweb.mapper.BoardFuncMapper;
import com.simpleweb.simpleweb.model.Like_stat;

@Service
public class BoardFuncServiceImpl implements BoardFuncService{

	@Autowired
	BoardFuncMapper boardfuncmapper;
	
	@Override
	public void LikeLogic(int member_no, int post_no, int i) {
		Optional<Like_stat> validlikecheck = boardfuncmapper.validlikecheck(member_no, post_no, i);
		
		try {
			int excep = validlikecheck.get().getLike_stat_no();
			boardfuncmapper.deleteLike_stat(member_no, post_no, i);
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertLike_stat(member_no, post_no, i);			
		}

	}

}
