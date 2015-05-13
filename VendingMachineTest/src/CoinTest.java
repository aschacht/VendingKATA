import junit.framework.TestCase;


public class CoinTest extends TestCase {

	public void testEnumSize() throws Exception {
		assertEquals(4, Coins.values().length);
	}
	
	public void testNameseOf() throws Exception {
		assertEquals("PENNIES",Coins.PENNIES.toString());
		assertEquals("NICKLES",Coins.NICKLES.toString());
		assertEquals("DIMES",Coins.DIMES.toString());
		assertEquals("QUARTERS",Coins.QUARTERS.toString());
		
	}
	
}
