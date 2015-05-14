import junit.framework.TestCase;


public class InventoryManagerTest extends TestCase {

	public void testStockingTheInventory() throws Exception {
		InventoryManagerInterface inventoryManager = new InventoryManager();
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		inventoryManager.stock(Items.CANDY,100);
		assertEquals(100,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		
		inventoryManager.stock(Items.SODA,19);
		assertEquals(100,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(19,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.stock(Items.CHIPS,1090);
		assertEquals(100,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(1090,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(19,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.stock(Items.SODA,50);
		assertEquals(100,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(1090,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(69,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.stock(Items.CHIPS,1);
		assertEquals(100,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(1091,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(69,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.stock(Items.CANDY,34);
		assertEquals(134,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(1091,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(69,inventoryManager.getNumberOfItems(Items.SODA));
		
		
		
	}
	
	public void testDispensingItem() throws Exception {

		InventoryManagerInterface inventoryManager = new InventoryManager();
		inventoryManager.stock(Items.CANDY,2);
		inventoryManager.stock(Items.CHIPS,3);
		inventoryManager.stock(Items.SODA,4);



		inventoryManager.dispense(Items.CHIPS);
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(4,inventoryManager.getNumberOfItems(Items.SODA));
		
		inventoryManager.dispense(Items.CHIPS);
		assertEquals(1,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(4,inventoryManager.getNumberOfItems(Items.SODA));
		
		inventoryManager.dispense(Items.CHIPS);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(4,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.dispense(Items.CHIPS);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(4,inventoryManager.getNumberOfItems(Items.SODA));
		

		inventoryManager.dispense(Items.SODA);
		assertEquals(3,inventoryManager.getNumberOfItems(Items.SODA));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		
		inventoryManager.dispense(Items.SODA);
		assertEquals(2,inventoryManager.getNumberOfItems(Items.SODA));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		
		inventoryManager.dispense(Items.SODA);
		assertEquals(1,inventoryManager.getNumberOfItems(Items.SODA));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));

		inventoryManager.dispense(Items.SODA);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));

		inventoryManager.dispense(Items.SODA);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		assertEquals(2,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		
		inventoryManager.dispense(Items.CANDY);
		assertEquals(1,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		
		inventoryManager.dispense(Items.CANDY);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));

		inventoryManager.dispense(Items.CANDY);
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CANDY));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.CHIPS));
		assertEquals(0,inventoryManager.getNumberOfItems(Items.SODA));
		
	}
	
	public void testIsPossibleToDespense() throws Exception {
		InventoryManagerInterface inventoryManager = new InventoryManager();
		inventoryManager.stock(Items.CANDY,2);
		inventoryManager.stock(Items.SODA,4);
		
		assertTrue(inventoryManager.isPossibleToDespense(Items.CANDY));
		assertTrue(inventoryManager.isPossibleToDespense(Items.SODA));
		assertFalse(inventoryManager.isPossibleToDespense(Items.CHIPS));
		
	}
	
}
