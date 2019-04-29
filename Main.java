import java.awt.event.KeyEvent;

import enigma.console.Console;
import enigma.core.Enigma;

public class Main {
	
	//static console.
	static Console console;
	static int lastDirection=KeyEvent.VK_RIGHT;

	public static void main(String[] args) {
		console = Enigma.getConsole(null, 80,25,15, 0);  
		
		MapSnake newGame = new MapSnake();
		newGame.displayMap(console);
		Game game = new Game(console);
		
	}

}
