package shortener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Short
 */
@WebServlet("/Short")
public class Short extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		String longURL = request.getParameter("longURL");
		String shortURL= new String("");
		try {
		int flag = new Logic().validationURL(longURL);
		if(flag!=0) {
			shortURL = new Logic().getshortURL(flag);
		}
		else {
			shortURL = new Logic().getnewshortURL(longURL);
		}}
		catch(Exception e) {
			e.printStackTrace();
		}String serverName = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();
		shortURL = "https://"+serverName+":"+port+contextPath+"/"+shortURL;
		out.println(shortURL);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
