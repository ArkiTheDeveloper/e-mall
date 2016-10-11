import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

	public static final String AUTHENTICATE_QUERY = "select * from register1810 where E_mail_1 = ? and Password = ?";
	public static final String DEREGISTERINGUSERS = "SELECT * FROM USERSSTATUS WHERE STATUS = 'INACTIVE' ";
	public static final String ACTIVE_USERS_QUERY = "SELECT * FROM USERSTATUS WHERE USERNAME = ? AND STATUS = 'ACTIVE'";

	/**
	* Method to authenticate the user
	**/
	public int authenticate(LoginBean login) throws ClassNotFoundException, SQLException {
			int result;
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pst = con.prepareStatement(AUTHENTICATE_QUERY);
			PreparedStatement pstt = con.prepareStatement(ACTIVE_USERS_QUERY);

				pst.setString(1,login.getUser());
				pst.setString(2,login.getPwd());
				pstt.setString(1, login.getUser());

				ResultSet rs = pst.executeQuery();

				if(rs.next()){
					result = 1;
				} else {
					result = 0;
				}
				rs = pstt.executeQuery();
				if (rs.next()) {
					if (result == 1){
						result = 1;
					}
					else
						result = 0;
				}

		return result;
	}

	/**
	 * Method to get the USERS who want to deregister
	 * CREATE TABLE USERSTATUS (
	 * 			USERNAME VARCHAR(100),
	 * 			STATUS CHAR(20),
	 * );
	 * @return List<Users who want to deregister>
	 * @throws ClassNotFoundException
	 * @throws SQLException
     */

	public List<String> getListOfDeRegisteringUsers() throws ClassNotFoundException, SQLException {
		Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(DEREGISTERINGUSERS);
		List<String> deRegisteringUsers = new ArrayList<>();
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			deRegisteringUsers.add(rs.getString("USERNAME"));
		}
		return deRegisteringUsers;
	}

}
