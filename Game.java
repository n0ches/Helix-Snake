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
	
	
	
	
	public Game(enigma.console.Console cn) {
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
	      
	      move(rkey);
	}
	
	public void move(int direction) {
		if(direction == KeyEvent.VK_RIGHT && Main.lastDirection != KeyEvent.VK_LEFT)
		{
			MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()+1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_LEFT&& Main.lastDirection != KeyEvent.VK_RIGHT)
		{
			MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()-1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_DOWN && Main.lastDirection != KeyEvent.VK_UP)
		{
			MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()+1);
			Main.lastDirection = direction;
		}
		else if(direction == KeyEvent.VK_UP && Main.lastDirection != KeyEvent.VK_DOWN)
		{
			MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()-1);
			Main.lastDirection = direction;
		}
		else
		{
			if(Main.lastDirection == KeyEvent.VK_RIGHT )
			{
				MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()+1);
			}
			else if(Main.lastDirection == KeyEvent.VK_LEFT)
			{
				MapSnake.snake.getHead().setCoordinateX(MapSnake.snake.getHead().getCoordinateX()-1);
			}
			else if(Main.lastDirection == KeyEvent.VK_DOWN)
			{
				MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()+1);
			}
			else if(Main.lastDirection == KeyEvent.VK_UP)
			{
				MapSnake.snake.getHead().setCoordinateY(MapSnake.snake.getHead().getCoordinateY()-1);
			}
		}
		
	}
}
	

	
	



		

				
	

