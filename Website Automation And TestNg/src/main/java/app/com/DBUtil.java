package app.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/automate";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "PoH@NBf#112233";

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return con;
	}

}
