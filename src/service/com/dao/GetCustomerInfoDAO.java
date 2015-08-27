package service.com.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import service.com.dto.Services;

public class GetCustomerInfoDAO {
	
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String uname = "hr";
			String password = "hr";
			String url = "jdbc:oracle:thin:@192.168.0.121:1521:orcl";
			con = DriverManager.getConnection(url, uname, password);
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return con;
	}
	
	public Vector<Services> getCustomerInfo( String cusID ) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<Services>  customersVect = new Vector<Services>();
		
		try {
			con = createConnection();
			System.out.println("entered try dao block ");
			
			String sqlStr = "select a.service_id, b.service_name, a.service_current_status from customer_services a, service_description b where a.customer_id="+Integer.parseInt(cusID)+ "AND a.service_id=b.service_id AND (a.service_current_status='Active' OR a.service_current_status='Suspended')";
			pstmt = con.prepareStatement( sqlStr );
			rs = pstmt.executeQuery();
			System.out.println("query executed ");
			while( rs.next() ) {
				System.out.println("while entered");
                Services response = new Services();
                            
                response.setServiceID(rs.getString("service_id") );
                response.setServiceName( rs.getString("service_name") );
                response.setStatus( rs.getString("service_current_status") ); 
                System.out.println(response.getServiceName());
                /** add employee instance into vector list */
                customersVect.add(response);
             }
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return customersVect;
}
}