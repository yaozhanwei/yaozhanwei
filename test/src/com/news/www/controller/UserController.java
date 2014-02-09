package com.news.www.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.news.www.bean.User;
import com.news.www.service.UserService;

@Controller
public class UserController {

	protected final Log log = LogFactory.getLog(getClass());
	@Resource(name="userService")
	private UserService userService;
	@RequestMapping(value="/hello.html",method=RequestMethod.GET)
	public ModelAndView getAllUser(){
		User user=userService.getUser(1);
		ModelAndView model =new ModelAndView();
		model.addObject("USER", user);
		model.setViewName("hello");
		return model;
	}
	
}
