package assignment3;

import java.text.NumberFormat;

public class Grocery extends Item
{
	boolean perishable; 

	/**
	 * @see Item
	 * @param perishable boolean if this item is perishable or not
	 */
	public Grocery(String name, float priceEach, int quantity, float weight, boolean perishable){
		super(name,priceEach,quantity,weight);
		this.perishable = perishable;
		getTotalCost();
	}
	/*
	 * (non-Javadoc)
	 * @see assignment3.Item#getTotalCost()
	 */
	public float getTotalCost(){
		priceTotal = price + 20*weight*quantity;
		
		if(perishable)
		{
			priceTotal += priceTotal*PREMIUM_SHIPPING;
		}
		return priceTotal;
	}
	/*
	 * (non-Javadoc)
	 * @see assignment3.Item#toString()
	 */
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return "Name: "+name+"\tQuantity: "+quantity+"\tPrice: "+formatter.format(priceTotal)+"\tPerishable: "+perishable;
	}	
}
