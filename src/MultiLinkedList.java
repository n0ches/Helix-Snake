
public class MultiLinkedList {
	
	private Node head;
	
	public MultiLinkedList() {
		head = null;
	}
	
	//adding new protein.
	public void addProtein(String name, String code) 
	{
		if(head == null)
		{
			Node newnode = new Node(name,code);
			head = newnode;
		}
		else
		{ 
			Node temp = head;
			while(temp.getDown()!=null)
				temp = temp.getDown();
			Node newnode = new Node(name,code);
			temp.setDown(newnode);
		}
	}
	//
	
	
	
	public void addCodon(String code,Object name, int point)
	{
		Node temp = head;
		while(temp!=null) {
			if(code.equalsIgnoreCase(temp.getProCode()))
			{
				Node temp2 = temp.getRight();
				if(temp2 == null)
				{
					Node newnode = new Node(code,name,point);
					temp.setRight(newnode);
				}
				else
				{
					while(temp2.getNextCod() != null)
						temp2 = temp2.getNextCod();
					Node newnode = new Node(code,name,point);
					temp2.setNextCod(newnode);
				}
				//else
			}
			//if
			temp=temp.getDown();
		}
		//while
		
	}
	//add
	
	
	public void display()
	{
		Node temp = head;
		while(temp!= null)
		{
			System.out.print(temp.getProName() + " - "  + temp.getProCode()+ " => "  );
			Node temp2 = temp.getRight();
			while(temp2 != null)
			{
				System.out.print(temp2.getCodName() + "  - "  + temp2.getPoint() + "  ," );
				temp2 = temp2.getNextCod();
			}
			temp = temp.getDown();
			System.out.println();
		}
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
	
	
}
