import java.util.ArrayList;

public class CoinReturn implements CoinReturnInterface {

	ArrayList<Coins> coinsInReturn = new ArrayList<Coins>();

	@Override
	public void add(Coins coin) {
		coinsInReturn.add(coin);
	}

	private double addTotal() {
		double total = 0.0;

		for (Coins coinToAdd : coinsInReturn) {
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
	public void emptyReturn() {
		coinsInReturn.clear();
	}

}
