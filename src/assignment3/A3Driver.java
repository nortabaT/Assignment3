package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class A3Driver 
{
	static ArrayList<Item> shoppingCart = new ArrayList<Item>();
	static Map<String, Boolean> inputMap = new HashMap<String, Boolean>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		inputMap.put("NP", false);
		inputMap.put("P", true);
		inputMap.put("NF",false);
		inputMap.put("F", true);
		
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processInputFile (args[0]);
		
	}

	public static void processInputFile(String filename) 
	{ 
		
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
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
	
	public static boolean processInput(String transaction)
	{
		boolean isValid = checkInput(transaction);
		Scanner scanner = new Scanner(transaction);
		System.out.println(isValid);
		String operation;
		if(isValid){
			// TODO: finish all operations
			operation = scanner.next();
			if(operation.equalsIgnoreCase("insert")){
				insert(scanner);
			}
			else if(operation.equalsIgnoreCase("print")){
				// TODO: Format this to look nice if needed
				System.out.println(shoppingCart);
			}
			else if(operation.equalsIgnoreCase("search")){
				search(scanner.next());
			}
			else if(operation.equalsIgnoreCase("update")){
				update(scanner.next(), scanner.nextInt());
			}
			else if(operation.equalsIgnoreCase("delete")){
				String name = scanner.next();
				search(name);
				delete(name);
			}
		}
		return isValid;
	}
	
	private static void delete(String name){
		for(int i = 0; i<shoppingCart.size(); i++){
			Item cur = shoppingCart.get(i);
			if(name.equals(cur.getName())){
				shoppingCart.remove(cur);
				break;
			}
		}
	}
	
	private static boolean update(String name, int quantity)
	{
		// TODO: this might have to be changed a little, not tested yet
		for(int i = 0; i<shoppingCart.size(); i++)
		{
			Item cur = shoppingCart.get(i);
			if(name.equals(cur.getName()))
			{
				cur.setQuantity(quantity);
				System.out.println(cur);
				return true;
			}
		}
		return false;
	}
	
	private static void search(String name){
		int quantityFound = 0;
		for(Item item : shoppingCart)
		{
			if(item.getName().equals(name))
			{
				quantityFound++;
			}
		}
		System.out.println(name+" :"+quantityFound);
	}
	
	private static void insert(Scanner input)
	{
		String category = input.next();
		if(category.equalsIgnoreCase("groceries")){
			shoppingCart.add(new Grocery(input.next(), input.nextFloat(), input.nextInt(), input.nextFloat(), inputMap.get(input.next().toUpperCase())));
		}
		else if(category.equalsIgnoreCase("clothing")){
			shoppingCart.add(new Clothing(input.next(), input.nextFloat(), input.nextInt(), input.nextFloat()));
		}
		else if(category.equalsIgnoreCase("electronics")){
			shoppingCart.add(new Electronic(input.next(), Float.valueOf(input.next()), input.nextInt(), Float.valueOf(input.next()), inputMap.get(input.next().toUpperCase()), input.next()));
		}
	}
	
	private static boolean checkInput(String transaction){	
		String name = 		"(\\w+)";									// any word for the name
		String price = 		" ([0-9]*\\.?[0-9]{0,2})";					// floating point number for price in dollars
		String quantity = 	" ([0-9]+)";								// integer for quantity
		String weight = 	" ([0-9]*\\.?[0-9]+)";						// floating point number for weight
		String perishable = "( (p|np))";									// perishable attribute
		String electronicInfo = "( f|nf) (a[lkzr]|c[aot]|de|fl|ga|hi|i[dlna]|k[sy]|la|m[edainsot]|"	// fragile + state attribute
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


