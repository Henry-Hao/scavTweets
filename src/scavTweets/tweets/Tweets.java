package scavTweets.tweets;

import java.util.Calendar;
import twitter4j.*;
import java.util.*;
import org.json.JSONObject;

import scavTweets.util.Constant;

public class Tweets implements Runnable
{
	String search;
	ArrayList<Status> tweets;
	int cTweet=0;
	String []words;
	ResponseList <Location> location;
	Twitter t;
	Thread thread;
	Status status;
	String[] s_tweets;
	String result_id;
	public Tweets(Twitter t,String search,String [] words,String result_id)
	{
		this.t=t;
		this.search=search;
		this.words = words;
		this.result_id = result_id;
		s_tweets = new String[100];
		thread= new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		
		try
		  {
			
 			Query query=new Query(search).geoCode(new GeoLocation(Constant.LAT,Constant.LON),Constant.RAIDUS,Constant.UNIT).count(100);
		    
			Calendar c= Calendar.getInstance();
			QueryResult res;
		    res=null;
		    res = t.search(query);
		   
		    tweets =(ArrayList) res.getTweets();
		    System.out.println("Tweet Size: "+tweets.size());
		    System.out.println("\n\nTweets for Keyword: " + search +"\n\n");
		    for(cTweet=0;cTweet<tweets.size();cTweet++)
		    {
		    	status = tweets.get(cTweet);
		    	s_tweets[cTweet]=status.getUser().getScreenName()+" : "+c.getTime().toString().replaceAll(" ", "")+" : "+status.getText().toString();
		    	System.out.println(s_tweets[cTweet]);
		    }
		    
		    Comparison com= new Comparison();
		    
		   com.word_compare(words, s_tweets,result_id);//
		
		  }
		  catch(Exception e)
		  {
			  System.out.println("Message: "+e.getMessage());
			  e.printStackTrace();
		  }
	}
	
}
