package shortener;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Linker
 */
@WebServlet("/Linker")
public class Linker extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String longURL = request.getParameter("longURL");
		String shortURL = (String)session.getAttribute("shortURL");
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();
		shortURL = "https://"+serverName+":"+port+contextPath+"/"+shortURL;
		try {
		int id  = new Logic().join(longURL,shortURL);
		if(id!=0) {
			session.setAttribute("action","done" );
			response.sendRedirect("OnDemand.jsp");
		}
		else {
			session.setAttribute("action","not done");
			response.sendRedirect("OnDemand.jsp");	
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
