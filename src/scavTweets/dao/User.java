package scavTweets.dao;

import java.sql.Date;
import java.sql.ResultSet;



public class User {
	private long id;
	private String username = null;
	private String password = null;
	private Date Last_login_date = null;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		Last_login_date = new Date(System.currentTimeMillis());
	}
	public long getId() {
		return id;
	}
	public Date getLast_login_date() {
		return Last_login_date;
	}
	public void setLast_login_date(Date last_login_date) {
		Last_login_date = last_login_date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int insertUser() throws Exception {
		Sql sql =new Sql();
		Date date = new Date(System.currentTimeMillis());
		ResultSet rs = sql.executeQuery("SELECT * FROM user where(Username='"+this.username+"')");
		if(rs.next()) {
			sql.destroy();
			return -1;//duplicated userrname
		}
		String sqlString = "INSERT INTO user(Username,Password,Register_date,Last_login_date) "
				+ "VALUES('"+this.username+"',"
						+ "'"+this.password+"',"
							+ "'"+date+"',"
							+ "'"+this.Last_login_date+"')";
		int result = sql.executeUpdate(sqlString); 
		//get the primary key
		ResultSet rst = sql.getStmt().getGeneratedKeys();
		if(rst.next()) {
			this.id = rst.getInt(1);
		}
		sql.destroy();
		return result;
	}
	
	public boolean verification() throws Exception{
		Sql sql =new Sql();
		String sqlString = "SELECT * FROM user where(Username = '"+this.username+"')";
		ResultSet rs = sql.executeQuery(sqlString);
		boolean result = false;
		
		if(rs.next()) {
			String c = rs.getString("Password");
			if(c.equals(this.password)) {
				result = true;
				this.id = rs.getLong("Id");
			}
		}
		sql.destroy();
		return result;
	}
	
	public int updateLastLogin() throws Exception {
		Sql sql = new Sql();
		String sqlString = "UPDATE user SET Last_login_date = '"+this.Last_login_date+"' where Id = "+this.id+"";
		int result = sql.executeUpdate(sqlString);
		sql.destroy();
		return result;
	}
	
	
}
