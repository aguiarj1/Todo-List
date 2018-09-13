
import java.util.*;

/**
 * This class has methods that format text to be displayed in the command line
 * @author Joel Aguiar
 */

public class TextFormating{
	
	// ******************************************************
	// Private methods
	// ******************************************************

	//processes one row	
	private static String processOneRow(int[] rowSize, String[] values){
		StringBuilder result = new StringBuilder();		
		processOneRow(rowSize, values, result, false); 
		return result.toString(); 	

	}

	//recursively processes one row	
	private static void processOneRow(int[] rowSize, String[] values, StringBuilder result, boolean finished){
		//stopping case 
		if(finished){
			return; 
		}
		int done =0; 
		//put everything that fits into result array
		//and keep track if finished 
		for(int i =0; i< values.length; i++){
			//if it fits 
			if(values[i].length() <= rowSize[i]){
				//put padding and then put it result
				result.append(makeCorrectSize(values[i], rowSize[i])); 
				values[i] = ""; 	
				done++; 
			} else {	
				//if it doesn't fit only put the part that fits and save the rest in array
				result.append(makeCorrectSize(values[i].substring(0,rowSize[i]-2), rowSize[i] ));
				values[i] = values[i].substring(rowSize[i]-2);	
			}
		}
		result.append("\n"); 
		if(done == values.length) finished = true; 	
		//recurse
		processOneRow(rowSize, values, result, finished); 
	}
	
	//adds whitespace to the right of any string
	private static String makeCorrectSize(String s, int length){
		StringBuilder sb = new StringBuilder(s);  
		for(int i = sb.length(); i<length; i++){
                        sb.append(" ");
                }
		return sb.toString(); 

	}

	// ******************************************************
	// Public methods
	// ******************************************************
		
	/**
 	* This method formats a 2d array into a table to be displayed in the command line.
	* It wraps text when it reaches the limit size for a particular column. 
	* @param rowSize an array representing the length of ech of the 
	*	columns. 
	* @param values - an array representing the text that will be 
	*	displayed in each row. 
	* @return A formatted string that is displayed as a table 
	*/
	public static String makeTable(int[] rowSize, String[][] values){
		StringBuilder result = new StringBuilder(); 
		for(int i=0; i<values.length;i++){
			result.append(processOneRow(rowSize, values[i])); 
		}
		return result.toString();
	}

}
