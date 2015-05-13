public class MockCoinReturn implements CoinReturnInterface {

	private double total;
	public int numberOfTimesAddWasCalled;
	public boolean emptyReturnWasCalled=false;

	@Override
	public void add(Coins coin) {
		numberOfTimesAddWasCalled++;
	}

	@Override
	public void emptyReturn() {
		emptyReturnWasCalled = true;
	}

	@Override
	public double getTotal() {
		return total;
	}

	public void setTotal(double totalToSet) {
		total = totalToSet;
	}

}
