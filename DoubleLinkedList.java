import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class DoubleLinkedList {
	enigma.console.Console cn=Enigma.getConsole("Snake",85,35,25,3);
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
			//add to top
			if(newnode.getScore() >= head.getScore()) {
				newnode.setNext(head);
				head.setPrev(newnode);
				head = newnode;
				
			}
			// 
			else {
				Node temp = head;
				Node temp1=null;
				while(temp.getNext()!= null && newnode.getScore() < temp.getScore()) {
					temp1=temp;
					temp = temp.getNext();
				}
				//add to end
				
				if(temp.getNext() != null) {
					newnode.setPrev(temp1);
					newnode.setNext(temp);
					temp1.setNext(newnode);
					temp.setPrev(newnode);
					
				}
				else  {
					if(newnode.getScore()>temp.getScore()) {
						newnode.setPrev(temp1);
						newnode.setNext(temp);
						temp1.setNext(newnode);
						temp.setPrev(newnode);
					}
					else {
						temp.setNext(newnode);
						newnode.setPrev(temp);
					}
					
					//intercalation
				}
				
					
				
			}
			
		}
		
	}
	

		

	// Specified for highscores (TOP10)
	public void display()
	{	
	    clear(cn);
	    		//create an print writer for writing to a file
	    PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("HighScoreTable.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Node temp = head;
			int rank = 1;
			cn.setTextAttributes(new TextAttributes(new Color(0,255,0)));
			System.out.println("                           ___  ___ ___  _ __ ___ ___            ");
			System.out.println("                          / __|/ __/ _ \\| '__/ _ / __|           ");
			System.out.println("                          \\__ | (_| (_) | | |  __\\__ \\  | BACK |     ");
			System.out.println("              ____________|___/\\___\\___/|_|  \\___|___/____________           ");
			System.out.println("             |                                                    |           ");
			System.out.println("             |   RANK     NAME                      SCORE         |           ");
			System.out.println("             |  ¯¯¯¯¯¯   ¯¯¯¯¯¯                    ¯¯¯¯¯¯¯        | ");
			int py=7;
			while(temp != null && rank <= 10) {
				String name=temp.getName().toString().trim();
				cn.getTextWindow().setCursorPosition(13, py);
				System.out.println("|");
				cn.getTextWindow().setCursorPosition(18, py);
				System.out.println(rank + ".  ");
				cn.getTextWindow().setCursorPosition(25, py);
				System.out.println(name);
				cn.getTextWindow().setCursorPosition(53, py);
				System.out.println(temp.getScore());
				cn.getTextWindow().setCursorPosition(67, py);
				System.out.println("|");
			    //output to the file a line
			    out.println(temp.getName() + ";" + temp.getScore());
				rank++;
				py++;
				temp = temp.getNext();
			}
			System.out.println("             |                                                    |");
			System.out.println("             |                                                    |");
			System.out.println("             |____________________________________________________|");
		}
	      //close the file
	      out.close();

	}
	public void clear(enigma.console.Console cn) {
		for (int i = 0; i < 100; i++) {
			   System.out.println();
		}
		   cn.getTextWindow().setCursorPosition(0, 0);
		
	}
	public int ScoreExist(String name) {
        Node temp = head;
        int score = 0;
        while(temp != null) {
            if(temp.getName().equals(name)) {
                score = temp.getScore();
                break;
            }
            else {
                temp = temp.getNext();
            }
        }
        return score;
    }

	//Controls the input name whether is already exist or not
	public boolean isNameExist(String name) {
		Node temp = head;
		boolean flag = false;
		while(temp != null) {
			if(temp.getName().equals(name)) {
				flag = true;
				break;
			}
			else {
				temp = temp.getNext();
			}
		}
		return flag;
	}

	/**
	 * @return the head
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(Node head) {
		this.head = head;
	}
	
}
