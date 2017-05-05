package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import User.TechTalk;

public class TechDAOuser {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public TechDAOuser(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean insertBook(TechTalk techTalk) throws SQLException {
		String sql = "INSERT INTO techinforeq(date,title,description,presenter) VALUES (?, ?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, techTalk.getDate());
		statement.setString(2, techTalk.getTitle());
		statement.setString(3, techTalk.getDescription());
		statement.setString(4, techTalk.getPresenter());

		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<TechTalk> listAllBooks() throws SQLException {
		List<TechTalk> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM techinfo";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String date=resultSet.getString("date");
			String title = resultSet.getString("title");
			String description = resultSet.getString("description");
			String Presenter= resultSet.getString("presenter");
			
			TechTalk techTalk = new TechTalk(id, date , title, description, Presenter);
			listBook.add(techTalk);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	
	public List<TechTalk> listAllemp() throws SQLException {
		List<TechTalk> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM registration";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String date=resultSet.getString("employee_name");
			String title = resultSet.getString("event_title");
			
			
			TechTalk techTalk = new TechTalk(id, date , title);
			listBook.add(techTalk);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	


	

	public boolean register(int id,String Uname) throws SQLException {
		String sql = "INSERT INTO techinforeg(ID,Username) VALUES (?, ?)";
		connect();
		
	System.out.println("Entered1");
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setString(2, Uname);


		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	
	
}
