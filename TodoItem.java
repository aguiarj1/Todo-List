import java.util.Date; 

public class TodoItem{
	private static int idCount = 0; //provided by program 
	public int id; //provided by program
	public String description; //provided by user
       	public Date timestamp; //provided by program 
	public Date dueDate;  //provided by user
	public String status; //provided by user 
	public int parent; //provded by user 


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

	public int getID(){
		return id; 
	}	
	
	public static void setIdCount(int i){
		idCount = i; 	
	}


	public String toString(){
		int[] size = {4,50,60};
		String[][] val = {{Integer.toString(id),description,dueDate.toString()}};
		return TextFormating.makeTable(size,val);
	}

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
		return record.toString();

	}

}
