package www.yao.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ListNewsTag extends SimpleTagSupport{

	private int record;
	private String url;

	@Override
	public void doTag() throws JspException, IOException {
		NewsService service = new NewsService();
		List<News> newsList = service.listNews();
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"con clearself\">");
	        for (int i = 0; i < newsList.size(); i++) {
	            News news = (News) newsList.get(i);
	            if (i == 0) {
	                sb.append("<ul style=\"width:410px;\" class=\"list fl\">");
	            }
	            if (i % 3 == 0 && i != 0) {
	                sb.append("<ul style=\"width:410px; margin-left:50px; padding-left:50px; border-left:1px solid #e6e5e5;\" class=\"list fl\">");
	            }
	            sb.append("<li><span class=\"fr\">");
	            sb.append(news.getName());
	            sb.append("</span><a href=\"");
	            sb.append(news.getContent());
	            sb.append("\">");
	            sb.append(news.getId());
	            sb.append("</a></li>");
	            if (i % 3 == 2 || newsList.size() - 1 == i) {
	                sb.append("</ul>");
	            }
	        }
	        sb.append("</div>");
	        JspWriter out = getJspContext().getOut();
	        System.out.println(sb.toString());
	        out.write(sb.toString());
	    }
	
	public void setRecord(int record) {
        this.record = record;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}