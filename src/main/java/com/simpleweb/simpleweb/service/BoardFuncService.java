package com.simpleweb.simpleweb.service;

import java.util.Map;
import java.util.Optional;

import com.simpleweb.simpleweb.model.Like_stat;

public interface BoardFuncService {

	Map<String, Integer> LikeLogic(int member_no, int post_no, int i);

}
