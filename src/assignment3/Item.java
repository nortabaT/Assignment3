package assignment3;

import java.text.NumberFormat;

public class Item implements Comparable<Item>
{
	protected String name;
	protected float priceEach;
	protected float price;
	protected float priceTotal;
	protected float weight;
	protected int quantity;
	protected static final float STANDARD_SHIPPING = .10f;
	protected static final float PREMIUM_SHIPPING = .20f;
	protected static final float TAX = .10f;
	
	/**
	 * @param name item name
	 * @param price amount in dollars
	 * @param weight weight in pounds
	 * @param quantity number requested
	 */
	public Item(String name, float priceEach, int quantity, float weight){
		this.name = name;
		this.priceEach = priceEach;
		this.weight = weight;
		this.quantity = quantity;
		
		price = quantity*priceEach;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
		getTotalCost();
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public String getName(){
		return name;
	}
	
	public float getTotalCost()
	{
		priceTotal = price + 20*weight*quantity;
		priceTotal += priceTotal*TAX;
		return priceTotal;
	}

	@Override
	public int compareTo(Item item) {
		
		return this.name.toUpperCase().compareTo(item.getName().toUpperCase());
	}
	
	public String toString(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return "Name: "+name+"\tQuantity: "+quantity+"\tPrice: "+formatter.format(priceTotal);
	}	
}
