
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


import junit.framework.TestCase;

public class VendingMachineTest extends TestCase {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Override
	protected void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));

		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		System.setOut(null);
		System.setErr(null);
		super.tearDown();
	}

	public void testConstructor() throws Exception {
		CoinChangerInterface coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		assertSame(coinChanger, machine.getCoinChanger());
		assertSame(inventoryManager, machine.getInventoryManager());

	}
	
	public void testPrintTotal() throws Exception {
		MockCoinChanger mockCoinChanger = new MockCoinChanger();
		mockCoinChanger.setTotal(2.50);
		VendingMachine vendingMachine = new VendingMachine(mockCoinChanger,new MockInventoryManager());
		vendingMachine.printTotal();
		assertEquals("Total Amount: $2.50\n", outContent.toString());
		mockCoinChanger.setTotal(.45);
		outContent.reset();
		vendingMachine.printTotal();
		assertEquals("Total Amount: $0.45\n", outContent.toString());
	}

	public void testDisplaysInsertCoinOption_AndCallsCoinChangerQuarters()
			throws Exception {

		MockCoinChanger coinChanger = new MockCoinChanger();
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());

		System.setIn(new ByteArrayInputStream("QqQQq\n".getBytes()));
		machine.makeSelection(new Scanner(System.in), "1");
		assertEquals("INSERT COIN\n", outContent.toString());
		assertEquals(5, coinChanger.getNumberOfTimesAcceptWasCalled());
		assertEquals(Coins.QUARTERS, coinChanger.getListOfCoinsAccepted()
				.get(0));
		assertEquals(Coins.QUARTERS, coinChanger.getListOfCoinsAccepted()
				.get(1));
		assertEquals(Coins.QUARTERS, coinChanger.getListOfCoinsAccepted()
				.get(2));
		assertEquals(Coins.QUARTERS, coinChanger.getListOfCoinsAccepted()
				.get(3));
		assertEquals(Coins.QUARTERS, coinChanger.getListOfCoinsAccepted()
				.get(4));
	}

	public void testDisplaysInsertCoinOption_AndCallsCoinChangerDimes()
			throws Exception {

		MockCoinChanger coinChanger = new MockCoinChanger();
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());

		System.setIn(new ByteArrayInputStream("DdDDd\n".getBytes()));
		machine.makeSelection(new Scanner(System.in), "1");
		assertEquals("INSERT COIN\n", outContent.toString());
		assertEquals(5, coinChanger.getNumberOfTimesAcceptWasCalled());
		assertEquals(Coins.DIMES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.DIMES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.DIMES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.DIMES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.DIMES, coinChanger.getListOfCoinsAccepted().get(0));
	}

	public void testDisplaysInsertCoinOption_AndCallsCoinChangerNickles()
			throws Exception {

		MockCoinChanger coinChanger = new MockCoinChanger();
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());

		System.setIn(new ByteArrayInputStream("NnNnN\n".getBytes()));
		machine.makeSelection(new Scanner(System.in), "1");
		assertEquals("INSERT COIN\n", outContent.toString());
		assertEquals(5, coinChanger.getNumberOfTimesAcceptWasCalled());
		assertEquals(Coins.NICKLES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.NICKLES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.NICKLES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.NICKLES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.NICKLES, coinChanger.getListOfCoinsAccepted().get(0));
	}

	public void testDisplaysInsertCoinOption_AndCallsCoinChangerPennies()
			throws Exception {

		MockCoinChanger coinChanger = new MockCoinChanger();
		coinChanger.setAcceptCoin(false);
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());

		System.setIn(new ByteArrayInputStream("PpPpP\n".getBytes()));

		machine.makeSelection(new Scanner(System.in), "1");
		assertEquals("INSERT COIN\nCoin Rejected\nCoin Rejected\nCoin Rejected\nCoin Rejected\nCoin Rejected\n", outContent.toString());
		assertEquals(5, coinChanger.getNumberOfTimesAcceptWasCalled());
		assertEquals(Coins.PENNIES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.PENNIES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.PENNIES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.PENNIES, coinChanger.getListOfCoinsAccepted().get(0));
		assertEquals(Coins.PENNIES, coinChanger.getListOfCoinsAccepted().get(0));
	}

	public void testDisplaysItemMenue_ColaSelected_AndInStock()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		
		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("1\n".getBytes()));

		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());

		assertTrue(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);

	}
	public void testDisplaysItemMenue_ColaSelected_AndOUTOFSTOCK()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();

		inventoryManager.setIsPossibleToDespense(false);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("1\n".getBytes()));

		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nSOLD OUT\n", outContent.toString());

		assertFalse(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);
		
	}
	public void testDisplaysItemMenue_CHipsSelected_AndInStock()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();

		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("2\n".getBytes()));
		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());
		assertTrue(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);
		
	}
	
	public void testDisplaysItemMenue_CHipsSelected_AndOUTOFSTOCK()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		
		inventoryManager.setIsPossibleToDespense(false);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("2\n".getBytes()));

		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nSOLD OUT\n", outContent.toString());
		
		assertFalse(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);
		
	}
	public void testDisplaysItemMenue_CAndySelected_AndInStock()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		
		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("3\n".getBytes()));
		
		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());
		assertTrue(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);
		
	}
	
	public void testDisplaysItemMenue_CAndySelected_AndOUTOFSTOCK()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		
		inventoryManager.setIsPossibleToDespense(false);
		coinChanger.setIsPossibleToDeduct(true);
		
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		System.setIn(new ByteArrayInputStream("3\n".getBytes()));
		assertFalse(inventoryManager.dispenseWasCalled);
		assertFalse(inventoryManager.isPossibleToDespenseWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nSOLD OUT\n", outContent.toString());
		assertFalse(inventoryManager.dispenseWasCalled);
		assertTrue(inventoryManager.isPossibleToDespenseWasCalled);
		
	}
	
	
	
	public void testChipsHasCorrectAmoutOfMoney() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);

		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		System.setIn(new ByteArrayInputStream("2\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());
		assertTrue(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	public void testChipsHasInsufficientFunds() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		
		System.setIn(new ByteArrayInputStream("2\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nPRICE $0.50\n", outContent.toString());
		assertFalse(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	
	public void testCandyHasCorrectAmoutOfMoney() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		System.setIn(new ByteArrayInputStream("3\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());
		assertTrue(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	public void testCandyHasInsufficientFunds() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		
		System.setIn(new ByteArrayInputStream("3\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nPRICE $0.65\n", outContent.toString());
		assertFalse(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	public void testSodaHasCorrectAmoutOfMoney() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		inventoryManager.setIsPossibleToDespense(true);
		coinChanger.setIsPossibleToDeduct(true);
		
		System.setIn(new ByteArrayInputStream("1\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nTHANK YOU\n", outContent.toString());
		assertTrue(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	public void testSodaHasInsufficientFunds() throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		MockInventoryManager inventoryManager = new MockInventoryManager();
		VendingMachine machine = new VendingMachine(coinChanger,inventoryManager);
		
		coinChanger.setIsPossibleToDeduct(false);
		
		System.setIn(new ByteArrayInputStream("1\n".getBytes()));
		assertFalse(coinChanger.deductTotalWasCalled);
		assertFalse(coinChanger.isPossiableToDeductAmountWasCalled);
		
		machine.makeSelection(new Scanner(System.in), "2");
		assertEquals("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n"
				+ "2)chips $0.50\n"
				+ "3)candy $0.65\nPRICE $1.00\n", outContent.toString());
		assertFalse(coinChanger.deductTotalWasCalled);
		assertTrue(coinChanger.isPossiableToDeductAmountWasCalled);
	}
	
	

	
	
	
		
	public void testReturnChange()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		coinChanger.setTotal(1.00);
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());
		
		System.setIn(new ByteArrayInputStream("3\n".getBytes()));
		
		machine.makeSelection(new Scanner(System.in), "3");
		assertEquals("You hear the machine returning your change\n", outContent.toString());
		assertTrue(coinChanger.returnChangeWasCalled);
		
	}
	public void testCheckCoinReturn()
			throws Exception {
		
		MockCoinChanger coinChanger = new MockCoinChanger();
		coinChanger.setTotal(1.00);
		MockCoinReturn coinReturnToSet = new MockCoinReturn();
		coinReturnToSet.setTotal(3.05);
		coinChanger.setCoinReturn(coinReturnToSet);
		
		VendingMachine machine = new VendingMachine(coinChanger,new MockInventoryManager());
		
		
		machine.makeSelection(new Scanner(System.in), "4");
		assertEquals("You find $3.05 in the coin Return\n", outContent.toString());
		assertTrue(coinReturnToSet.emptyReturnWasCalled);
	}
	
	
	public void testDoNotDisplaysInsertCoinOption() throws Exception {

		VendingMachine machine = new VendingMachine(new MockCoinChanger(),new MockInventoryManager());

		machine.makeSelection(new Scanner(System.in), "0");
		assertEquals("", outContent.toString());

	}

}
