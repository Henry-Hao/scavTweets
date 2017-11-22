package scavTweets.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import scavTweets.dao.Result;
import scavTweets.dao.Search;
import scavTweets.dao.User;
import scavTweets.util.Constant;

/**
 * Servlet implementation class dispatcher
 */
@WebServlet("/router")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getQueryString();
		User user = (User)request.getSession().getAttribute("user");
		String returnJson = null;
		switch(param) {
			case "search":
				try {
					returnJson = Search.getAllSearches(user.getId());
					
				} catch (SQLException | JSONException e) {
					e.printStackTrace();
				}
				request.setAttribute("search", returnJson);
				request.getRequestDispatcher(Constant.VIEW_PUBLIC + "/search.jsp").forward(request, response);
				break;
			case "result":
			try {
				returnJson = Result.getAllResult(user.getId());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				request.setAttribute("result", returnJson);
				request.setCharacterEncoding("ISO-8859-1");
				request.getRequestDispatcher(Constant.VIEW_PUBLIC + "/result.jsp").forward(request, response);
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
