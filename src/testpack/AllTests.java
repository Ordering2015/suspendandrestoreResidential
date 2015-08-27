package testpack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GetCustomerInfoDAOtest.class, OrderInfoDAOTest.class,DatabaseConnectionTest.class })
public class AllTests {

}
