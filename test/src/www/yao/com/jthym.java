package www.yao.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.activation.Server;

/**
 * Servlet implementation class jthym
 */
public class jthym extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jthym() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		try{
			URL url = new URL("http://localhost:8080/test/jth");
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine())!=null){
				sb.append(inputLine);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(this.getServletConfig().getInitParameter("path"));
		File file = new File(this.getServletConfig().getInitParameter("path")+"/index.html");
		file.createNewFile();
//		OutputStream output = new FileOutputStream(file);
		PrintWriter p = new PrintWriter(file);
		p.write(sb.toString());
		p.flush();
		response.sendRedirect("htmlPage/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
