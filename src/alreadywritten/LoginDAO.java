package alreadywritten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	/**
	* Method to authenticate the user
	**/
	public int authenticate(LoginBean login) throws ClassNotFoundException, SQLException {
		int result = 0;
		System.out.println("in dao class");
		
			Connection con = JDBCUtils.getConnection();
			System.out.println(con == null);
			PreparedStatement pst = con.prepareStatement("select * from register1810 where E_mail_1 = ? and Password = ?");

				pst.setString(1,login.getUser());
				pst.setString(2,login.getPwd());

				ResultSet rs = pst.executeQuery();
				if(rs.next()){
				System.out.println("success");
				System.out.println("happy");
				result = 1;
				//String redirect_url  = "Service.jsp";
				//response.sendRedirect(redirect_url);
		}
	else {
					result = 0 ;
					System.out.println("fail");
				}

	return result;
	}
}
