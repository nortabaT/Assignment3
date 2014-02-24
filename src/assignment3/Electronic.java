package assignment3;

public class Electronic extends Item
{
	protected boolean fragile;
	protected String deliveryState;
	private static String stateRegex = "^(?i)(tx|nm|v[sa]|a[zk])$";

	/**
	 * @see Item
	 * @param fragile boolean if this item is fragile or not
	 * @param state strict 2 letter phrase for the destination shipping state
	 */
	public Electronic(String name, float priceEach, float weight, int quantity, boolean fragile, String state){
		super(name, priceEach, weight, quantity);
		
		this.deliveryState = state;
		this.fragile = fragile;
	}
	
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		
		if(fragile)
		{
			priceTotal += priceTotal*PREMIUM_SHIPPING;
		}
		
		if(!deliveryState.matches(stateRegex))
		{
			priceTotal += priceTotal*TAX;
		}
		return priceTotal;
	}
}