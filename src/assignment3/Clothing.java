package assignment3;

public class Clothing extends Item
{
	
	public Clothing(String name, float priceEach, float weight, int quantity)
	{
		super(name, priceEach, weight, quantity);
	}
	
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		priceTotal += priceTotal*TAX;
		return priceTotal;
	}
}
