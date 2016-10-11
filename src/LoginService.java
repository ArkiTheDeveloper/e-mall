import java.sql.SQLException;

public class LoginService {

	public int authenticate(LoginBean login) throws ClassNotFoundException, SQLException {
		return new LoginDAO().authenticate(login);
	}

}
