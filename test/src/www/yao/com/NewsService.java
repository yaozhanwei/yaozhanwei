package www.yao.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewsService {

	Connection connectMySQL =null;
	Statement statament  = null;
	ResultSet rs = null;
	
	public List<News> listNews(){
		
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				connectMySQL  =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root" ,"83899678" );
				statament = connectMySQL.createStatement(); 
				rs = statament.executeQuery("select id,name,content from News");
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		 List<News> newsList = new ArrayList<News>();
		 try {
			while(rs.next()){
				System.out.println(rs.getInt("id"));
				 News news = new News();
				 news.setId(rs.getInt("id"));
				 news.setName(rs.getString("name"));
				 news.setContent(rs.getString("content"));
				 newsList.add(news);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				statament.close();
				connectMySQL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
			 return newsList;
	}
	
}
