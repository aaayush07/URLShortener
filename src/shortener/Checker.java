package shortener;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Checker
 */
@WebServlet("/Checker")
public class Checker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String shortURL = request.getParameter("shortURL");
		try{
		int x = new Logic().checker(shortURL);
		if(x==0) {
			session.setAttribute("shortURL", shortURL);
			response.sendRedirect("OnDemand.jsp");}
		else {
			session.setAttribute("shortURL", null);
			response.sendRedirect("OnDemand.jsp");
		}}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
