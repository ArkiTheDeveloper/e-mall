package alreadywritten;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			RegisterBean register=new RegisterBean();
			register.setFname(request.getParameter("fname"));
			register.setLname(request.getParameter("lname"));
			register.setPwd(request.getParameter("pwd"));
			register.setCpwd(request.getParameter("cpwd"));
			register.setDob(request.getParameter("dob"));
			register.setGender(request.getParameter("gender"));
			register.setAddress(request.getParameter("address"));
			register.setMobile(Integer.parseInt(request.getParameter("mobile")));
			register.setTele(Integer.parseInt(request.getParameter("tele")));
			register.setEmail1(request.getParameter("email1"));
			register.setEmail2(request.getParameter("email2"));
			register.setCountry(request.getParameter("country"));
					
			RegisterService service=new RegisterService();
			int result=service.insert(register);
			if(result==1)
				response.getWriter().print("user inserted successfully");
			else
				response.getWriter().print("user failed to insert");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		
		
	}

}
