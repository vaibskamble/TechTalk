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
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */private Reg reg;

 	public void init() {
 		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
 		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
 		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

 		reg = new Reg(jdbcURL, jdbcUsername, jdbcPassword);

 	}
 
	/**


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Username = request.getParameter("user");
        String Password = request.getParameter("pwd");
        String Email=request.getParameter("email");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         
        if(Username.equals(" ")|| Password.equals(" ") || Email.equals(" "))
        {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
            out.println("Wrong user name /PAssword/Email");
            rd.include(request, response);
           
        }
        else if(Email.endsWith("@atmecs.com"))
        {
               try {
				reg.insert(Username, Email, Password);
	            out.println("Register Succesfully");
	            response.sendRedirect("Login.jsp");
	           
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else
        {
        	RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");

            out.println("Enter Company Mail Id");
            rd.include(request, response);
        }
	}

}
