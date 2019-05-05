
public class Node {
	
	   //SLL
	   private int coordinateX;
	   private int coordinateY;
	   private Object data;
	   private Node link; 
	   
	   //DLL
	   private String name;
	   private int score;
	   private Node prev; 
	   private Node next;
	   
	   //MLL  -  Protein node.
	   private String ProName;
	   private String ProCode;
	   private Node down;
	   private Node right;
	   
	   
	   //MLL -  Codons
	   private String eqCode;
	   private Object CodName;
	   private int point;
	   private Node nextCod;
	   
	   
	 //MLL
	   
	   //protein Node constructor.
	   public Node(String name, String code) {
		   ProName = name;
		   ProCode = code;
		   down = null;
		   right = null;
	   }
	   
	   
	   //codon Node constructor
	   public Node(String eqCode,Object name, int point) {
		   this.eqCode = eqCode; 
		   CodName = name;
		   this.point = point;
		   nextCod= null;
	   }
	   
	   
	   
	   
	   
	   public String getProName() {
		return ProName;
	}




	public void setProName(String proName) {
		ProName = proName;
	}




	public String getProCode() {
		return ProCode;
	}




	public void setProCode(String proCode) {
		ProCode = proCode;
	}




	public Node getDown() {
		return down;
	}




	public void setDown(Node down) {
		this.down = down;
	}




	public Node getRight() {
		return right;
	}




	public void setRight(Node right) {
		this.right = right;
	}




	public Object getCodName() {
		return CodName;
	}




	public void setCodName(Object codName) {
		CodName = codName;
	}




	public int getPoint() {
		return point;
	}




	public void setPoint(int point) {
		this.point = point;
	}




	public Node getNextCod() {
		return nextCod;
	}




	public void setNextCod(Node nextCod) {
		this.nextCod = nextCod;
	}


    /////MLL end.
	

	//DLL
	   public Node(String dataName, int dataScore ) {
	     name = dataName;
	     score = dataScore;
	     prev = null;
	     next = null;
	   }
	   
	   
	  

	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getScore() {
		return score;
	}





	public void setScore(int score) {
		this.score = score;
	}





	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	//SLL
	   public Node(Object dataToAdd, int coordinateX, int coordinateY) {
		     data = dataToAdd;
		     link = null;
		     this.coordinateX = coordinateX;
		     this.coordinateY = coordinateY;
	   }
	   
	   public Object getData() { return data; }
	   public void setData(Object data) { this.data = data;  }

	   public Node getLink() { return link;  }
	   public void setLink(Node link) { this.link = link;   }

	/**
	 * @return the coordinateX
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * @param coordinateX the coordinateX to set
	 */
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	/**
	 * @return the coordinateY
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

	/**
	 * @param coordinateY the coordinateY to set
	 */
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}


	public String getEqCode() {
		return eqCode;
	}


	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}   
	
}
