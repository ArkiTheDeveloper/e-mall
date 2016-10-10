import java.sql.SQLException;


public class RegisterService {

	public int insert(RegisterBean register) throws ClassNotFoundException, SQLException {
		return new RegisterDAO().insert(register);
	}

}
