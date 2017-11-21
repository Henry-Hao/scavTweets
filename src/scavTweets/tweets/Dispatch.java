package scavTweets.tweets;

import java.util.Scanner;

import scavTweets.util.Constant;

public class Dispatch 
{
	static int count_global=0;
	static int global_rate=0;
	static Scanner s;
	static boolean flag=true;
	
//	public static void main(String [] args)
//	{
//	try
//	{
//		//String[] words = {"Himalaya","Baseball","#DXC17","#Wordpress","#techgiant","abc","bcd","cde","efg","fgh","ghi","ijk","lmn","mno",
//				//"opq","pqr"};
//		String [] words = { "Charles", "Manson"};
//		dispatch(words);
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	}
//	}
	public static void dispatch(String[] keywords,String result_id) throws Exception
	{
		//String[] words = {"Himalaya","Baseball","#DXC17","#Wordpress","#techgiant","abc","bcd","cde","efg","fgh","ghi","ijk","lmn","mno",
			//	"opq","pqr"};
		s=new Scanner(System.in);
		String keyword="";
		SearchTask tc = null;
		int i=0;
		
		new Counter();
		
			if(global_rate < Constant.QUERY_LIMIT)
			{	if(count_global < Constant.USER_MAXIMUM)
				{
					System.out.println("Count Global Increased: " + (++count_global));
					tc = new SearchTask(keywords,result_id);
					i++;
				}
				else
				{
					System.out.println("Max number of users reached.... Please Wait");
				}
			}
			else
			{
				System.out.println("Please wait for sometime....");
			}
			
			flag=false;
	}
}
