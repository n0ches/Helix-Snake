import enigma.console.Console;
import enigma.core.Enigma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game {
	/*    ÖRNEK TIMER KULLANIMI:,
	 * 
	 * Timer myTimer = new Timer();
	 * TimerTask gorev = new TimerTask(){
	 * 
	 * 		@Override
	 * 		public void run(){
	 * 				System.Out.Println("Merhaba);
	 * 		}
	 * };
	 * 
	 * 
	 * myTimer.schedule(gorev,0,500);   10000ms = 1sn
		
			
			
	*/
	public KeyListener klis;
	public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	private int lastCoordinateX; //controlling last coordinate for each node.
	private int lastCoordinateY;
	
	
	
	//constructor
	public Game(enigma.console.Console cn) {
		//getting snake direction from user.
		keypr=0;
		klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      cn.getTextWindow().addKeyListener(klis);
	      
	      determineDirection(rkey);
	      move(MapSnake.snake, MapSnake.maps);
	      screen(MapSnake.maps, Main.console);
	}
	//cosntructor end
	
	
	
	//taking direction from user.
	public void determineDirection(int direction) {
		if(direction == KeyEvent.VK_RIGHT && Main.lastDirection != KeyEvent.VK_LEFT)
		{
			DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());   //copying last head coordinate
			MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()+1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_LEFT&& Main.lastDirection != KeyEvent.VK_RIGHT)
		{
			DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
			MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()-1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_DOWN && Main.lastDirection != KeyEvent.VK_UP)
		{
			DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
			MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()+1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_UP && Main.lastDirection != KeyEvent.VK_DOWN)
		{
			DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
			MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()-1);
			Main.lastDirection = direction;
		}
		else
		{
			if(Main.lastDirection == KeyEvent.VK_RIGHT )
			{
				DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
				MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()+1);
			}
			else if(Main.lastDirection == KeyEvent.VK_LEFT)
			{
				DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
				MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()-1);
			}
			else if(Main.lastDirection == KeyEvent.VK_DOWN)
			{
				DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
				MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()+1);
			}
			else if(Main.lastDirection == KeyEvent.VK_UP)
			{
				DetermineLastCoordinateOfHead(MapSnake.snake.getHead().getCoordinateX(),MapSnake.snake.getHead().getCoordinateY());
				MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()-1);
			}
		}
		
	}

	
	
	//moving operations.
	public void move(SingleLinkedList snake, Object [][]maps) {
		//temps for x and y.
		int x ;
		int y ;
		Node temp = snake.getHead().getLink(); //temp
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
	
	
	
	//printing game screen.
	public void screen(Object [][]maps,Console console) {
		
  	
    	//adjusting game screen.
    	for (int i = 0; i < 100; i++) {
			System.out.println();
		}
    	console.getTextWindow().setCursorPosition(0, 0);
    	//printing new game array.
    	 for (int i = 0; i < 24; i++) {
 			for (int j = 0; j < 60; j++) {
 				System.out.print(maps[i][j]);
 			}
 			System.out.println();
    	 }
    	 ////other game screen properties.
    	  console.getTextWindow().setCursorPosition(65, 1);
	      System.out.println("SCORE: ");
	      console.getTextWindow().setCursorPosition(65, 2);
	      System.out.println("----------");
	      console.getTextWindow().setCursorPosition(65, 22);
	      System.out.println("Level: ");
	      console.getTextWindow().setCursorPosition(65, 23);
	      System.out.println("Time: ");
	}
	
	
	
	
	
	//keeping last head coordinates.
	public void DetermineLastCoordinateOfHead(int x,int y ) {
		lastCoordinateX = x;
		lastCoordinateY = y;
	}
	

	
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



		

				
	
	}
