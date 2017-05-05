package Authuser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reg 
{

	
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	public int role=2;

	public Reg(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
	
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
   
	 public void insert(String Username,String Email,String Password) throws SQLException
	 {
		 String sql = "INSERT INTO userTable(Username,Email,Password,role) VALUES ( ?, ?, ? ,?)";
		 connect();
		 
		 PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, Username);
			statement.setString(2, Email);
			statement.setString(3, Password);
			statement.setInt(4, role);
			
			boolean rowInserted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			
	 }
	 
	
}
