import enigma.console.Console;
import enigma.core.Enigma;

public class Main {
	
	//static console.
	static Console console;

	public static void main(String[] args) {
		console = Enigma.getConsole(null, 80,25,15, 0);  
		
		MapSnake newGame = new MapSnake();
		
	}

}
