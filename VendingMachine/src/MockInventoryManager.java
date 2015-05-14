
public class MockInventoryManager implements InventoryManagerInterface {

	public boolean dispenseWasCalled=false;
	public boolean isPossibleToDespenseWasCalled;
	public boolean despense;

	@Override
	public void dispense(Items itemTypeToRemove) {
		dispenseWasCalled=true;
	}

	@Override
	public int getNumberOfItems(Items itemToSearchFor) {
		return 0;
	}

	@Override
	public void stock(Items itemToAdd, int numberOfItemsToStock) {

	}


	@Override
	public boolean isPossibleToDespense(Items itemToCheckFor) {
		isPossibleToDespenseWasCalled = true;
		return despense;
	}

	
	public void setIsPossibleToDespense(boolean despenseToSet){
		despense = despenseToSet;
	}
}
