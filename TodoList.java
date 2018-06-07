import java.util.ArrayList; 
import java.util.Date; 
import java.io.*; 
import java.io.IOException; 
import java.util.LinkedList; 

/**
 * This class represents a list of TodoItems. This is a list of TodoItems 
 * and several methods to add, view, and delete from the list
 * @author Joel Aguiar
 */

public class TodoList {

// ******************************************************
// Fields
// ******************************************************
	private ArrayList<TodoItem> list; 
	private LinkedList<TodoItem> listLL; 
	private final String TodoFilename = "TodoRecords.txt";
       	private File todoFile; 	
	private final String doneTodoFileName = "done.txt";
	private File doneFile; 

// ******************************************************
// Constructors 
// ******************************************************
	

	//The constructor. It makes a list and loads data when available. 
	public TodoList() throws IOException{
		list = new ArrayList<TodoItem>(); 
		listLL = new LinkedList<TodoItem>(); 
		//load saved Todo Items
		loadOrCreateFile(); 
	}

// ******************************************************
// Private methods
// ******************************************************
	
	//This method creates a file or add the content of a file to TodoList
	private void loadOrCreateFile() throws IOException{
		File file = new File(TodoFilename); 
		todoFile = file; 
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
		BufferedReader br = new BufferedReader(new FileReader(todoFile)); 
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
			list.add(item);
			addToLinkedListInOrder(item);  
			if(Integer.parseInt(data[0]) > biggestID){
				biggestID = Integer.parseInt(data[0]); 
			}
		}
		//change the starting ponit of the ID's based on biggest id
		biggestID++; 
		TodoItem.setIdCount(biggestID); 
	}

	private void addToLinkedListInOrder(TodoItem item){
		if(listLL.size() != 0){
			//search for the index of the first item that is greater
			int index = -1; 
			for(int i =0; i<listLL.size(); i++){
				if(item.getDueDate().before(listLL.get(i).getDueDate())){
					index = i; 
					listLL.add(index, item); 
					break; 
				}	
			}
			if(index == -1){
				listLL.addLast(item); 
			}
		} else {
			listLL.add(item); 	
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
		for(TodoItem x : list){
			if(x.getID()  == n){
				deleteIndex = counter; 
			}
			counter++;
		}
		File file = new File(doneTodoFileName); 
		doneFile = file; 
		if(!file.exists()){
			file.createNewFile(); 
		} 
		//append the item to be deleted to the end of the file
		System.out.println("item deleted: " + list.get(deleteIndex).provideRecord());
		FileWriter fw = new FileWriter(file, true);
		fw.write(list.get(deleteIndex).provideRecord());
		fw.write("\n"); 
		fw.flush(); 
		fw.close(); 	
		
		list.remove(deleteIndex); 
	}

	


	//This method writes the data back to the file 
	public void saveRecords() throws IOException{
		FileWriter fw = new FileWriter(todoFile, false);
		for(TodoItem i : list){
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
		list.add(item);

	}
	
	//This method prints out the TodoItems on the list
	public void view(){
		for(TodoItem i : list){
			System.out.print(i.toString()); 
		}
		System.out.println("\nLINKED LINST\n"); 
		for(TodoItem i : listLL){
			System.out.print(i.toString()); 
		}		


	}
}
