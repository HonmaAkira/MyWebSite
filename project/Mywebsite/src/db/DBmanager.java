package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB接続のみのクラス
 */

	public class DBmanager {
	    final private static String URL = "jdbc:mysql://localhost/";
	    final private static String DB_NAME = "mywebsite?useUnicode=true&characterEncoding=utf8";
	    final private static String USER = "root";
	    final private static String PASS = "password";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					URL+DB_NAME,USER,PASS);
			System.out.println("DBConnected!!");
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
