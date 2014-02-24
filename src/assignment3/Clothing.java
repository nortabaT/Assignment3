package assignment3;

public class Clothing extends Item
{
	/**
	 * @see Item
	 */
	public Clothing(String name, float priceEach, int quantity, float weight)
	{
		super(name,priceEach,quantity,weight);
		getTotalCost();
	}
	
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		priceTotal += priceTotal*TAX;
		return priceTotal;
	}
}
