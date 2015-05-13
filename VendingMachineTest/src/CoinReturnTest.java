import junit.framework.TestCase;


public class CoinReturnTest extends TestCase {
	
	public void testAddingChangeToCoinReturn_AndEmptyingRetrun() throws Exception {
		CoinReturnInterface coinReturn = new CoinReturn();
		coinReturn.add(Coins.DIMES);
		coinReturn.add(Coins.DIMES);
		coinReturn.add(Coins.DIMES);
		
		assertEquals(.30,coinReturn.getTotal(),.01);
		
		coinReturn.add(Coins.QUARTERS);
		assertEquals(.55,coinReturn.getTotal(),.01);
		coinReturn.add(Coins.DIMES);
		assertEquals(.65,coinReturn.getTotal(),.01);
		coinReturn.emptyReturn();
		assertEquals(0.00,coinReturn.getTotal(),.01);
	}

}
