import java.util.Date; 

/*
 * This class represents a project. 
 * @author Joel Aguiar
 */

public class Project {

	//***************************************************
	// Fields
	//***************************************************
 	private static int idCount = 0; 
	private int id; 	
	private String projectDescription; 
	private Date projectStartDate;
	private Date projectEndDate;
	private Date timestamp; 

	//***************************************************
	// Constructors
	// **************************************************

	public Project(String desc, Date d){
		projectDescription = desc; 
		projectEndDate = d; 
		id = idCount; 
		idCount++; 
		timestamp = new Date();
		projectStartDate = new Date(); 		

	}

	public Project(int id, String projectDescription, Date timeS, Date projectStartDate,
		       Date projectEndDate){
		this.id=id; 
		this.projectDescription=projectDescription;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate; 
		this.timestamp = timeS;

	}

	//***************************************************
	// Public Methods
	// **************************************************
	
	public String getProjectDescription(){
		return projectDescription; 
	}

	public Date getDueDate(){
		return projectEndDate; 
	}

	public int getID(){
		return id; 
	}

	
	/**
 	* This method provides a string representation of all the fields in the 
 	* project item, so that it can be saved in a text file and easily parsed. It
 	* sepeartes all records with "|".
 	* @return a String representation of all the records.   
 	*
 	*/ 	
	public String provideRecord(){
		StringBuilder record = new StringBuilder(); 
		record.append(id);
		record.append("|"); 
	       	record.append(projectDescription);
		record.append("|");	
	     	record.append(timestamp.getTime());
	       	record.append("|");
		record.append(projectStartDate.getTime());
		record.append("|"); 
		record.append(projectEndDate.getTime()); 
		record.append("|");	
		return record.toString();

	}
	
	public static void setIdCount(int startingID){
		idCount = startingID;

	}

	@Override 
	public String toString(){
		int[] size = {4,50,60};
		String[][] val = {{Integer.toString(id),projectDescription,projectEndDate.toString()}};
		return TextFormating.makeTable(size,val);
	
	}

}