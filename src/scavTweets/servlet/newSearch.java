package scavTweets.servlet;

import java.io.IOException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import scavTweets.dao.Search;
import scavTweets.dao.User;
import scavTweets.tweets.Dispatch;
import scavTweets.tweets.SearchTask;

/**
 * Servlet implementation class newSearch
 */
@WebServlet("/newSearch")
public class NewSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String Region;
       private String Start_time;
       private String End_time;
       private String Term;
       private String Name;
       private long User_id;
       private Search search;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User_id = ((User)session.getAttribute("user")).getId();
		Region = request.getParameter("location").trim();
		Start_time = request.getParameter("st").trim();
		End_time = request.getParameter("et").trim();
		Term = request.getParameter("term").trim();
		Name = request.getParameter("name").trim();
		String [] term_condition;
		int result;
		
		RR r = new RR();
		
		if( Region.equals("") || Start_time.equals("") || End_time.equals("") || Name.equals("") || Term.equals(""))
			r.result = "fail";
		else {
			search = new Search(Region, Start_time, End_time,Name, Term,User_id);
			try {
				result = search.insertSearch();
				if(result == 0)
					r.result = "fail";
				else if(result == -1)
					r.result = "duplicate";
				else {
					r.result = "success";
					term_condition = Term.split(";");
//					dispatch_class dispatch = new dispatch_class();
					Dispatch.dispatch(term_condition, search.getResultId());
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.getWriter().write(JSON.toJSONString(r));
	}
	class RR{
		public String result;
	}

}
