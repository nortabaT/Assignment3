package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class A3Driver 
{
	String[] validOperations = {"insert", "search", "delete", "update", "print"};
	String[] validCategories = {"clothing", "electronics", "groceries"};
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
		checkInput(transaction);
		System.out.println(transaction);
		return false;
	}
	
	private static boolean checkInput(String transaction){
		
		Scanner inputScanner = new Scanner(transaction);
		
		String operationRegex = "(insert|search|delete|update|print)";	// valid operations
		String categoryRegex = 	"(groceries|electronics|clothing)";		// valid categories
		String nameRegex = 		"(\\w+)";								// any word for the name
		String priceRegex = 	"([0-9]+(\\.[0-9][0-9]?)?)";			// floating point number for price
		String quantityRegex = 	"([0-9]+)";								// integer for quantity
		String weightRegex = 	"([0-9]+(\\.[0-9]+)?)";					// floating point number for weight
		String optionalRegex1 = "(F|f|NF|nf|NP|np|P|p)";				// Fragile or Perishable attribute
		String optionalRegex2 = "((?i)(a[lkzr]|c[aot]|de|fl|ga|hi|i[dlna]|k[sy]|la|m[edainsot]|"	// Matching states
								+ "n[evhjmycd]|o[hkr]|pa|ri|s[cd]|t[nx]|ut|v[ta]|w[aviy]))";
		
		return false;
	}

}


