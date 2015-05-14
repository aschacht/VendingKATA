

import junit.framework.TestCase;

public class CoinChangerTest extends TestCase {

	
	
	public void testConstructor() throws Exception {
		MockCoinReturn coinReturn = new MockCoinReturn();
		CoinChangerInterface coinChanger = new CoinChanger(coinReturn);
		assertSame(coinReturn,coinChanger.getCoinReturn());
	}
	
	
	
	
	public void testOnlyAcceptsNiclkesDimesAndQuarteres() throws Exception {
		CoinChangerInterface changer = new CoinChanger(new MockCoinReturn());
		assertTrue(changer.accept(Coins.NICKLES));
		assertTrue(changer.accept(Coins.DIMES));
		assertTrue(changer.accept(Coins.QUARTERS));
		assertFalse(changer.accept(Coins.PENNIES));
		
	}
	
	
	public void testCoinChangerReturnsChangeToCoinReturn() throws Exception {
		MockCoinReturn coinReturn = new MockCoinReturn();
		CoinChangerInterface changer = new CoinChanger(coinReturn);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		
		assertEquals(1.00,changer.getTotal(),0.01);
		changer.returnChange();
		assertEquals(4,coinReturn.numberOfTimesAddWasCalled);
		assertEquals(0.00,changer.getTotal(),0.01);

	}

	
	public void testDeductAmount() throws Exception {
		CoinChangerInterface changer = new CoinChanger(new CoinReturn());
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		
		assertEquals(1.00,changer.getTotal(),0.01);
		changer.deductAmount(1.00);
		assertEquals(0.00,changer.getTotal(),0.01);
		assertEquals(0.00,changer.getCoinReturn().getTotal(),0.01);

		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.NICKLES);
		changer.accept(Coins.DIMES);
		
		assertEquals(.90,changer.getTotal(),0.01);
		changer.deductAmount(.45);
		assertEquals(0.00,changer.getTotal(),0.01);
		assertEquals(0.45,changer.getCoinReturn().getTotal(),0.01);

		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.NICKLES);
		changer.accept(Coins.DIMES);
		changer.accept(Coins.DIMES);
		changer.accept(Coins.DIMES);
		changer.accept(Coins.DIMES);
		
		assertEquals(.95,changer.getTotal(),0.01);
		changer.deductAmount(.05);
		assertEquals(0.00,changer.getTotal(),0.01);
		assertEquals(1.35,changer.getCoinReturn().getTotal(),0.01);

		
		
		
		
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
	
		
		assertEquals(.50,changer.getTotal(),0.01);
		changer.deductAmount(.85);
		assertEquals(0.50,changer.getTotal(),0.01);
		assertEquals(1.35,changer.getCoinReturn().getTotal(),0.01);

	
	
	
	}
	
	
	
	public void testCanYouDeductAmount() throws Exception {
	
		CoinChangerInterface changer = new CoinChanger(new CoinReturn());
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		changer.accept(Coins.QUARTERS);
		
		assertTrue(changer.isPossibleToDeductAmount(1.00));
		assertFalse(changer.isPossibleToDeductAmount(1.25));

		changer.accept(Coins.NICKLES);
		changer.accept(Coins.DIMES);

		assertTrue(changer.isPossibleToDeductAmount(1.15));
		assertFalse(changer.isPossibleToDeductAmount(1.25));

	}
	
	
	
}
