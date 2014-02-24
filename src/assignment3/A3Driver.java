package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class A3Driver 
{
	ArrayList<Item> shoppingCart = new ArrayList<Item>();

	/**
	 * @param args
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
		System.out.println(isValid);
		// TODO: if isValid==true do whatever was requested (start shopping, etc)
		
		return isValid;
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


