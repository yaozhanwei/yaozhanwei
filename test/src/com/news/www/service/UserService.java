package com.news.www.service;

import org.springframework.stereotype.Service;

import com.news.www.base.Dao;
import com.news.www.bean.User;
public interface UserService extends Dao<User>{

	public void save(User user);
	public User getUser(long id);
	
}
