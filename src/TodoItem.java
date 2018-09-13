
import java.util.Date; 

/**
 *   This class represents a todo item. 
 *   @author Joel Aguiar
 */


public class TodoItem{

// ******************************************************
// Fields
// ******************************************************

	private static int idCount = 0; //id count of todoItem
	private int id; //id of a specific TodoTiem
	private String description; //description of todo item
       	private Date timestamp; //when it was inputted 
	private Date dueDate;  //when todoitem is due
	private String status; //whehter it is done or not
	private int parent; //any parent todoitem. 
	private Project projectAssoc; //the project it is 
       				//associated with	
// ******************************************************
// Constructors
// ******************************************************

	public TodoItem(String desc, Date d){
		id = idCount; 
		idCount++; 
		description = desc; 
		timestamp = new Date(); 
		dueDate = d; 
		status = "in progress";
	       	parent = -1; //flag		
	}

	public TodoItem(int id, String desc, Date timestamp, 
			Date d, String status, int parent){
		this.id = id; 
		this.description = desc; 
		this.timestamp = timestamp;
		this.dueDate =d; 
       		this.status = status; 
 		this.parent = parent; 		
	}


	public TodoItem(int id, String desc, Date timestamp, 
			Date d, String status, int parent, String project){
		this.id = id; 
		this.description = desc; 
		this.timestamp = timestamp;
		this.dueDate =d; 
       		this.status = status; 
 		this.parent = parent;
		projectAssoc = findProject(project); 		
	}

	public Project findProject(String project){
		//FIXME	
		return null; 
	}

	
	// ******************************************************
	// Public methods
	// ******************************************************


	/**
 	* This method returns the due date. 
 	* @return the date that the task is due
 	*/	 	
	public Date getDueDate(){
		return dueDate; 
	}

	/**
 	* This method gets the ID of the todo item.
 	* @return id the id of the todoitem
 	*/ 
	public int getID(){
		return id; 
	}	
	
	/**
 	*  This method sets the starting point for the id param.
 	*  @param startingID takes a number that is the starting point of id 
 	*/ 
	public static void setIdCount(int startingID){
		idCount = startingID; 	
	}

	@Override 
	public String toString(){
		int[] size = {4,50,60};
		String[][] val = {{Integer.toString(id),description,dueDate.toString()}};
		return TextFormating.makeTable(size,val);
	}

	/**
 	* This method provides a string representation of all the fields in the 
 	* todo item, so that it can be saved in a text file and easily parsed. It
 	* sepeartes all records with "|".
 	* @return a String representation of all the records.   
 	*
 	*/ 	
	public String provideRecord(){
		StringBuilder record = new StringBuilder(); 
		record.append(id);
		record.append("|"); 
	       	record.append(description);
		record.append("|");	
	     	record.append(timestamp.getTime());
	       	record.append("|");
		record.append(dueDate.getTime());
		record.append("|"); 
		record.append(status); 
		record.append("|");	
		record.append(parent); 
		record.append("|");
		if(projectAssoc != null){
			record.append(projectAssoc.getProjectDescription().hashCode()); 
		} else {
			record.append("   "); 
		}
		return record.toString();

	}

}
