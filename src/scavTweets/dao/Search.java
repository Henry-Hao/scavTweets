package scavTweets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import scavTweets.util.Util;


public class Search {
	
	private long id;
	private  String region;
	private String start_time;
	private String end_time;
	private String term;
	private String name;
	private String result_id;
	private long user_id;
	
	public Search(String region, String start_time, String end_time, String term, String name,long user_id) {
		this.region = region;
		this.start_time = start_time;
		this.end_time = end_time;
		this.term = term;
		this.user_id = user_id;
		this.name = name;
	}
	
	public String getResultId() {
		return this.result_id;
	}
	
	public static String getAllSearches(long user_id) throws SQLException, JSONException {
		Sql sql = new Sql();
		String sqlString = "SELECT * FROM search where(user_id ="+user_id+")";
		String result = Util.resultSetToJson(sql.executeQuery(sqlString));
		return result;
	}
	
	public void createResult() throws Exception {
		Sql sql = new Sql();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(this.start_time);
		result_id = "result_"+this.user_id+"_"+date.getTime();
		String sqlString = "CREATE TABLE `"+result_id+"`"
				+ "(`Id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`Time` varchar(45) DEFAULT NULL,"
				+ "`Keyword` varchar(45) DEFAULT NULL,"
				+ "`Content` varchar(500) DEFAULT NULL,"
				+ "PRIMARY KEY (`Id`)) "
				+ "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
		sql.executeUpdate(sqlString);
		sql.destroy();
	}
	
	public int insertSearch() throws Exception {
		this.createResult();
		Sql sql = new Sql();
		int result;
		String sqlString = "SELECT * FROM search WHERE(Region='"+this.region+"' AND "
				+ "Term='"+this.term+"' AND "
				+ "Start_time='"+this.start_time+"' AND "
				+ "User_id='"+this.user_id+"' AND "
				+ "End_time='"+this.end_time+"')";
		ResultSet rs = sql.executeQuery(sqlString);
		if(rs.next()) {
			sql.destroy();
			return -1;//duplicated search
		}else {
			sqlString = "INSERT INTO search(Region,Start_time,End_time,Term,Result_id,User_id,Name) VALUES"
					+ "('"+this.region+"',"
					+ "'"+this.start_time+"',"
					+ "'"+this.end_time+"',"
					+ "'"+this.term+"',"
					+ "'"+this.result_id+"',"
					+ "'"+this.user_id+"',"
					+ "'"+this.name+"')";
			result = sql.executeUpdate(sqlString);
			return result;
		}
	}
	
	public static int removeSearch(long search_id) throws Exception {
		Sql sql = new Sql();
		String resultid;
		String sqlString = "SELECT Result_id FROM search where(Id='"+search_id+"')";
		int result;
		ResultSet rs = sql.executeQuery(sqlString);
		if(!rs.next()) {
			sql.destroy();
			return 0;
		} else {
			resultid = rs.getString("Result_id");
		}
		
		sqlString = "DELETE FROM search where(Id='"+search_id+"')";
		result = sql.executeUpdate(sqlString);
		if(result == 1) {
			sqlString = "DROP TABLE `"+resultid+"`";
			sql.executeUpdate(sqlString);
			return 1;
		} else {
			return 0;
		}
		
		
		
	}
}
