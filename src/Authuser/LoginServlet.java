package Authuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private Check check;

 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException 
    {
    	try 
    	{
    	int role=0;
        // get request parameters for userID and password
        String Username = request.getParameter("user");
        String Password = request.getParameter("pwd");
        check=new Check();
        ResultSet rs;
		
			rs = check.verify(Username, Password);
		       
			while(rs.next())
			{
			
					role=rs.getInt("role");
			
			}
			
			
			   if(role==1){
		            HttpSession session = request.getSession();
		            session.setAttribute("user", Username);
		            //setting session to expiry in 30 mins
		            session.setMaxInactiveInterval(1*60);
		            Cookie userName = new Cookie("user", Username);
		            response.addCookie(userName);
		            //Get the encoded URL string
		            String encodedURL = response.encodeRedirectURL("TechList.jsp");
		            response.sendRedirect(encodedURL);
		        }
		        else if(role==2)
		        {
		        	HttpSession session = request.getSession();
		            session.setAttribute("user", Username);
		            //setting session to expiry in 30 mins
		            session.setMaxInactiveInterval(1*60);
		            Cookie userName = new Cookie("user", Username);
		            response.addCookie(userName);
		            //Get the encoded URL string
		            String encodedURL = response.encodeRedirectURL("TechListu.jsp");
		            response.sendRedirect(encodedURL);
		        }
		        else{
		            RequestDispatcher rd = getServletContext().getRequestDispatcher("Login.jsp");
		            PrintWriter out= response.getWriter();
		            out.println("Either user name or password is wrong.");
		            rd.include(request, response);
		        }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         
     
 
    }
 
}
