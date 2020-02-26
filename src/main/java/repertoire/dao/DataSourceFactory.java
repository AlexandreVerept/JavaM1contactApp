package repertoire.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Philippe Duval (adapted by Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept)
 */
public class DataSourceFactory {

	/**
	 * @return a connection to the Database inferred by the URL
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/contactapp" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "java";
		String password = "java";
		
		Connection cnx = DriverManager.getConnection(url,username,password);
		if (cnx != null) {
			DatabaseMetaData meta = cnx.getMetaData();
			//System.out.println("The driver name is " + meta.getDriverName());
		}
		return cnx;

	}
}
