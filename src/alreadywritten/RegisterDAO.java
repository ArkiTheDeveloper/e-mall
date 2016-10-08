package alreadywritten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDAO {
	/**
	 * A method to register a user
     */

	public int insert(RegisterBean register) throws ClassNotFoundException, SQLException {
		int result = 0;
		System.out.println("in dao class");
		
			Connection con = JDBCUtils.getConnection();
			if (con == null) {
				System.out.println("Connection is not established");
				throw new SQLException();
			}
			
			PreparedStatement ps = con.prepareStatement("insert into register1810 values(?,?,?,?,?,?,?,?,?,?,?,?)");

		    ps.setString(1, register.getFname());
			ps.setString(2, register.getLname());
			ps.setString(3, register.getPwd());
			ps.setString(4, register.getCpwd());
			ps.setString(5, register.getDob());
			ps.setString(6, register.getGender());
			ps.setString(7, register.getAddress());
			ps.setInt(8, register.getMobile());
			ps.setInt(9, register.getTele());
			ps.setString(10, register.getEmail1());
			ps.setString(11, register.getEmail2());
			ps.setString(12, register.getCountry());

			result = ps.executeUpdate();
			System.out.println(result);
		

		return result;

	}

}