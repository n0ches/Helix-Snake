import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;
import enigma.console.*;
import enigma.core.Enigma;
//
//libraries.

public class SnakeGame {
	
	//enigma.
	enigma.console.Console cn=Enigma.getConsole("Snake",85,35,25,3);
	//game array.
	Object maps[][];
	
	//snake.
	SingleLinkedList snake;
	//variables to determine object indexes.
	private int randomX;
	private int randomY;
	//which codon will be added ?
	private int randomCodon;
	//keyboard listener variable (right-left-up-down)
	private int lastDirection;//right
	//controlling last coordinate for each node.
	private int lastCoordinateX; 
	private int lastCoordinateY;
	//counters for upgrading level with adding new wall to game array.
	private int counterOfWall;
	private int counterOfTime;
	//game level related time.
	private int level;
	//players' all score.
	private Score NewScore;
	//game time.
	private int time;
	//key listener.
	public static int rkey;
	//new keyboard.
	Key keyboard =new Key();
	//scanner.
	Scanner sc=new Scanner(System.in);
	private boolean gameOver;
	int t=60;
	
	
	//constructor
	public SnakeGame(){
		
		//initial values.
		NewScore = new Score();
		maps=new Object[25][60];
		snake=new SingleLinkedList();
		randomX=0;
		randomY=0;
		randomCodon=0;
		counterOfWall=0;
		counterOfTime=0;
		level=0;
	}
	//constructor end.
	
