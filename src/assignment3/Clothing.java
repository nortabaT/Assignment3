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
	/*
	 * (non-Javadoc)
	 * @see assignment3.Item#getTotalCost()
	 */
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		priceTotal += priceTotal*TAX;
		return priceTotal;
	}
}
