package scavTweets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import scavTweets.dao.Search;

/**
 * Servlet implementation class removeSearch
 */
@WebServlet("/removeSearch")
public class removeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long search_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeSearch() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NumberFormatException {
		// TODO Auto-generated method stub
		String ss = request.getParameter("removeId");
		search_id = Long.parseLong(ss);
		RR r = new RR();
		r.result = "fail";
		int result = 0;
		
		try {
			result = Search.removeSearch(search_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result == 1)
			r.result = "success";
		
		response.getWriter().write(JSON.toJSONString(r));
	}
	class RR{
		public String result;
	}
}
