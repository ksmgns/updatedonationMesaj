import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DBAutomationConnection {
public static String main(String KýsaNo) throws ClassNotFoundException, IOException, SQLException {
  DBAutomationConnection dbconn = new DBAutomationConnection();
  //Connection conn = dbconn.DBConnection1();
 // String kisano =JOptionPane.showInputDialog("Kýsa numara:","KýsaNo..."); 
  String kisano =KýsaNo; 
  String a= dbconn.DBConnection1("select HB_Donation.THANKINGMSG from HB_COMPANY INNER JOIN HB_SERVICE on HB_COMPANY.OBJECTID = HB_SERVICE.COMPANYID INNER JOIN HB_Donation ON HB_Donation.SUBCLASSID = HB_SERVICE.OBJECTID WHERE HB_COMPANY.CONTESTSERVICENO ='" +kisano+ "'","");
 // String YeniMetin =JOptionPane.showInputDialog("Yeni Mesaj içeriði:",a);
return a; 
}

public Vector TarihGetir(String KýsaNo) throws ClassNotFoundException{
	
	 Connection connection = null;
	 Statement stmt = null;
	 Vector  A = new Vector();
	  try {
	      // Load the JDBC driver

	      String driverName = "oracle.jdbc.driver.OracleDriver";

	      Class.forName(driverName);

	      connection = DriverManager.getConnection("jdbc:oracle:thin:@//pooldbscan:1462/VASPARTP", "PANDORA", "PANDORA");
	      System.out.println("Connection successful: " +connection);

	      try {
	    	  
	    	   Statement selectStmt = null;
	    	  selectStmt = connection.createStatement();
	          String query="select HB_SERVICE.STARTDATE,HB_SERVICE.STOPDATE  from  HB_SERVICE INNER JOIN HB_COMPANY on HB_SERVICE.COMPANYID = HB_COMPANY.OBJECTID where Contestserviceno='" +KýsaNo+ "'";
			ResultSet rs = selectStmt.executeQuery(query);
			
	          while(rs.next())
	          {
	        	  A.add(rs.getString(1)); 
	        	  A.add(rs.getString(2)); 
	        	  
	        	 
	        	// System.out.println(A.get(1));    //First Column
	              }
	        } catch (SQLException e ) {
	              System.out.println("Could not execute query.");
	              //JDBCTutorialUtilities.printSQLException(e);
	        } finally {
	              if (stmt != null) { stmt.close(); }
	        }


	  } catch (SQLException e) {
	                  System.out.println("Could not connect to the database");
	  }
	return A;

}

public void updateTarih(String Stop , String KýsaNo) throws ClassNotFoundException{
	
	Connection connection = null;
	 Statement stmt = null;
	 String msj = null ;
	/* SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
	 java.util.Date dateStart=null;
	 java.util.Date dateStop=null;
	 try {
	    dateStart =  sdf.parse(Start);
		dateStop = sdf.parse(Stop);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
		

	  try {
	      // Load the JDBC driver

	      String driverName = "oracle.jdbc.driver.OracleDriver";

	      Class.forName(driverName);

	      connection = DriverManager.getConnection("jdbc:oracle:thin:@//pooldbscan:1462/VASPARTP", "PANDORA", "PANDORA");
	      System.out.println("Connection successful: " +connection);

	      try {
	    	 
	    	
	    	  connection.setAutoCommit(false);
	    	   Statement selectStmt = null;
	    	  selectStmt = connection.createStatement();
	    	  System.out.println(Stop);
	          String query="UPDATE HB_SERVICE SET HB_SERVICE.STOPDATE = '" +Stop+ "' WHERE  HB_SERVICE.COMPANYID=(select HB_COMPANY.OBJECTID from  HB_SERVICE INNER JOIN HB_COMPANY on HB_SERVICE.COMPANYID = HB_COMPANY.OBJECTID where Contestserviceno='" +KýsaNo+ "')";
	       	         selectStmt.executeQuery(query);
	       	      JOptionPane.showMessageDialog(null, "Baþarý ile Update edildi.");
	       	      connection.commit();
	       	   
	        } catch (SQLException e ) {
	              System.out.println("Could not execute query.");
	              //JDBCTutorialUtilities.printSQLException(e);
	        } finally {
	              if (stmt != null) { stmt.close(); }
	        }


	  } catch (SQLException e) {
	                  System.out.println("Could not connect to the database");
	  }

}

public void update(String Mesaj, String KýsaNo) throws ClassNotFoundException{
	
	Connection connection = null;
	 Statement stmt = null;
	 String msj = null ;
	  try {
	      // Load the JDBC driver

	      String driverName = "oracle.jdbc.driver.OracleDriver";

	      Class.forName(driverName);

	      connection = DriverManager.getConnection("jdbc:oracle:thin:@//pooldbscan:1462/VASPARTP", "PANDORA", "PANDORA");
	      System.out.println("Connection successful: " +connection);

	      try {
	    	  connection.setAutoCommit(false);
	    	   Statement selectStmt = null;
	    	  selectStmt = connection.createStatement();
	          String query="UPDATE HB_Donation SET HB_Donation.THANKINGMSG = '" +Mesaj+ "' WHERE  HB_Donation.SUBCLASSID=(select HB_SERVICE.OBJECTID from HB_COMPANY INNER JOIN HB_SERVICE on HB_COMPANY.OBJECTID = HB_SERVICE.COMPANYID WHERE HB_COMPANY.CONTESTSERVICENO ='" +KýsaNo+ "')";
	       	         selectStmt.executeQuery(query);
	       	      connection.commit();
	          
	        } catch (SQLException e ) {
	              System.out.println("Could not execute query.");
	              //JDBCTutorialUtilities.printSQLException(e);
	        } finally {
	              if (stmt != null) { stmt.close(); }
	        }


	  } catch (SQLException e) {
	                  System.out.println("Could not connect to the database");
	  }

}

public String DBConnection1(String query, String colName)throws IOException, ClassNotFoundException{
 Connection connection = null;
 Statement stmt = null;
 String msj = null ;
  try {
      // Load the JDBC driver

      String driverName = "oracle.jdbc.driver.OracleDriver";

      Class.forName(driverName);

      connection = DriverManager.getConnection("jdbc:oracle:thin:@//pooldbscan:1462/VASPARTP", "PANDORA", "PANDORA");
      System.out.println("Connection successful: " +connection);

      try {
    	  
    	   Statement selectStmt = null;
    	  selectStmt = connection.createStatement();
          ResultSet rs = selectStmt.executeQuery(query);
          while(rs.next())
          {
        	  msj= rs.getString(1) ; 
        	 // System.out.println(msj);    //First Column
              }
        } catch (SQLException e ) {
              System.out.println("Could not execute query.");
              //JDBCTutorialUtilities.printSQLException(e);
        } finally {
              if (stmt != null) { stmt.close(); }
        }


  } catch (SQLException e) {
                  System.out.println("Could not connect to the database");
  }
return msj;

}




}