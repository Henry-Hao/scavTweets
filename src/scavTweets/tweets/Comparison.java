package scavTweets.tweets;

import java.sql.PreparedStatement;

import org.json.*;


import scavTweets.dao.Sql;

public class Comparison 
{
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("abc");
		sb.append("123");
		sb.append("'");
		System.out.println(sb.toString());
	}
	
	public void word_compare(String[] words, String [] tweets,String result_id) throws Exception
	{
		JSONObject result=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject tweet = new JSONObject();
		String [] split;
		
		boolean flag;
		boolean batchSwitch = false;//True means at least one tweet contains one of the keywords
		
		System.out.println("Tweets Length: "+tweets.length);
		
		Sql s = new Sql();
		PreparedStatement pst = s.getCon().prepareStatement("INSERT into "+result_id+" (Time,Keyword,Content) VALUES (?,?,?)");
			
		for(int i = 0; i < words.length; i++)
		{
			for(int j=0;(j<tweets.length)&&(tweets[j]!=null);j++)
			{
				tweets[j] = tweets[j].replaceAll("\'","\"");
				flag = false;
				System.out.println("Tweet Number: " +j);
				split = tweets[j].split(" ");
				
				for(int k=4;k<split.length;k++)
				{
					if(words[i].equals(split[k]))
					{
						flag = true;
						batchSwitch = true;
					}
				}
				
				if(flag)
				{
					pst.setString(1,split[2]);
					pst.setString(2, words[i]);
					pst.setString(3, tweets[j]);
					pst.addBatch();
				}
			}
			result.put(words[i], array);
			//At least one tweet contains the keyword
		}
		
		if(batchSwitch) {
			pst.executeBatch();
		}
	}
}
