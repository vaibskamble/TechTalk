package Authuser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Check 
{

    public ResultSet rs; 

	
	
	
	public ResultSet verify(String Username,String Password) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techtalk","root","root");
		java.sql.Statement stmt = null;
	    String query ="select * from userTable where Username='" + Username + "' and Password='" + Password + "'"  ;
         
	        stmt =jdbcConnection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
		 
		 
		
		return rs;
	}

}
