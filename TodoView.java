import java.util.Scanner; 
import java.io.IOException; 

public class TodoView{
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(System.in); 
		TodoList list = new TodoList(); 
		//open saved to do file or if no file create a new one 

		System.out.println("Welcome to do the to-do app!");
		do{

			System.out.println("What do you want to do?" );
			String response = reader.nextLine(); 
			String[] responseArr = response.split(" "); 
			StringBuilder description = new StringBuilder();

			if(responseArr[0].equals("add")){	
				for(int j=1; j<responseArr.length -1; j++){
					description.append(responseArr[j] + " "); 
				}
				list.add(description.toString(), responseArr[responseArr.length-1]); 

			} else if(responseArr[0].equals("view")){
				list.view(); 
			
			} else if(responseArr[0].equals("quit")){
				//save before quiting to file
				list.saveRecords(); 
				break; 
			} else if(responseArr[0].equals("done")){

				list.done(Integer.parseInt(responseArr[1])); 
			}

		}while(true); 

	}



}
