package com.news.www.controller.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseController {
	protected final Log log = LogFactory.getLog(getClass());
	public BaseController(){

	}
	
	public String upload(HttpServletRequest request) throws Exception{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String local = request.getSession().getServletContext().getRealPath("/");
		String name="";
		factory.setSizeThreshold(1024*1024) ;
			List<FileItem> list =(List<FileItem>)upload.parseRequest(request);
			System.out.println("list:"+list.size());
			for(int i=0;i<list.size();i++){
				FileItem item = list.get(i);
				name = item.getName().substring(item.getName().lastIndexOf("\\")+1);
				System.out.println(name);
				File file = new File("D:/upload/"+name);
				item.write(file);
			}
			System.out.println("D:/upload/"+name);
		return "D:/upload/"+name;
	}
	
	public void ErrorMsg(HttpServletResponse response) throws IOException{
		ServletOutputStream output = response.getOutputStream();
		 response.setContentType("text/html;charset=GBK");
		    StringBuffer buf = new StringBuffer();
		    buf.append("");
		    buf.append("<html>");
		    buf.append("<head>");
		    buf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
		    buf.append("<title>警告</title>");
		    buf.append("</head>");
		    buf.append("<body>");
		    buf.append(" <script language=\"javascript\">");
		     
		    buf.append("var x = 640;");
		    buf.append("var y = 480;");
		    buf.append("var xx = (window.screen.width - x) / 2;");
		    buf.append("var yy = (window.screen.height - y) / 2;");
		     
		    buf.append("window.close();");
		    buf.append("var windowFeatures = \"width=\" + x + \",height=\" + y + \",status,scrollbars=yes,resizable,left=\" + xx + \",top=\" + yy + \"screenX=\" + xx + \",screenY=\" + yy; ");
		     
		     
		    buf.append("var win=window.open('','',windowFeatures);");
		    buf.append("win.document.write('<span class=\"bnew\">操作错误:</span>');");
		    buf.append("win.document.write('<center><p><pre style=\"color:red;border=2px\">请联系管理员！</pe> </p></center>');");
		    buf.append(" </script>");
		    buf.append("</body>");
		    buf.append("</html>");
		    byte[] bs = buf.toString().getBytes();
		    output.write(bs, 0, bs.length);
		    output.flush();
		    output.close();
	}
	
}
