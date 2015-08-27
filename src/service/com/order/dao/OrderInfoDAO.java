package service.com.order.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;

public class OrderInfoDAO {
	
	public Connection createConnection() {
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
	
	public int insertOrderInfo( String cusID, String serviceID, String orderType, String orderStatus ) throws SQLException {
		int res=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = createConnection();
			String sqlStr = "insert into customer_orders(customer_id, order_id, order_negotiation_date,order_due_date, order_completion_date,order_status, order_type) Values(?,order_sequence.nextval, sysdate, sysdate+2, sysdate ,'BR' , ? ) ";
			pstmt = con.prepareStatement(sqlStr);
			pstmt.setInt( 1,Integer.parseInt(cusID) );
            pstmt.setString( 2,orderType);
           res=pstmt.executeUpdate();
            /*if(res>0)  result="inserted successfully";
            throw new SQLException("Values Not Inserted");*/
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
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
		
		return res;
	}
	
	public String updateCustomerServices( String cusID, String serviceID, String currentStatus) throws Exception {
		String result;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = createConnection();
			String sqlStr = "update customer_services set service_current_status=?, service_effective_date= sysdate where (customer_id=? and service_id=?)";
			pstmt = con.prepareStatement(sqlStr);
			pstmt.setInt( 2,Integer.parseInt(cusID) );
            pstmt.setString( 1,currentStatus);
            pstmt.setString( 3,serviceID);
            pstmt.executeUpdate();
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
		result="updated successfully";
		return result;
	}
}
