package assignment3;

public class Grocery extends Item
{
	boolean perishable; 

	/**
	 * @see Item
	 * @param perishable boolean if this item is perishable or not
	 */
	public Grocery(String name, float priceEach, float weight, int quantity, boolean perishable){
		super(name,priceEach,weight,quantity);
		this.perishable = perishable;
	}
	
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		
		if(perishable)
		{
			priceTotal += priceTotal*PREMIUM_SHIPPING;
		}
		return priceTotal;
	}
}
