
public interface CoinChangerInterface {

	public abstract CoinReturnInterface getCoinReturn();

	public abstract boolean deductAmount(double amountToDeduct);

	public abstract void returnChange();

	public abstract double getTotal();

	public abstract boolean accept(Coins coinToAdd);

}
