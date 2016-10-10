import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

	private static Connection con=null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.16.153.59:1521:orcl","elite1859","techm123$");
			return con;
	}

	public static void closeConnection(Connection con) {
		try {
			if(con!=null) {
				System.out.println("connection failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
