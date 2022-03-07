package com.simpleweb.simpleweb.service;

import java.util.HashMap;
import java.util.Map;
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
	public Map LikeLogic(int member_no, int post_no, int i) {
		Optional<Like_stat> validlikecheck = boardfuncmapper.validlikecheck(member_no, post_no, i);
		Map<String, Integer> likelogic = new HashMap<String, Integer>();
		int likestat = -1;
		
		try {
			int excep = validlikecheck.get().getLike_stat_no();
			boardfuncmapper.deleteLike_stat(member_no, post_no, i);
			
			likestat = 1;
			if(likestat == 1) {
				likelogic.put("likestat", likestat);
				likelogic.put("likecount", getLikeCount(post_no));
			}
		}catch(NoSuchElementException e) {
			boardfuncmapper.insertLike_stat(member_no, post_no, i);			
			
			likestat = 0;
			if(likestat == 0) {
				likelogic.put("likestat", likestat);
				likelogic.put("likecount", getLikeCount(post_no));
			}
		}
		
		return likelogic;

	}
	private int getLikeCount(int post_no) {
		int getlikecount = boardfuncmapper.getLikeCount(post_no);
		
		return getlikecount;
	}

}
