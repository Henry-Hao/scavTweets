package scavTweets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import scavTweets.dao.User;


/**
 * Servlet implementation class Registeration
 */
@WebServlet("/Registeration")
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       User user;
       String username;
       String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registeration() {
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
		username = request.getParameter("Username").trim();
		password = request.getParameter("Password").trim();
		HttpSession session = request.getSession();
		RR r = new RR();
		int result = 0;
		
		if(username.equals("") || password.equals("")) {
			r.result = "fail";
		} else {
			user = new User(username, password);
			try {
				result = user.insertUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(result == 1) {
				r.result = "success";
				session.setAttribute("user", user);
			} else if(result == -1) {
				r.result = "duplicated";
			} else {
				r.result = "fail";
			}
		}
			
		
		response.getWriter().write(JSON.toJSONString(r));
	}
	class RR{
		public String result;
	}

}
