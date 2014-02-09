package com.news.www.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.www.base.BaseDao;
import com.news.www.bean.User;
import com.news.www.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseDao<User> implements UserService{

	@Override
	public void save(User user) {
		super.save(user);
	}

	public User getUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
