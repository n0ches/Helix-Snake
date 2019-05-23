


public class SingleLinkedList {
	private Node head;
	
	public SingleLinkedList()
	{
		head = null;
	}
	
	public void add(Object dataToAdd,int coordinateX, int coordinateY) {
		Node newNode = new Node(dataToAdd,coordinateX,coordinateY);
		if(head == null) {
			head = newNode;
		}
		else {
			newNode.setLink(head);
			head = newNode;
		}
	}
	public String display()
	{
	    String output = "";
	    
	    Node temp = head;
		
		while(temp != null)
		{
		   output += temp.getData() + " ";
		   temp = temp.getLink();
		}
	    
	    return output;
	}
	
	public int size()
	{
		if(head == null)
		{
			System.err.println("The Linked List is empty");
			return 0;
		}
		else
		{
			int counter = 0;
			
			Node temp = head;
			while(temp != null)
			{
				counter++;
				temp = temp.getLink();
			}
			
			return counter;
		}
	}
	public boolean search(Object dataToSearch)
	{
		if(head == null)
		{
			return false;
		}
		else
		{
		    Node temp = head;
		    
		    boolean retVal = false;
		    while(temp != null)
		    {
		    	if((int)temp.getData() == (int) dataToSearch)
		    	{
		    		retVal = true;
		    		break;
		    	}
		    	
		    	temp = temp.getLink();
		    }
		    
		    return retVal;
		}
	}
	public String firstTreeLetters() {
		
		Node temp = head;
		String output = "";
		for(int i = 0; i < 3; i++) {
			output += temp.getData();
			temp = temp.getLink();
		}
		
		return output;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
}
