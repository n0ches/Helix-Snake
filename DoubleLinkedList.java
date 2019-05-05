import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DoubleLinkedList {
	private Node head;	
	@SuppressWarnings("unused")
	private Node tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
 
	public void add(String dataName, int dataScore) {
		Node newnode;
		if (head == null) {  //list is empty
			newnode = new Node(dataName, dataScore);
			head = newnode;
			tail = newnode;
     
		}
		else {   
			newnode = new Node(dataName, dataScore);
			Node temp = head;
			//add to top
			if(newnode.getScore() >= temp.getScore()) {
				temp.setPrev(newnode);
				newnode.setNext(temp);
				head = newnode;
				
			}
			// 
			else {
				
				while(temp.getNext()!= null && newnode.getScore() < temp.getScore()) {
					temp = temp.getNext();
				}
				//add to end
				if(temp.getNext() == null) {
					temp.setNext(newnode);
					newnode.setPrev(temp);
					tail = newnode;
				}
				else {    //intercalation
					newnode.setPrev(temp.getPrev());
					temp.setPrev(newnode);
					newnode.setNext(temp);
					
				}
			}
			
		}
		
	}
	

		

	// Specified for highscores (TOP10)
	public void display()
	{	
	    
		//create an print writer for writing to a file
	    PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("highscores.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node temp = head;
			int rank = 1;
			while(temp != null && rank <= 10) {
				System.out.println(rank + "- " + "Name: " + temp.getName() + "  -  Score: " + temp.getScore() );
			    //output to the file a line
			    out.println(temp.getName() + ";" + temp.getScore());
				rank++;
				temp = temp.getNext();
			}
		}




	      //close the file
	      out.close();

	}
	
}