	//initializing score table
	public void initScore() {
		//reading all time high scores from txt file.
		try { 
			NewScore.allTimeHighScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//init end.
	
	//initializing the normal game.
	public void init() throws NumberFormatException, IOException {
		maps=new Object[25][60];
		snake=new SingleLinkedList();
		NewScore.initScore();
		this.gameOver=false;
		randomX=0;
		randomY=0;
		randomCodon=0;
		lastDirection=39;
		counterOfWall=0;
		counterOfTime=0;
		level=0;
		time=0;
		rkey=39;
		//reading aminoacids from txt.
		NewScore.readAminoacids();	
		//first map.
		mapInitialize();
		//first snake with one codon.
		firstSnake(this.snake);
		//printing game array per Main.t
		displayMap(cn);

			
	}
	//init end.
	
	//initializing the advanced game.
	public void initAdvanced() throws NumberFormatException, IOException {
		maps=new Object[25][60];
		snake=new SingleLinkedList();
		NewScore.initScore();
		this.gameOver=false;
		randomX=0;
		randomY=0;
		randomCodon=0;
		lastDirection=39;
		counterOfWall=0;
		counterOfTime=0;
		level=0;
		time=0;
		rkey=39;
		//reading aminoacids from txt.
		NewScore.readAminoacids();	
		//first map.
		mapInitializeAdvanced();
		//first snake with one codon.
		firstSnake(this.snake);
		//printing game array per Main.t
		displayMapAdvanced(cn);
	}
	//init end.
	
	
		// COMMON METHODS FOR TWO GAME MODS \\
	
	//init snake.
			public void firstSnake(SingleLinkedList snake) {
				snake.setHead(null);
				//random function
			      Random random = new Random();
			      //initial values
			      int startingPointY = 0; 
			      int startingPointX = 0;
			      //starting from head
			      
			      //control boolean to check empty indexes of map
			      boolean controller = false;
			      while(controller==false) {
			    	  //random possible indexes.
			    	  startingPointY = random.nextInt(28)+4;
				      startingPointX = random.nextInt(24)+1;
			    	  if((char)maps[startingPointX][startingPointY] == ' ' && (char)maps[startingPointX][startingPointY+1] == ' ' && (char)maps[startingPointX][startingPointY+2] == ' ')
			    		  controller = true;
			    	  
			      }
			      char[] ch = new char[4];
					ch[0] = 'A'; ch[1] = 'T'; ch[2] = 'G'; ch[3] = 'C';
					Random rand = new Random();
					// Randomly generated 3 letters at the start.
					for(int i = 0; i < 3; i++) {
						int rnd = rand.nextInt(4);
						snake.add(ch[rnd],startingPointX, startingPointY);
						startingPointY++; //increase Y index.
					}	
					Node temp = snake.getHead();
					//copy list to game array
			    	while(temp != null) { 
			    	  maps[temp.getCoordinateX()][temp.getCoordinateY()] = temp.getData(); 
			   	      System.out.println(temp.getData());
			   	      System.out.println(maps[startingPointX][startingPointY]);
			    	  temp = temp.getLink();
			    	
			      }
				// For letters
				
			}
			//first snake end.

	public void move(SingleLinkedList snake, Object [][]maps) {
		//temps for x and y.
		int x ;
		int y ;
		Node temp = this.snake.getHead().getLink(); //temp
		while(temp!=null) {
			//temps for last coordinates.
			x = lastCoordinateX;
			y = lastCoordinateY;
			//copy last coordinates again.
			lastCoordinateX = temp.getCoordinateX();
			lastCoordinateY = temp.getCoordinateY();
			//setting new node's indexes.
			temp.setCoordinateX(x);
			temp.setCoordinateY(y);
			temp=temp.getLink();
		}
    	
		temp = snake.getHead();
		//copy node datas to game array.
    	while(temp!=null) {
    		
    		maps[temp.getCoordinateX()][temp.getCoordinateY()] = temp.getData(); // new head indexes.
        	temp=temp.getLink();
    		
    		
    	}
    	//last index of snake must be null.
    	maps[lastCoordinateX][lastCoordinateY] = ' '; 
    	
	}
	//move end.
	
	
	//controlling game is ended or not ?
	public void gameOver(SingleLinkedList snake, Object [][]maps) {
		
		//finishing boolean variable.
		
		//using head to determine.
		int px=snake.getHead().getCoordinateX();
		int py=snake.getHead().getCoordinateY();
		//array bounds control
		if(py==maps[0].length-1 || py==0) {
			gameOver= true;
		}
		else if(px==maps.length-1 || px==0) {
			gameOver= true;
		}
		//array bounds control
		
		//generated walls control
		if(maps[px][py]==(Object)'#') {
			gameOver= true;
		}
		//generated walls control
		
		//eating itself control
		else {
			Node temp=snake.getHead().getLink();
			while(temp!=null) {
				if(temp.getCoordinateX()==px && temp.getCoordinateY()==py) {
					gameOver= true;
					break;
				}
				temp=temp.getLink();
				}
		}
			
			
			
		//eating itself control
		}
	//game over end.
	
	
	
	
	//growing of snake.
	public void grow(SingleLinkedList snake, Object [][]maps) {
		
		
		//variable to determine next index of head is empty or not.
		boolean grow=false;
		//head indexes.
		int px=snake.getHead().getCoordinateX();
		int py=snake.getHead().getCoordinateY();
		
		if(maps[px][py]!=(Object)' ') {
			if(this.lastDirection==40) {//down
				snake.add(maps[px][py], px+1, py);
				maps[px][py]=(Object)' ';
				grow=true;
				NewScore.setCounter(NewScore.getCounter()+1);
			}
			else if(this.lastDirection==39) {//right
				snake.add(maps[px][py], px, py+1);
				maps[px][py]=(Object)' ';
				grow=true;
				NewScore.setCounter(NewScore.getCounter()+1);
			}
			else if(this.lastDirection==38) {//up
				snake.add(maps[px][py], px-1, py);
				maps[px][py]=(Object)' ';
				grow=true;
				NewScore.setCounter(NewScore.getCounter()+1);
			}	
			else if(this.lastDirection==37) {//left
				snake.add(maps[px][py], px, py-1);
				maps[px][py]=(Object)' ';
				grow=true;
				NewScore.setCounter(NewScore.getCounter()+1);
			}
			
			//generating new codon after eating
			if(grow) {
				
				//increasing score.
				NewScore.setScore(NewScore.getScore()+5);
				
				//while.
				
				while(grow) {
								randomCodon=(int)(Math.random()*4)+1;
						          randomX=(int)(Math.random()*22)+1;
						          randomY=(int)(Math.random()*57)+1;
						          if((randomX!=1 & randomY!=1) && (randomX!=23 & randomY!=1) && (randomX!=23 & randomY!=58) && (randomX!=1 & randomY!=58) ) {
						          if (randomCodon==1 && this.maps[randomX][randomY]==(Object)' ' &&this.maps[randomX+1][randomY]==(Object)' '&&this.maps[randomX-1][randomY]==(Object)' '&&this.maps[randomX][randomY+1]==(Object)' '&&this.maps[randomX][randomY-1]==(Object)' ') {
						        	  this.maps[randomX][randomY]='A';
						        	  grow=false;
						    	  }
						          else if (randomCodon==2 && this.maps[randomX][randomY]==(Object)' ' &&this.maps[randomX+1][randomY]==(Object)' '&&this.maps[randomX-1][randomY]==(Object)' '&&this.maps[randomX][randomY+1]==(Object)' '&&this.maps[randomX][randomY-1]==(Object)' ') {
						        	  this.maps[randomX][randomY]='C';
						        	  grow=false;
						    	  }
						          else if (randomCodon==3 && this.maps[randomX][randomY]==(Object)' ' &&this.maps[randomX+1][randomY]==(Object)' '&&this.maps[randomX-1][randomY]==(Object)' '&&this.maps[randomX][randomY+1]==(Object)' '&&this.maps[randomX][randomY-1]==(Object)' ') {
						        	  this.maps[randomX][randomY]='T';
						        	  grow=false;
						    	  }
						          else if (randomCodon==4 && this.maps[randomX][randomY]==(Object)' ' &&this.maps[randomX+1][randomY]==(Object)' '&&this.maps[randomX-1][randomY]==(Object)' '&&this.maps[randomX][randomY+1]==(Object)' '&&this.maps[randomX][randomY-1]==(Object)' ') {
						        	  this.maps[randomX][randomY]='G';
						        	  grow=false;
						    	  }
						        }
						       }
			}
			//generating new codon after eating
		}
		
		
	}
	//grow end.
	
	
	//determining last coordinates.
		public void DetermineLastCoordinateOfHead(int x,int y ) {
			lastCoordinateX = x;
			lastCoordinateY = y;
		}
		//end.
	
				// COMMON METHODS FOR TWO GAME MODS \\
	
	
			// METHODS FOR NORMAL MOD \\
	
	//generating new wall to increase level.
		public void generateWall(Object[][]maps,int time) {
			counterOfWall++;
			//boolean variable to determine empty indexes.
			boolean flag=false;
			
			//adjusting walls with time.
			if(counterOfWall==20000/time) {
				while(!flag) {
					//random indexes.
				randomX=(int)(Math.random()*22)+1;
		        randomY=(int)(Math.random()*57)+1;
		        //
		          if (this.maps[randomX][randomY]==(Object)' ') {
		        	  this.maps[randomX][randomY]='#';
		        	  flag=true;
		        	  level++;
		    	  }
		          //if.
		          //first wall value.
		          counterOfWall=0;
				}
			}
		}
		//wall end.
		
		
	
	//printing game screen.
		public void displayMap(enigma.console.Console cn) {
			
			
			//printing object array
			for (int i = 0; i < 100; i++) {
				System.out.println();
			}
			cn.getTextWindow().setCursorPosition(0, 0);
			//setCursor to fixed.
			
			
			//coloring game screen and printing game array.
		      for (int i = 0; i < 25; i++) {
				for (int j = 0; j < 60; j++) {
					
					if(this.maps[i][j]==(Object)'A') {
						cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(51,255,51)));
						
					}
					else if(this.maps[i][j]==(Object)'T') {
						cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(51,153,255)));
					}
					else if(this.maps[i][j]==(Object)'C') {
						cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(255,83,20)));
					}
					else if(this.maps[i][j]==(Object)'G') {
						cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(148,0,211)));
					}
					else if(this.maps[i][j]==(Object)'#') {
						cn.setTextAttributes(new TextAttributes(new Color(255,204,204),new Color(255,204,204)));
					}
					else {
						cn.setTextAttributes(new TextAttributes(new Color(97,97,97),new Color(97,97,97)));
					}
					System.out.print(this.maps[i][j]);
					cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(0,0,0)));
					
				}
				System.out.println();
			}
		      /////
		      
		      
		      //other game screen properties.
		      cn.setTextAttributes(new TextAttributes(new Color(255,255,255)));
		      cn.getTextWindow().setCursorPosition(65, 1);
		      System.out.println("SCORE: " + NewScore.getScore());
		      cn.getTextWindow().setCursorPosition(65, 2);
		      System.out.println("----------");
		      cn.getTextWindow().setCursorPosition(65, 23);
		      System.out.println("Level: "+level);
		      cn.getTextWindow().setCursorPosition(65, 24);
		      System.out.println("Time: " + time );

		}
		//display map end.
		
		
		
		//initial map and borders.
		public void mapInitialize() {
		      for(int i=0;i<25;i++) {
		    	  if(i==0 || i==24) {
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
		          if (randomCodon==1 && this.maps[randomX][randomY]==(Object)' ') {
		        	  this.maps[randomX][randomY]='A';
		    	  }
		          else if (randomCodon==2 && this.maps[randomX][randomY]==(Object)' ') {
		        	  this.maps[randomX][randomY]='C';
		    	  }
		          else if (randomCodon==3 && this.maps[randomX][randomY]==(Object)' ') {
		        	  this.maps[randomX][randomY]='T';
		    	  }
		          else if (randomCodon==4 && this.maps[randomX][randomY]==(Object)' ') {
		        	  this.maps[randomX][randomY]='G';
		    	  }
		          else {
		        	  i--;
		          }
		          System.out.println(maps[randomX][randomY]);
		          System.out.println("-----------");
			}
		  }
		//mapInitializing end.
	
	//move of snake.
	
	
	
	
	//keyboard moving.
	public void getMove(int rkey) {
		
		//key variable.
		int direction=rkey;
		if(direction == KeyEvent.VK_RIGHT && lastDirection != KeyEvent.VK_LEFT)
		{
			DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());   //copying last head coordinate
			snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()+1);
			
			lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_LEFT&& lastDirection != KeyEvent.VK_RIGHT)
		{
			DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
			snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()-1);
			
			lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_DOWN && lastDirection != KeyEvent.VK_UP)
		{
			DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
			snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()+1);
			
			lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_UP && lastDirection != KeyEvent.VK_DOWN)
		{
			DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
			snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()-1);
			
			lastDirection = direction;
		}
		
		//possibility of non-pressing.
		else {
			if(lastDirection == KeyEvent.VK_RIGHT )
			{
				DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
				snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()+1);
				
			}
			else if(lastDirection == KeyEvent.VK_LEFT)
			{
				DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
				snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()-1);
				
			}
			else if(lastDirection == KeyEvent.VK_DOWN)
			{
				DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
				snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()+1);
				
			}
			else if(lastDirection == KeyEvent.VK_UP)
			{
				DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
				snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()-1);
				
			}
		}
		
		
	}
	//getMove end.
	
						// METHODS FOR NORMAL MOD \\
					
	
						// METHODS FOR ADVANCED MOD \\
	
	//keyboard moving.
			public void getMoveAdvanced(int rkey) {
				
				//key variable.
				int direction=rkey;
				if(direction == KeyEvent.VK_RIGHT && lastDirection != KeyEvent.VK_LEFT)
				{
					DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());   //copying last head coordinate
					snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()+1);
					if(snake.getHead().getCoordinateY()>58) {
						snake.getHead().setCoordinateY(1);
					}
					lastDirection = direction;
				}
				else if(direction == KeyEvent.VK_LEFT&& lastDirection != KeyEvent.VK_RIGHT)
				{
					DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
					snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()-1);
					if(snake.getHead().getCoordinateY()<1) {
						snake.getHead().setCoordinateY(58);
					}
					lastDirection = direction;
				}
				else if(direction == KeyEvent.VK_DOWN && lastDirection != KeyEvent.VK_UP)
				{
					DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
					snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()+1);
					if(snake.getHead().getCoordinateX()>23) {
						snake.getHead().setCoordinateX(1);
					}
					lastDirection = direction;
				}
				else if(direction == KeyEvent.VK_UP && lastDirection != KeyEvent.VK_DOWN)
				{
					DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
					snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()-1);
					if(snake.getHead().getCoordinateX()<1) {
						snake.getHead().setCoordinateX(23);
					}
					lastDirection = direction;
				}
				
				//possibility of non-pressing.
				else {
					if(lastDirection == KeyEvent.VK_RIGHT )
					{
						DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
						snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()+1);
						if(snake.getHead().getCoordinateY()>58) {
							snake.getHead().setCoordinateY(1);
						}
					}
					else if(lastDirection == KeyEvent.VK_LEFT)
					{
						DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
						snake.getHead().setCoordinateY(snake.getHead().getCoordinateY()-1);
						if(snake.getHead().getCoordinateY()<1) {
							snake.getHead().setCoordinateY(58);
						}
					}
					else if(lastDirection == KeyEvent.VK_DOWN)
					{
						DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
						snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()+1);
						if(snake.getHead().getCoordinateX()>23) {
							snake.getHead().setCoordinateX(1);
						}
					}
					else if(lastDirection == KeyEvent.VK_UP)
					{
						DetermineLastCoordinateOfHead(snake.getHead().getCoordinateX(),snake.getHead().getCoordinateY());
						snake.getHead().setCoordinateX(snake.getHead().getCoordinateX()-1);
						if(snake.getHead().getCoordinateX()<1) {
							snake.getHead().setCoordinateX(23);
						}
					}
				}
				
				
			}
			//getMove end.
	
	
	public void generateWallAdvanced(Object[][]maps,int time) {
		counterOfWall++;
		//boolean variable to determine empty indexes.
		boolean flag=false;
		
		//adjusting walls with time.
		if(counterOfWall==10000/time) {
			while(!flag) {
				//random indexes.
			randomX=(int)(Math.random()*22)+1;
	        randomY=(int)(Math.random()*57)+1;
	        //
	        if((randomX>=8 && randomX<=16) && (randomY>=23 && randomY<=30)) {
	        	flag=false;
	        }
	        else if (this.maps[randomX][randomY]==(Object)' ') {
	        	  this.maps[randomX][randomY]='#';
	        	  flag=true;
	        	  level++;
	    	  }
	          //if.
	          //first wall value.
	          counterOfWall=0;
			}
		}
	}
	
	public void displayMapAdvanced(enigma.console.Console cn) {
		
		
		//printing object array
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
		cn.getTextWindow().setCursorPosition(0, 0);
		//setCursor to fixed.
		
		
		//coloring game screen and printing game array.
	      for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 60; j++) {
				
				if(this.maps[i][j]==(Object)'A') {
					cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(124,50,3)));
				}
				else if(this.maps[i][j]==(Object)'T') {
					cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(124,50,3)));
				}
				else if(this.maps[i][j]==(Object)'C') {
					cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(124,50,3)));
				}
				else if(this.maps[i][j]==(Object)'G') {
					cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(124,50,3)));
				}
				else if(this.maps[i][j]==(Object)'#') {
					cn.setTextAttributes(new TextAttributes(new Color(43,61,6),new Color(43,61,6)));
				}
				else if(this.maps[i][j]==(Object)'*') {
					cn.setTextAttributes(new TextAttributes(new Color(124,50,3),new Color(124,50,3)));
				}
				else {
					cn.setTextAttributes(new TextAttributes(new Color(186,196,2),new Color(186,196,2)));
				}
				System.out.print(this.maps[i][j]);
				cn.setTextAttributes(new TextAttributes(new Color(255,255,255),new Color(0,0,0)));
				
			}
			System.out.println();
		}
	      /////
	      
	      
	      //other game screen properties.
	      cn.setTextAttributes(new TextAttributes(new Color(255,255,255)));
	      cn.getTextWindow().setCursorPosition(65, 1);
	      System.out.println("SCORE: " + NewScore.getScore());
	      cn.getTextWindow().setCursorPosition(65, 2);
	      System.out.println("----------");
	      cn.getTextWindow().setCursorPosition(65, 23);
	      System.out.println("Level: "+level);
	      cn.getTextWindow().setCursorPosition(65, 24);
	      System.out.println("Time: " + time );

	}
	//display map end.
	
	public void mapInitializeAdvanced() {
	      for(int i=0;i<25;i++) {
	    	  if(i==0 || i==24) {
	    		  for(int j=0;j<60;j++) {
	        		  this.maps[i][j]='*';
	        	  }
	    	  }
	    	  else {
	    		  for(int j=0;j<60;j++) {
	    			  if(j==0 || j==59) {
	    				  this.maps[i][j]='*';
	    			  }
	    			  else {
	    				  this.maps[i][j]=' ';
	    			  }
	        	  }
	    	  }
	    	  
	      }
	      for (int i = 0; i < 32; i++) {
			this.maps[15][i]='#';
		}
	      for (int i = 0; i < 14; i++) {
				this.maps[i][28]='#';
			}
	      for (int i = 0; i < 29; i++) {
				this.maps[9][31+i]='#';
			}
	      for (int i = 0; i < 14; i++) {
				this.maps[11+i][34]='#';
			}
	      
	      for (int i = 0; i < 3; i++) {
	    	  randomCodon=(int)(Math.random()*4)+1;
	          randomX=(int)(Math.random()*22)+1;
	          randomY=(int)(Math.random()*57)+1;
	          if (randomCodon==1 && this.maps[randomX][randomY]==(Object)' ') {
	        	  this.maps[randomX][randomY]='A';
	    	  }
	          else if (randomCodon==2 && this.maps[randomX][randomY]==(Object)' ') {
	        	  this.maps[randomX][randomY]='C';
	    	  }
	          else if (randomCodon==3 && this.maps[randomX][randomY]==(Object)' ') {
	        	  this.maps[randomX][randomY]='T';
	    	  }
	          else if (randomCodon==4 && this.maps[randomX][randomY]==(Object)' ') {
	        	  this.maps[randomX][randomY]='G';
	    	  }
	          else {
	        	  i--;
	          }
	          System.out.println(maps[randomX][randomY]);
	          System.out.println("-----------");
		}
	  }
	//mapInitializing end.
	
					// METHODS FOR ADVANCED MOD \\
	
	
	
	
	//GET -SET
	public int getLastHeadCoordinateX() {
		return lastCoordinateX;
	}

	public void setLastHeadCoordinateX(int lastHeadCoordinateX) {
		this.lastCoordinateX = lastHeadCoordinateX;
	}

	public int getLastHeadCoordinateY() {
		return lastCoordinateY;
	}

	public void setLastHeadCoordinateY(int lastHeadCoordinateY) {
		this.lastCoordinateY = lastHeadCoordinateY;
	}
	public Score getNewScore() {
		return NewScore;
	}


	public void setNewScore(Score newScore) {
		NewScore = newScore;
	}
	/**
	 * @return the snake
	 */
	public SingleLinkedList getSnake() {
		return snake;
	}


	/**
	 * @return the counterOfTime
	 */
	public int getCounterOfTime() {
		return counterOfTime;
	}




	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}


	/**
	 * @param counterOfTime the counterOfTime to set
	 */
	public void setCounterOfTime(int counterOfTime) {
		this.counterOfTime = counterOfTime;
	}


	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
}
