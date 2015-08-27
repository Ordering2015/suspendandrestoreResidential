package testpack;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.com.order.dao.OrderInfoDAO;

public class OrderInfoDAOTest {

	OrderInfoDAO oi;
	@Before

	public void initEnvironment()//SETS UP TESTING ENVIRONMENT

	{
	System.out.println("creating test environment");
	
	}

	@Test
	public void testInsertOrder() throws Exception{
		try{
		oi=new OrderInfoDAO();
        int returnValue1= oi.insertOrderInfo("4","1","Suspend","PR");
        assertEquals(returnValue1,1);
		}catch(SQLException e){
			fail("Failed"+e.getMessage());
		}
    			
	}

	@Test
	public void testupdateCustomerServices() throws Exception{
		oi=new OrderInfoDAO();
        String returnValue1=(String) oi.updateCustomerServices("2","1","Suspended");
        assertEquals("updated successfully",returnValue1);
    			
	}
	
/*	@Test
	public void testUTC_01() throws SQLException {
		oi=new OrderInfoDAO();
		System.out.println("insufficient records inserted");
		
		int a = actual.size();
		assertEquals(4, a);
	}
*/
	
	@After
	public void clear()//RELEASES TESTING ENVIRONMENT

	{
		System.out.println("cleaning test environment");
	}
}
