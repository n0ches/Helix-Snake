import java.util.Timer;
import java.util.TimerTask;


public class Main {
	
	//changeable timer clock.
	static int t=100;
	
	public static void main(String[] args) throws Exception {
		/*char wall=(int)255;
		System.out.println(wall);*/
		
		
		//new SnakeGame object.
		SnakeGame game=new SnakeGame();
		
		//first properties for starting of game.
		game.init();
		
		
		//timer starting.
		Timer myTimer = new Timer();
		  TimerTask gorev = new TimerTask(){
		 
		  		@Override
		  		public void run(){
		  			
		  			//all game process.
		  			game.play();
		  			
		  		}
		  };
		  
		  //playing game per t.
		  myTimer.schedule(gorev,0,t); //  10000ms = 1sn
		
	}
	
}
