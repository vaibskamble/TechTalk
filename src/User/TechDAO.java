package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TechDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public TechDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
		String sql = "INSERT INTO techinfo(date,title,description,presenter) VALUES (?, ?, ?, ?)";
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
	
	public boolean deleteBook(TechTalk techTalk) throws SQLException {
		String sql = "DELETE FROM techinfo where id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, techTalk.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateBook(TechTalk techTalk) throws SQLException {
		String sql = "UPDATE techinfo SET date = ? ,title = ?, description = ?, presenter = ?";
		sql += " WHERE id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, techTalk.getDate());
		statement.setString(2, techTalk.getTitle());
		statement.setString(3, techTalk.getDescription());
		statement.setString(4, techTalk.getPresenter());
		statement.setInt(5, techTalk.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public TechTalk getBook(int id) throws SQLException {
		TechTalk techTalk = null;
		String sql = "SELECT * FROM techinfo WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String date=resultSet.getString("date");
			String title = resultSet.getString("title");
			String Description = resultSet.getString("description");
			String Presenter = resultSet.getString("presenter");
			
			techTalk = new TechTalk(id,date, title, Description, Presenter);
		}
		
		resultSet.close();
		statement.close();
		
		return techTalk;
	}
	
	public List<TechTalk> listreqtechtalks() throws SQLException 
	{
		List<TechTalk> reqlist=new ArrayList<>();
        String sql = "SELECT * FROM techinforeq";
		
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
			reqlist.add(techTalk);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return reqlist;
		
		
		
	}
	
	public boolean deletereqtechtalk(TechTalk techTalk) throws SQLException {
		String sql = "DELETE FROM techinforeq where id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, techTalk.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean accepttechtalk(TechTalk techTalk) throws SQLException {
		String sql = "INSERT INTO techinfo(date,title,description,presenter) VALUES (?, ?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, techTalk.getDate());
		statement.setString(2, techTalk.getTitle());
		statement.setString(3, techTalk.getDescription());
		statement.setString(4, techTalk.getPresenter());

		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		
		deletereqtechtalk(techTalk);
		return rowInserted;
	}
	
	public TechTalk getreqtechtalk(int id) throws SQLException {
		TechTalk techTalk = null;
		String sql = "SELECT * FROM techinforeq WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String date=resultSet.getString("date");
			String title = resultSet.getString("title");
			String Description = resultSet.getString("description");
			String Presenter = resultSet.getString("presenter");
			
			techTalk = new TechTalk(id,date, title, Description, Presenter);
		}
		
		resultSet.close();
		statement.close();
		
		return techTalk;
	}
	
	
	
}
