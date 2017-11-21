package scavTweets.util;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Constant {
	public static final String DB_USERNAME = "sql";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/scavTweets";
	public static final String DB_PASSWORD = "root";
	public static final String VIEW_PUBLIC = "/WEB-INF/views";
	public static final int USER_MAXIMUM = 8;//1 querys per minute
	public static final int QUERY_INTERVAL = 900;
	public static final int QUERY_LIMIT = 160; 
	public static final double LAT = 43.7;
	public static final double LON = -79.4;
	public static final int RAIDUS = 20;
	public static final String UNIT = "mi";
}
