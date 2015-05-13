import java.util.ArrayList;


public class MockCoinChanger implements CoinChangerInterface {

	private int timesAcceptWasCalled;
	private ArrayList<Coins> coinArrayList = new ArrayList<Coins>();
	private double total;
	private boolean acceptCoins = true;
	public boolean deductTotalWasCalled= false;
	public double amountDeducted;
	public boolean returnChangeWasCalled = false;
	public CoinReturnInterface coinReturn;
	

	@Override
	public void returnChange() {
		returnChangeWasCalled  = true;

	}

	@Override
	public double getTotal() {
		return total;
	}

	@Override
	public boolean accept(Coins coinToAdd) {
		coinArrayList.add(coinToAdd);
		timesAcceptWasCalled++;
		return acceptCoins;
	}

	public int getNumberOfTimesAcceptWasCalled() {
		return timesAcceptWasCalled;
	}

	public ArrayList<Coins> getListOfCoinsAccepted() {
		return coinArrayList;
	}

	public void setTotal(double totalToSet) {
		total = totalToSet;
	}

	public void setAcceptCoin(boolean acceptCoin) {
		acceptCoins = acceptCoin;
	}
	
	@Override
	public boolean deductAmount(double amountToDeduct){
		deductTotalWasCalled = true;
		amountDeducted = amountToDeduct;
		return true;
	}

	public CoinReturnInterface getCoinReturn() {
		return coinReturn;
	}
	
	public void setCoinReturn(CoinReturnInterface coinReturnToSet){
		coinReturn =coinReturnToSet;
	}
	
	
	
}
