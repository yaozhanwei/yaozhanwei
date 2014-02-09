package com.news.www.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.news.www.base.QueryResult;
import com.news.www.bean.Clzp;
import com.news.www.bean.bl;
import com.news.www.controller.common.BaseController;
import com.news.www.service.ClzpService;
import com.news.www.service.BlglService;

@Controller
public class BlglController extends BaseController{
	
	@Resource(name="blglService")
	private BlglService blglservice;
	@Resource(name="clzpService")
	private ClzpService clzpService;

	@RequestMapping(value="/index.do",method={RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request){
		return "redirect:/index.jsp?entity=bl";
	}
	@RequestMapping(value="/blSave.do",method=RequestMethod.POST)
	public String Save(HttpServletRequest request,@Valid bl blEntity,BindingResult result){
		blglservice.save(blEntity);
		return "redirect:/blUpdateRedirect.do?bh="+blEntity.getBh();
		
	}
	@RequestMapping(value="/blUpdateRedirect.do",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateRedirect(HttpServletRequest request,String bh){
		bl b = blglservice.findByBh(bh);
		ModelAndView model = new ModelAndView();
		model.setViewName("index.jsp");
		model.addObject("Object",b);
		return model;
	}
	@RequestMapping(value="/blUpdate.do",method=RequestMethod.POST)
	public String Update(HttpServletRequest request,@Valid bl Entity,BindingResult result){
		blglservice.update(Entity);
		return "redirect:/blUpdateRedirect.do?bh="+Entity.getBh();
	}
	@RequestMapping(value="/clUpload.do",method=RequestMethod.POST)
	public String clUpload(HttpServletRequest request,String bh) throws Exception{
		String path = super.upload(request);
		Clzp cl = new Clzp();
		String clmc = path.substring(path.lastIndexOf("/")+1,path.length());
		cl.setBlbh(bh);
		cl.setClmc(clmc);
		cl.setClcfdz(path);
		clzpService.save(cl);
		request.setAttribute("clzp", path);
		if(request.getParameter("bh")!=null){
			return "redirect:/blUpdateRedirect.do?bh="+request.getParameter("bh");
		}else{
			return "redirect:/index.jsp?entity=bl"; 
		}
	}
	@RequestMapping(value="/ckclzp.do",method=RequestMethod.GET)
	public ModelAndView ckcl(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String bh = request.getParameter("bh");
		System.out.println(bh);
		QueryResult<Clzp> Result= clzpService.getScrollData(-1, -1, "o.blbh=?", new Object[]{bh});
		model.setViewName("clzp.jsp");
		model.addObject("result", Result);
		return model;
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {

		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}
	public BlglService getBlglservice() {
		return blglservice;
	}
	public void setBlglservice(BlglService blglservice) {
		this.blglservice = blglservice;
	}
}
