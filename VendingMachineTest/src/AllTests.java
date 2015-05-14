import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(CoinChangerTest.class);
		suite.addTestSuite(CoinReturnTest.class);
		suite.addTestSuite(CoinTest.class);
		suite.addTestSuite(InventoryManagerTest.class);
		suite.addTestSuite(ItemsTest.class);
		suite.addTestSuite(VendingMachineTest.class);
		//$JUnit-END$
		return suite;
	}

}
