
public interface InventoryManagerInterface {

	public abstract void dispense(Items itemTypeToRemove);

	public abstract int getNumberOfItems(Items itemToSearchFor);

	public abstract void stock(Items itemToAdd, int numberOfItemsToStock);

	public abstract boolean isPossibleToDespense(Items itemToCheckFor);

}
