package scavTweets.tweets;

import scavTweets.util.Constant;

public class Counter implements Runnable
{
	public Counter()
	{
		Thread thread= new Thread(this);
		thread.start();
	}
	public void run()
	{
		int i=0;
		try
		{
			while(Dispatch.flag==true)
			{
				if(i < Constant.QUERY_INTERVAL)
				{
					i++;
					Thread.sleep(1000);
				}
				else
				{
					Dispatch.global_rate=0;
					i=0;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
