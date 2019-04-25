import enigma.core.Enigma;

public class Map {
	private Object maps[][];
	private int randomX;
	private int randomY;
	private int randomCodon;
	
	public Map() {
		maps=new Object[25][60];
		randomX=0;
		randomY=0;
		randomCodon=0;
	}
	public void mapInitialize(enigma.console.Console cn) {
	      for(int i=0;i<24;i++) {
	    	  if(i==0 || i==23) {
	    		  for(int j=0;j<60;j++) {
	        		  this.maps[i][j]='#';
	        	  }
	    	  }
	    	  else {
	    		  for(int j=0;j<60;j++) {
	    			  if(j==0 || j==59) {
	    				  this.maps[i][j]='#';
	    			  }
	    			  else {
	    				  this.maps[i][j]=' ';
	    			  }
	        	  }
	    	  }
	    	  
	      }
	      for (int i = 0; i < 3; i++) {
	    	  randomCodon=(int)(Math.random()*4)+1;
	          randomX=(int)(Math.random()*22)+1;
	          randomY=(int)(Math.random()*57)+1;
	          if (randomCodon==1) {
	        	  this.maps[randomX][randomY]='A';
	    	  }
	          else if (randomCodon==2) {
	        	  this.maps[randomX][randomY]='C';
	    	  }
	          else if (randomCodon==3) {
	        	  this.maps[randomX][randomY]='T';
	    	  }
	          else if (randomCodon==4) {
	        	  this.maps[randomX][randomY]='G';
	    	  }
		}
	      
	      
	      
	      for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j++) {
				System.out.print(this.maps[i][j]);
			}
			System.out.println();
		}
	      cn.getTextWindow().setCursorPosition(65, 1);
	      System.out.println("SCORE: ");
	      cn.getTextWindow().setCursorPosition(65, 2);
	      System.out.println("----------");
	      cn.getTextWindow().setCursorPosition(65, 22);
	      System.out.println("Level: ");
	      cn.getTextWindow().setCursorPosition(65, 23);
	      System.out.println("Time: ");
	}
}
