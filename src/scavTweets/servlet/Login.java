package scavTweets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scavTweets.dao.User;
import com.alibaba.fastjson.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private User user;
	private String username;
	private String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		username = request.getParameter("Username").trim();
		password = request.getParameter("Password").trim();
		RR r = new RR();
		if(username.equals("") || password.equals("")) {
			r.result = "fail";
		} else {
			user = new User(username,password);
			HttpSession session = request.getSession();
			
			boolean result = false;
			try {
				result = user.verification();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if(result) {
				try {
					user.updateLastLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
				r.result = "success";	
				session.setAttribute("user", user);
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
