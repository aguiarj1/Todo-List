import java.util.ArrayList; 
import java.util.LinkedList; 
public class ProjectList {

	//**************************************************
	// Fields
	// *************************************************

	private LinkedList<Project> projectList;
	private final String projectFilename = "ProjectRecords.txt";
	private File projectFile;    
    	private final String doneProjectFileName = "ProjectDone.txt";
	private File projectDoneFile;


// ******************************************************
// Constructors 
// ******************************************************
	

	//The constructor. It makes a list and loads data when available. 
	public ProjectList() throws IOException{
		projectList = new LinkedList<Project>(); 
		//load saved Todo Items
		loadOrCreateFile(); 
	}

// ******************************************************
// Private methods
// ******************************************************
	
	//This method creates a file or add the content of a file to TodoList
	private void loadOrCreateFile() throws IOException{
		File file = new File(projectFilename); 
		projectFile = file; 
		boolean b = false; 
		if(!file.exists()){
			//if file doesn't exist create it
			b = file.createNewFile(); 
		} else {
			//if file does exist add all the todos 
			getRecords(); 
		}
	}


	//This method read the data from a file and converts them to TodoItems
	//and adds them to the list
	private void getRecords() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(projectFile)); 
		String st;
	       	String[] data; 	
		int biggestID=-1;  
		//capture each line /todo 
		while((st = br.readLine()) != null){
			data = st.split("\\|"); 
			//create new todoItems and put them in Array
			//make a new object
			TodoItem item = new TodoItem(Integer.parseInt(data[0]),
					data[1], new Date(Long.parseLong(data[2])),
					new Date(Long.parseLong(data[3])), data[4],
					Integer.parseInt(data[5])); 

			//add it to arraylist 
			projectList.add(item); 
			if(Integer.parseInt(data[0]) > biggestID){
				biggestID = Integer.parseInt(data[0]); 
			}
		}
		//change the starting ponit of the ID's based on biggest id
		biggestID++; 
		TodoItem.setIdCount(biggestID); 
	}

	private void addToLinkedListInOrder(TodoItem item){
		if(projectList.size() != 0){
			//search for the index of the first item that is greater
			int index = -1; 
			for(int i =0; i<projectList.size(); i++){
				if(item.getDueDate().before(projectList.get(i).getDueDate())){
					index = i; 
					projectList.add(index, item); 
					break; 
				}	
			}
			if(index == -1){
				projectList.addLast(item); 
			}
		} else {
			projectList.add(item); 	
		}

	}


// ******************************************************
// Public methods
// ******************************************************
	
	

	//This method deletes an item from the list
	public void done(int n) throws IOException{
		//find objec that matched that ID and return it
		int deleteIndex =-1;
	       	int counter = 0; 	
		for(TodoItem x : projectList){
			if(x.getID()  == n){
				deleteIndex = counter; 
			}
			counter++;
		}
		File file = new File(doneProjectFileName); 
		projectDoneFile = file; 
		if(!file.exists()){
			file.createNewFile(); 
		} 
		//append the item to be deleted to the end of the file
		System.out.println("item deleted: " + projectList.get(deleteIndex).provideRecord());
		FileWriter fw = new FileWriter(file, true);
		fw.write(projectList.get(deleteIndex).provideRecord());
		fw.write("\n"); 
		fw.flush(); 
		fw.close(); 	
		
		projectList.remove(deleteIndex); 
	}

	


	//This method writes the data back to the file 
	public void saveRecords() throws IOException{
		FileWriter fw = new FileWriter(projectFile, false);
		for(TodoItem i : projectList){
			fw.write(i.provideRecord());
			fw.write("\n"); 
		}
		fw.flush(); 
		fw.close(); 


	}
	//This method adds a TodoItem to the list. It assume that the date
	//will be inputted like this: 1/22/18  MONTH, DAY, YEAR	
	public  void add(String description, String date){
	     	int slashCount =0;
		StringBuilder tempMonth = new StringBuilder();
		StringBuilder tempDay = new StringBuilder(); 
		StringBuilder tempYear = new StringBuilder(); 
	       //parse date	
		for(int i = 0; i< date.length(); i++){
			if(date.charAt(i) == '/'){
			       	slashCount++; 	
			} else {
				if(slashCount == 0){
					tempMonth.append(date.charAt(i)); 	
				}
				if(slashCount == 1){
					tempDay.append(date.charAt(i)); 
				}
				if(slashCount == 2){
					tempYear.append(date.charAt(i)); 
				}
			}
		}	
		Date d = new Date(Integer.parseInt(tempYear.toString()) - 1900, 
				Integer.parseInt(tempMonth.toString())-1,
			       	Integer.parseInt(tempDay.toString())); 	
		TodoItem item = new TodoItem(description, d); 
		addToLinkedListInOrder(item); 
	}
	
	//This method prints out the TodoItems on the list
	public void view(){
		for(TodoItem i : projectList){
			System.out.print(i.toString()); 
		}		


	}
		


}
