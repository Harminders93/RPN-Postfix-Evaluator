import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * @author HPS251 Harminder Singh
 * N17852268
 * 
 * This is the main class called Evaluator. It takes in 2 arguments, the first being the file to be read,
 * the second being the file to be altered. If the file doesn't exist, it creates one. Similarly,
 * to create the file, an argument to a path would be beneficial.
 * 
 *
 */
public class Evaluator {

	//A reader used to read the first argument.
	private static Scanner reader;
	
	/**
	 * Constructor class for the evaluator. Doesn't do anything.
	 */
	public Evaluator() {
		
	}
	
	/**
	 * 
	 * @param args - User arguments
	 * @throws IOException - If file isn't found.
	 * 
	 * This is the main class for evaluator that does all the work.
	 * This method does print out the stack trace. If it isn't required (The forum post says it's optional, just put "//" before each stack print!
	 */
	public static void main(String[] args) throws IOException {
		
		//If the user doesn't enter any files. It is an error and the program closes.
		if(args.length < 2) {
			System.err.println("Error: Text File not entered in commandline.");
			System.exit(1);
		}
		//If the user entered 1 or more files...
		else {
			//Create a reference to file 2.
			String filename2 = args[1];
			
			//Create an PFE object used to evaluate values.
			PostFixEvaluator eval = new PostFixEvaluator();
			
			//Create an arraylist to store results. Used for writing output file.
			ArrayList<String> results = new ArrayList<String>();
			
			//Read to read each line of the first file.
			reader = new Scanner(new FileInputStream(args[0]));
			
			//Loop through all lines of the first argument
			while(reader.hasNextLine()) {
				try {
					//Change infix to postfix and then evaluate. Lastly, add to results.
					String temps = reader.nextLine();
					temps = eval.InfixToPostfix(temps);
					temps = eval.evaluate(temps);
					results.add(temps);
				} 
				//Catches PFE and adds Invalid to results
				catch (PostFixException e) {
					// TODO Auto-generated catch block
					results.add("Invalid");
					e.printStackTrace();
				}
				//Catches AE and adds Invalid to results
				catch(ArithmeticException e) {
					results.add("Invalid");
					e.printStackTrace();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				//This is the last piece of code executed. This code writes to the second argument.
				//If the argument doesn't exist, it creates a file to that argument. 
				//Note if argument 2 is a path, it creates a file at that path and writes to it.
				finally {
					File file = new File(filename2);
					BufferedWriter output = new BufferedWriter(new FileWriter(file));
					for(String s : results) {
						output.write(s);
						output.newLine();
					}
					//Close output
					output.close();
				}
			}
		}
	}
			
}
