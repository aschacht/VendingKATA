import java.util.Scanner;


public class VendingMachineMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String optionSelected = "0";
		InventoryManager inventoryManager = new InventoryManager();
		inventoryManager.stock(Items.CANDY, 1);
		inventoryManager.stock(Items.CHIPS, 1);
		inventoryManager.stock(Items.SODA, 1);
		VendingMachine vendingMachine = new VendingMachine(new CoinChanger(new CoinReturn()),inventoryManager);
		
		
		
		while(!optionSelected.equalsIgnoreCase("-1")){
			System.out
			.print("\nPlease Select An Option Enter Number To Select\n"
					+ "1)Enter Coins\n"
					+ "2)Select an Item\n"
					+ "3)Return Change\n"
					+ "4)Check Change Return\n");
			optionSelected = scanner.nextLine();
			vendingMachine.makeSelection(scanner, optionSelected);
			vendingMachine.printTotal();
		}
		
		

	}

}
