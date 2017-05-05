package Authuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import User.TechDAO;

/**
 * Servlet implementation class Register
 */
public class EventRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */private RegforEvent reg;

 	public void init() {
 		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
 		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
 		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

 		reg = new RegforEvent(jdbcURL, jdbcUsername, jdbcPassword);

 	}
 
	/**


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Username = request.getParameter("empid");
        String Password = request.getParameter("empname");
        String Email=request.getParameter("eventname");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         
        if(Username.equals(" ")|| Password.equals(" ") || Email.equals(" "))
        {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/regsuccess.jsp");
            out.println("Wrong Details");
            rd.include(request, response);
           
        }
        else
        {
               try {
				reg.insert(Username, Email, Password);
				
	            out.println("Register Succesfully");
	            response.sendRedirect("TechListu.jsp");
	           
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
	}

}
