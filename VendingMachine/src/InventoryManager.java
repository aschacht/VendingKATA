import java.util.ArrayList;

public class InventoryManager implements InventoryManagerInterface {

	private ArrayList<Items> itemInventory = new ArrayList<Items>();

	@Override
	public void stock(Items itemToAdd, int numberOfItemsToStock) {
		for (int i = 0; i < numberOfItemsToStock; i++) {
			addItem(itemToAdd);

		}
	}

	private void addItem(Items itemToAdd) {

		if (itemToAdd.equals(Items.CANDY)) {
			itemInventory.add(Items.CANDY);
		} else if (itemToAdd.equals(Items.CHIPS)) {
			itemInventory.add(Items.CHIPS);
		} else if (itemToAdd.equals(Items.SODA)) {
			itemInventory.add(Items.SODA);
		}
	}

	@Override
	public int getNumberOfItems(Items itemToSearchFor) {
		int count = 0;
		for (Items items : itemInventory) {
			if (items.equals(itemToSearchFor)) {
				count++;
			}
		}

		return count;
	}

	@Override
	public void dispense(Items itemTypeToRemove) {

		for (int i = 0; i < itemInventory.size();i++) {
			if (itemInventory.get(i).equals(itemTypeToRemove)) {
				itemInventory.remove(i);
				break;
			}
		}
	}

	@Override
	public boolean isPossibleToDespense(Items itemToCheckFor) {
		return getNumberOfItems(itemToCheckFor)>0;
	}

}
