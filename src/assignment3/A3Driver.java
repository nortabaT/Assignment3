package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class A3Driver 
{
	static ArrayList<Item> shoppingCart = new ArrayList<Item>();
	static Map<String, Boolean> inputMap = new HashMap<String, Boolean>();
	
	/**
	 * @param args text file that is used for the shopping cart
	 */
	public static void main(String[] args) 
	{

		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		
		processInputFile (args[0]);
		
	}
	/*
	 * process input file and sends lines of the input to the driver
	 */
	public static void processInputFile(String filename) 
	{ 
		
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			initInputMap();
			
			for (String in = reader.readLine(); in != null; in = reader.readLine()) 
			{
				processInput(in);
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	/*
	 * Processes the input string and determines which transaction is to be performed
	 * @param isvalid verifies a correct input stream for all possible transactions
	 * @param operation, the operation chosen by the input
	 * @param scanner bring in another line from the input file
	 */
	public static void processInput(String transaction)
	{
		boolean isValid = checkInput(transaction);
		Scanner scanner = new Scanner(transaction);
		String operation;
		if(isValid)
		{
			operation = scanner.next();
			if(operation.equalsIgnoreCase("insert"))
			{
				insert(scanner);
			}
			else if(operation.equalsIgnoreCase("print"))
			{
				print();
			}
			else if(operation.equalsIgnoreCase("search"))
			{
				search(scanner.next());
			}
			else if(operation.equalsIgnoreCase("update"))
			{
				update(scanner.next(), scanner.nextInt());
			}
			else if(operation.equalsIgnoreCase("delete"))
			{
				delete(scanner.next());
			}
		}
		else
		{
			System.out.println("Error in request: '"+transaction+"'\n");
		}
	}
	/*
	 * performs the print transaction
	 */
	private static void print(){
		float cartTotal = 0.0f;
		Collections.sort(shoppingCart);
		
		System.out.println("Shopping cart: ");
		for(Item a : shoppingCart){
			cartTotal += a.getTotalCost();
			System.out.println(a);
		}
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.println("Total cost: "+formatter.format(cartTotal)+"\n");
	}
	/* 
	 * performs the delete transaction. Iterators through the shopping cart and deletes the appropriate value and prints to the screen
	 */
	private static void delete(String name){
		int quantity = 0;	
		Iterator<Item> it = shoppingCart.iterator();
		
		while(it.hasNext()){
			Item item = it.next();
			if(item.getName().equalsIgnoreCase(name))
			{
				it.remove();
				quantity++;
			}
		}
		
		if(quantity > 0)
		{
			System.out.println("Deleted item: "+name+" "+quantity+" time(s).\n");
		}
		else
		{
			System.out.println("Error deleting item not in cart: "+name+"\n");
		}
	}
	/* 
	 * performs the update transaction. Iterators through the shopping cart and deletes the appropriate value
	 */
	private static void update(String name, int quantity)
	{
		if(quantity < 0){
			System.out.println("Error updating item: quantity too low!");
			return;
		}
		
		boolean updated = false;
		for(int i = 0; i<shoppingCart.size(); i++)
		{
			Item cur = shoppingCart.get(i);
			if(name.equalsIgnoreCase(cur.getName()))
			{
				cur.setQuantity(quantity);
				System.out.println("Updated item:\nName: "+name+" Quantity: "+quantity+"\n");
				updated = true;
				break;
			}
		}
		
		if(!updated){
			System.out.println("Error updating item not in cart: "+name+"\n");
		}
	}
	/* 
	 * performs the search transaction. searches through the shopping cart and prints the item found
	 */
	private static void search(String name){
		int requests = 0;
		int quantity = 0;
		for(Item item : shoppingCart)
		{
			if(item.getName().equalsIgnoreCase(name))
			{
				quantity += item.getQuantity();
				requests++;
			}
		}
		System.out.println("Searched item: "+name+" \nItems in cart: "+requests+"\nQuantity total: "+quantity+"\n");
	}
	/* 
	 * performs the insert transaction. Adds a new item to the shopping cart
	 */
	private static void insert(Scanner input)
	{
		String category = input.next();
		String name = input.next();
		float priceEach = input.nextFloat();
		int quantity = input.nextInt();
		float weightEach = input.nextFloat();
		
		if(quantity <= 0)
		{
			System.out.println("Error inserting item: "+name+", quantity too low!\n");
			return;
		}
		
		if(category.equalsIgnoreCase("groceries")){
			shoppingCart.add(new Grocery(name, priceEach, quantity, weightEach, inputMap.get(input.next().toUpperCase())));
		}
		else if(category.equalsIgnoreCase("clothing")){
			shoppingCart.add(new Clothing(name, priceEach, quantity, weightEach));
		}
		else if(category.equalsIgnoreCase("electronics")){
			shoppingCart.add(new Electronic(name, priceEach, quantity, weightEach, inputMap.get(input.next().toUpperCase()), input.next()));
		}
	}
	/*
	 * initiates the input mapping
	 */
	private static void initInputMap()
	{
		inputMap.put("NP", false);
		inputMap.put("P", true);
		inputMap.put("NF",false);
		inputMap.put("F", true);
	}
	
	/**
	 * Performs all of the desired regex on the entire input stream and associates to appropriate boolean values  for that transaction
	 * @param transaction input from text file
	 * @return boolean if text input is technically valid
	 */
	private static boolean checkInput(String transaction){	
		String name = 		"(\\w+)";					// any word for the name
		String price = 		" ([0-9]*\\.?[0-9]{0,2})";	// floating point number for price in dollars
		String quantity = 	" ([0-9]+)";				// integer for quantity
		String weight = 	" ([0-9]*\\.?[0-9]+)";		// floating point number for weight
		String perishable = "( (p|np))";				// perishable attribute
		String electronicInfo = "( f| nf) (a[lkzr]|c[aot]|de|fl|ga|hi|i[dlna]|k[sy]|la|m[edainsot]|"	// fragile + state attribute
								+ "n[evhjmycd]|o[hkr]|pa|ri|s[cd]|t[nx]|ut|v[ta]|w[aviy])";
		
		boolean grocery = transaction.matches("(?i)^(insert groceries) "+name+price+quantity+weight+perishable+"$");
		boolean clothing = transaction.matches("(?i)^(insert clothing) "+name+price+quantity+weight+"$");
		boolean electronic = transaction.matches("(?i)^(insert electronics) "+name+price+quantity+weight+electronicInfo+"$");
		boolean deleteOrSearch = transaction.matches("(?i)^(delete|search) "+name+"$");
		boolean update = transaction.matches("(?i)^(update) "+name+quantity+"$");
		boolean print = transaction.matches("(?i)^(print)$");
		
		return grocery|clothing|electronic|deleteOrSearch|update|print;
	}

}


