package scavTweets.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException  
	{  
	   // json arrays
	   JSONArray array = new JSONArray();  
	    
	   // get the number of columnns  
	   ResultSetMetaData metaData = rs.getMetaData();  
	   int columnCount = metaData.getColumnCount();  
	    
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	         
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
	        }   
	        array.put(jsonObj);   
	    }  
	    
	   return array.toString();  
	}  
	
	public static JSONArray resultSetToArray(ResultSet rs) throws SQLException,JSONException  
	{  
	   // json arrays
	   JSONArray array = new JSONArray();  
	    
	   // get the number of columnns  
	   ResultSetMetaData metaData = rs.getMetaData();  
	   int columnCount = metaData.getColumnCount();  
	    
	    while (rs.next()) {  
	        JSONObject jsonObj = new JSONObject();  
	         
	        for (int i = 1; i <= columnCount; i++) {  
	            String columnName =metaData.getColumnLabel(i);  
	            String value = rs.getString(columnName);  
	            jsonObj.put(columnName, value);  
	        }   
	        array.put(jsonObj);   
	    }  
	    
	   return array;  
	}  
}
