import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import enigma.console.Console;
import enigma.core.Enigma;

public class Main {
	
	//static console.
	static Console console;
	static int lastDirection=KeyEvent.VK_RIGHT;

	public static void main(String[] args) throws IOException {
		console = Enigma.getConsole(null, 80,25,15, 0);  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MapSnake newGame = new MapSnake();
		newGame.displayMap(console);
		br.readLine();
		
		  
		  Timer myTimer = new Timer();
		  TimerTask gorev = new TimerTask(){
		 
		  		@Override
		  		public void run(){
		  			Game game = new Game(console);
		  		}
		  };
		  
		  
		  myTimer.schedule(gorev,0,500); //  10000ms = 1sn
		
	}

}
