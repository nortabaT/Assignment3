package assignment3;

public class Electronic extends Item
{
	protected boolean fragile;
	protected String deliveryState;
	private static String stateRegex = "^(TX|tx|NM|nm|VS|va|AZ|az|AK|ak)$";

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