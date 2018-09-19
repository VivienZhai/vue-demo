package com.inspur.viz.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspur.viz.user.dao.UserInfoMapper;
import com.inspur.viz.user.data.UserInfo;
import com.inspur.viz.user.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo getUserInfoById(String userId) {
		return userInfoMapper.getUserInfoById(userId);
	}

}
