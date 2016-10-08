package alreadywritten;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie authenticatedUser = new Cookie("authenticatedUser", null);
		try {
			LoginBean login=new LoginBean();
			login.setUser(request.getParameter("user"));
			login.setPwd(request.getParameter("pwd"));
			LoginService service = new LoginService();
			int result = service.authenticate(login);
			if(result == 1) {
				//Adding username to the cookie, if authenticated
				//And redirecting
				authenticatedUser.setValue(login.getUser());
				response.sendRedirect("./shoppingcenter");
			}
			else {
				response.getWriter().print("Login failed");
			}
			response.addCookie(authenticatedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
	}

}
