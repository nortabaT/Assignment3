package assignment3;

public class Grocery extends Item
{
	boolean perishable; 

	public Grocery(String name, float priceEach, float weight, int quantity, boolean perishable){
		super(name,priceEach,weight,quantity);
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
