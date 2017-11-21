package scavTweets.tweets;

import org.json.*;

import scavTweets.dao.Sql;

public class Comparison 
{
	public void word_compare(String[] words, String [] tweets,String result_id) throws JSONException
	{
		JSONObject result=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject tweet = new JSONObject();
		String [] split;
		boolean flag;
		boolean tweetsFlag = false;
		int i;
		System.out.println("Tweets Length: "+tweets.length);
		Sql sql = new Sql();
		String sqlString = "INSERT into "+result_id+" (Time,Keyword,Content) VALUES";
		for(i=0;i<words.length;i++)
		{
			for(int j=0;(j<tweets.length)&&(tweets[j]!=null);j++)
			{
				tweets[j].replaceAll("\'","\"");
				flag = false;
				System.out.println("Tweet Number: " +j);
				split = tweets[j].split(" ");
				
				for(int k=4;k<split.length;k++)
				{
					if(words[i].equals(split[k]))
					{
						flag = true;
						tweetsFlag = true;
					}
				}
				
				if(flag)
				{
					tweet.put("Username:", split[0]);
					tweet.put("TimeStamp:", split[2]);
					tweet.put("Content:", tweets[j]);
					array.put(tweet);
					
					sqlString += "('"+split[2];
					sqlString += "','"+words[i];
					sqlString += "','"+tweets[j];
					sqlString += "'),";
				}
			}
			result.put(words[i], array);
			//At least one tweet contains the keyword
		}
		if(tweetsFlag) {
			sqlString = sqlString.substring(0, sqlString.length()-1);
			sql.executeQuery(sqlString);
		}
//		return result;
	}
}
