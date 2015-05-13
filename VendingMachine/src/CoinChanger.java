import java.util.ArrayList;

public class CoinChanger implements CoinChangerInterface {

	ArrayList<Coins> changInMachine = new ArrayList<Coins>();
	private CoinReturnInterface coinReturn;

	public CoinChanger(CoinReturnInterface CoinReturn) {
		coinReturn = CoinReturn;

	}

	@Override
	public boolean accept(Coins coinToAdd) {
		if (coinToAdd.equals(Coins.PENNIES)) {
			return false;
		}
		changInMachine.add(coinToAdd);
		return true;
	}

	private double addTotal() {
		double total = 0.0;

		for (Coins coinToAdd : changInMachine) {
			if (coinToAdd.equals(Coins.NICKLES)) {
				total += .05;
			} else if (coinToAdd.equals(Coins.DIMES)) {
				total += .10;
			} else if (coinToAdd.equals(Coins.QUARTERS)) {
				total += .25;
			}
		}
		return total;
	}

	@Override
	public double getTotal() {
		return addTotal();
	}

	
	@Override
	public void returnChange() {
		returnChange(getTotal());
		
	}
	
	
	private void returnChange(double totalToReturn) {

		while (totalToReturn > 0) {
			if (totalToReturn >= .25) {
				coinReturn.add(Coins.QUARTERS);
				totalToReturn -= .25;
			} else if (totalToReturn >= .10) {
				coinReturn.add(Coins.DIMES);
				totalToReturn -= .10;
			} else {
				coinReturn.add(Coins.NICKLES);
				totalToReturn -= .05;
			}
		}

		changInMachine.clear();
	}

	@Override
	public boolean deductAmount(double amountToDeduct) {
		double total = addTotal();
		if (total >= amountToDeduct) {
			total -= amountToDeduct;
			returnChange(total);
			return true;
		}
		return false;
	}

	@Override
	public CoinReturnInterface getCoinReturn() {
		return coinReturn;
	}



}
