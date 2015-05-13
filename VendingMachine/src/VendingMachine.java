import java.io.Console;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {

	private CoinChangerInterface coinChanger;

	public VendingMachine(CoinChangerInterface coinChanger) {
		this.coinChanger = coinChanger;
	}

	public void makeSelection(Scanner scanner, String optionSelected) {

		if (optionSelected.equalsIgnoreCase("1")) {
			coinAcceptance(scanner);
		} else if (optionSelected.equalsIgnoreCase("2")) {
			itemSelection(scanner);

		} else if (optionSelected.equalsIgnoreCase("3")) {
			System.out.print("You hear the machine returning your change\n");
			coinChanger.returnChange();
		} else if (optionSelected.equalsIgnoreCase("4")) {
			collectChangeFromCoinReturn();

		}
	}

	private void collectChangeFromCoinReturn() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(coinChanger.getCoinReturn()
				.getTotal());

		System.out.print("You find " + moneyString
				+ " in the coin Return\n");
		coinChanger.getCoinReturn().emptyReturn();
	}

	private void itemSelection(Scanner scanner) {
		System.out.print("Please Select From the Following Items\n"
				+ "1)cola  $1.00\n" + "2)chips $0.50\n" + "3)candy $0.65\n");
		String itemSelection = scanner.nextLine();

		if (itemSelection.equals("1")) {
			coinChanger.deductAmount(1.00);
			System.out.print("THANK YOU\n");
		} else if (itemSelection.equals("2")) {
			coinChanger.deductAmount(.50);
			System.out.print("THANK YOU\n");

		} else if (itemSelection.equals("3")) {
			coinChanger.deductAmount(.65);
			System.out.print("THANK YOU\n");
		}
	}

	private void coinAcceptance(Scanner scanner) {
		System.out.print("INSERT COIN\n");
		String coins = scanner.nextLine();
		acceptCoins(coins);
	}

	private void acceptCoins(String coins) {
		char[] coinArray = coins.toCharArray();
		ArrayList<Coins> coinsToAccept = new ArrayList<Coins>();
		generateCoinsToAccept(coinArray, coinsToAccept);

		for (Coins acceptableCoin : coinsToAccept) {
			boolean accept = coinChanger.accept(acceptableCoin);
			if (!accept) {
				System.out.print("Coin Rejected\n");
			}

		}
	}

	private void generateCoinsToAccept(char[] coinArray,
			ArrayList<Coins> coinsToAccept) {
		for (int i = 0; i < coinArray.length; i++) {
			if (coinArray[i] == 'q' || coinArray[i] == 'Q') {
				coinsToAccept.add(Coins.QUARTERS);
			} else if (coinArray[i] == 'd' || coinArray[i] == 'D') {
				coinsToAccept.add(Coins.DIMES);
			} else if (coinArray[i] == 'n' || coinArray[i] == 'N') {
				coinsToAccept.add(Coins.NICKLES);
			} else if (coinArray[i] == 'p' || coinArray[i] == 'P') {
				coinsToAccept.add(Coins.PENNIES);
			}
		}
	}

	public CoinChangerInterface getCoinChanger() {
		return coinChanger;
	}

	public void printTotal() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format(coinChanger.getTotal());

		System.out.print("Total Amount: " + moneyString + "\n");
	}

}
