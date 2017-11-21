package scavTweets.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Sql {
	
	private  Connection con; 
	private  PreparedStatement pstmt;
	private  ResultSet rs;
	private  int result;
	
	public Sql() {
		con = DB.getConnection(); 
	}
	
	public PreparedStatement getStmt() {
		return this.pstmt;
	}
	
	public int executeUpdate(String sql) {
		try {  
            pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
            result = pstmt.executeUpdate();  
              
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
		return result;
	}
	
	public  ResultSet executeQuery(String sql) {
		try {  
            pstmt = con.prepareStatement(sql);  
            rs = pstmt.executeQuery(); 
              
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
		return rs;
	}
	
	public void destroy() throws Exception
	{
		con.close();
	}
	
	
}