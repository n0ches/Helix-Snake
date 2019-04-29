
public class Node {
	   private int coordinateX;
	   private int coordinateY;
	   private Object data;
	   private Node link; 

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
	
}
