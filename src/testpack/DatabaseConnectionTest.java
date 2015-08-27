package testpack;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.com.dao.GetCustomerInfoDAO;

public class DatabaseConnectionTest {
	public static Connection expected = null;

	@Before
	public void initEnvironment() {
		System.out.println("Creating Test Environment");
	}

	@Test
	public void testUTC_01() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		expected = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.121:1521:orcl", "hr", "hr");
		Connection actual = GetCustomerInfoDAO.createConnection();
		assertNotNull(actual);
	}

	@After
	public void clear() {
		System.out.println("Removing Test Environment");
	}




}
