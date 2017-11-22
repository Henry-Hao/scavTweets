package scavTweets.tweets;

import twitter4j.*;
import twitter4j.conf.*;
import twitter4j.auth.*;
import twitter4j.api.*;
import twitter4j.*;
import java.util.*;
import java.util.List;

import scavTweets.servlet.Router;


public class SearchTask implements Runnable 
{
	ConfigurationBuilder cb = new ConfigurationBuilder();
	Twitter t;
	String[] word;
	String result_id;
	Thread thread;
	Trends trends;
	String [] trend = new String[10];
	
	public SearchTask(String [] word, String result_id) throws Exception
	{
		cb.setOAuthConsumerKey("GspyUoQvLtTIiEYqQ08HQKyuZ");
		cb.setOAuthConsumerSecret("3GV1UVF6sBTj72ep9MGGCH4QoBADPmzTW5aBiv0CBWqckSkWyO");
		cb.setOAuthAccessToken("200503237-A284z9j4mz0waDNtI8gYJbV1uC5oiANOjdfrVSLR"); 
		cb.setOAuthAccessTokenSecret("whFm04eKQZcKzcamW1sN1KLPESiq7kIY3SIxnU7fXC4vL");
		t=new TwitterFactory(cb.build()).getInstance();
		this.word = word;
		this.result_id = result_id;
		trends = t.getPlaceTrends(4118);
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() 
	{
		// TODO Auto-generated method stub
		try
		{
		Scanner s=new Scanner(System.in);
		Tweets nt=null;
		System.out.println("Rate Limit:" + t.getRateLimitStatus());
		  int i=0;
		  for(Trend trend : trends.getTrends())
			{
				if(i<10)
				{
					this.trend[i] = trend.getName().toString();
				}
				else
				{
					break;
				}
				
				i++;
			}
		  
		  i=0;
		  while(i < 10) {
			  System.out.println("Increasing query count:" + (++Dispatch.global_rate));
			  nt = new Tweets(t, trend[i],word,result_id) ;
			  i++;
		  }
//		  nt =new Tweets(t,trend[i],word,this.result_id);
		  while(nt.thread.isAlive())
		  {
			  
		  }
		  
		  System.out.println("Count Global Decreased: "+ (--Dispatch.count_global));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
}

