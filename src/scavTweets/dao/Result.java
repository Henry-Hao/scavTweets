package scavTweets.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import scavTweets.util.Util;

public class Result {
	private long id;
	private String time;
	private String Keyword;
	private String Content;
	
	public Result() {
		
	}
	public static String getContent(String result_id,String tweet_id) throws Exception {
		Sql sql = new Sql();
		String sqlString = "SELECT Content FROM "+result_id+" WHERE (Id="+tweet_id+")";
		ResultSet rs = sql.executeQuery(sqlString);
		String content = null;
		while(rs.next()) {
			content = rs.getString("Content");
		}
		rs.close();
		sql.destroy();
		return content;
	}
	
	public static JSONObject getResult(String result_id) throws Exception {
		Sql sql = new Sql();
		String sqlString = "SELECT * FROM "+result_id+"";
		ResultSet rs = sql.executeQuery(sqlString);
		JSONArray jArray = new JSONArray();
		JSONObject jObject,returnObj = new JSONObject(); 
		ResultSetMetaData metaData;  
		while(rs.next()) {
			metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			jObject = new JSONObject();
			
			for (int j = 1; j <= columnCount; j++) {  
	            String columnName =metaData.getColumnLabel(j);  
	            String value = rs.getString(columnName);
	            String[] split;
	            //brief content in case that resultArray overflows
	            if(columnName.equals("Content")) {
	            	split = value.split(":");
	            	String username = split[0];
//	            	String briefContent = split[4];
//	            	if(briefContent.length() > 5)
//	            		briefContent = briefContent.substring(0, 5);
//	            	value = username +":"+ briefContent + "...";
//	            	value = username + ":" + briefContent;
	            	jObject.put("Username", username);
	            } else 
	            	jObject.put(columnName, value);  
	        } 
			jArray.put(jObject);
		}
		returnObj.put("result", jArray);
		rs.close();
		sql.destroy();
		return returnObj;
	}
	
	public static String getAllResult(long user_id) throws Exception {
		Sql sql = new Sql();
		String sqlString = "SELECT Name,Result_id,Term,Start_time,End_time FROM search WHERE(User_id="+user_id+")";
		String result_id;
		ResultSet rs = sql.executeQuery(sqlString);
		ResultSet rs2 ;
		JSONObject object,resultObject;
		JSONArray searchArray = Util.resultSetToArray(rs);
		//every search has only one (JSONObject)result which contains (String)Name,(String)Term,(String)Start_time,(String)End_time,(JSONArray)tweets
		JSONArray resultArray = new JSONArray();
		//every (JSONArray)tweets has a list of (JSONObject)tweet which contains (String)Time,(String)Keywords,(String)Content
		JSONArray tweetArray;
		ResultSetMetaData metaData;  
		int columnCount;;  
		
		for(int i = 0; i < searchArray.length(); i++) {
			object = ((JSONObject)(searchArray.get(i)));
			
			resultObject = new JSONObject();
			resultObject.put("Result_id", object.get("Result_id"));
			resultObject.put("Name", object.get("Name"));
			resultObject.put("Term", object.get("Term"));	
			resultObject.put("Start_time", object.get("Start_time"));	
			resultObject.put("End_time", object.get("End_time"));	
			
			tweetArray = new JSONArray();
			
			result_id = object.getString("Result_id");
			sqlString = "SELECT * FROM "+result_id+"";
			rs2 = sql.executeQuery(sqlString);
			metaData = rs2.getMetaData();
			columnCount = metaData.getColumnCount();
			
			while (rs2.next()) {  
		        JSONObject jsonObj = new JSONObject();  
		         
		        for (int j = 1; j <= columnCount; j++) {  
		            String columnName =metaData.getColumnLabel(j);  
		            String value = rs2.getString(columnName);
		            String[] split;
		            //brief content in case that resultArray overflows
		            if(!columnName.equals("Content"))
//		            	split = value.split(":");
//		            	String username = split[0];
//		            	String briefContent = split[4];
//		            	if(briefContent.length() > 10)
//		            		briefContent = briefContent.substring(0, 10);
////		            	value = value.substring(0,value.length() / 5);
//		            	value = username +":"+ briefContent + "...";
//		            	jsonObj.put("Username", username);
//		            } else
		            	jsonObj.put(columnName, value);  
		        }   
		        tweetArray.put(jsonObj);   
		    }
			resultObject.put("Tweets", tweetArray);
			resultArray.put(resultObject);
			rs2.close();
		}
		sql.destroy();
		return resultArray.toString();
		
	}
}
