package testpack;
import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.com.dao.GetCustomerInfoDAO;
import service.com.dto.Services;

public class GetCustomerInfoDAOtest {
	GetCustomerInfoDAO info;
	@Before

	public void initEnvironment()//SETS UP TESTING ENVIRONMENT

	{
	System.out.println("creating test environment");
	 info=new GetCustomerInfoDAO();
	}

	@Test
	public void testUTC_01() throws Exception {
		
		String input1="2";
		Services s1=new Services("1","Wireless","Active");
		Services s2=new Services("2","FIOS","Suspended");
		Services s3=new Services("3","WirelineTV","Active");
		Vector<Services> list =new Vector<Services>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
				
		GetCustomerInfoDAO gd=new GetCustomerInfoDAO();
		Vector<Services> actual=gd.getCustomerInfo(input1);
		assertEquals(list.get(0).getServiceID(),actual.get(0).getServiceID());
		assertEquals(list.get(0).getServiceName(),actual.get(0).getServiceName());
		
		
				
	}
	@After
	public void clear()//RELEASES TESTING ENVIRONMENT

	{
		System.out.println("cleaning test environment");
	}
}
