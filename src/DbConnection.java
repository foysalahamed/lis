import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class DbConnection {
	static Connection conn=null;
	public static Statement statement;

	public static  Connection dbconnection() throws SQLException {
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
//			    conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
			   conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","lis","lis");
			    statement=conn.createStatement();
				 //JOptionPane.showMessageDialog(null, "Successfully Connected to Oracle");
			    
			   //statement.executeUpdate("CREATE TABLE ProductInfo(productId varchar(20),productName varchar(30),productPrice varchar(20))");
			   return conn;
			}
			catch(ClassNotFoundException ex) {
			  // System.out.println("Error: unable to load driver class!");
			   //System.exit(1);
			   JOptionPane.showMessageDialog(null, ex);
			   return null;
			}
		
	}

}
