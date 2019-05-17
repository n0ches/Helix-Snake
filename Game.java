import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import enigma.console.TextAttributes;
import java.awt.Color;

public class Game {
   
   public enigma.console.Console cn=Enigma.getConsole("Snake",85,30,25,3);
   public TextMouseListener tmlis;
   public KeyListener klis; 

   // ------ Standard variables for mouse and keyboard ------
   public int mousepr;          // mouse pressed?
   public int mousex, mousey;   // mouse text coords.
   public int keypr;   // key pressed?
   public int rkey;    // key   (for press/release)
   // ----------------------------------------------------
   boolean playFlag;
   boolean tutorialFlag;
   boolean exitFlag;
   boolean openingFlag;
   boolean scoreFlag;
   boolean menuFlag;
   boolean flag;
   SnakeGame game=new SnakeGame();
   Key keyboard =new Key();

	Score score = game.getNewScore();
   Game() throws Exception {   // --- Contructor
	   
                 
      // ------ Standard code for mouse and keyboard ------ Do not change
      tmlis=new TextMouseListener() {
         public void mouseClicked(TextMouseEvent arg0) {}
         public void mousePressed(TextMouseEvent arg0) {
            if(mousepr==0) {
               mousepr=1;
               mousex=arg0.getX();
               mousey=arg0.getY();
            }
         }
         public void mouseReleased(TextMouseEvent arg0) {}
      };
      cn.getTextWindow().addTextMouseListener(tmlis);
      playFlag=false;tutorialFlag=false;exitFlag=false;openingFlag=true;scoreFlag=false;menuFlag=false;
     
   }
  
   	public void menu() throws InterruptedException, IOException {
   	  playFlag=false;tutorialFlag=false;exitFlag=false;openingFlag=true;scoreFlag=false;menuFlag=false;
   	  mousepr=0;
      game=new SnakeGame();
   	  game.init();
      clear();
      opening();
      int px=5,py=5;
      //flag=true;
      while(true) {
         if(mousepr==1) {  // if mouse button pressed
            //cn.getTextWindow().output(mousex,mousey,'#');  // write a char to x,y position without changing cursor position
            px=mousex; py=mousey;
            //System.out.println(px+ ","+py);
            if((px>=2 && px<=81) && (py>=16 && py<=23) && openingFlag) {
            	clear();
            	menuInterface();
            	scoreFlag=true;
            	playFlag=true;
            	exitFlag=true;
            	tutorialFlag=true;
            	openingFlag=false;
            }
            else if((px>=57 && px<=64) && (py==2) && menuFlag && !scoreFlag) {
            	scoreFlag=true;
            	playFlag=true;
            	exitFlag=true;
            	tutorialFlag=true;
            	openingFlag=false;
            	clear();
            	menuInterface();
            	
            }
            else if((px>=28 && px<=56) && (py>=11 && py<=14) && scoreFlag && !openingFlag) {
            	playFlag=false;
            	menuFlag=true;
            	scoreFlag=false;
            	clear();
            	//score.allTimeHighScores();
            	game.getNewScore().getHighscores().display();
            	//score.getHighscores().display();
            	
            }
            else if((px>=16 && px<=23) && (py==2 ) && playFlag ) {
            	playFlag=false;
            	exitFlag=false;
            	tutorialFlag=false;
            	openingFlag=false;
            	scoreFlag=false;
            	menuFlag=false;
            	clear();
            	
            }
            else if((px>=32 && px<=48) && (py==26) && menuFlag) {
            	clear();
            	menuInterface();
            	playFlag=true;
            	exitFlag=true;
            	tutorialFlag=true;
            	scoreFlag=true;
            	menuFlag=false;
            }
            else if((px>=32 && px<=53) && (py>=4 && py<=9) && playFlag && !openingFlag) {
            	playFlag=false;
            	exitFlag=false;
            	tutorialFlag=false;
            	openingFlag=false;
            	scoreFlag=false;
            	menuFlag=false;
            	clear();
            	Timer myTimer = new Timer();
            	TimerTask gorev = new TimerTask(){
      		 
      		  		@SuppressWarnings("static-access")
					@Override
      		  		public void run(){
      		  			
      		  			if(game.getNewScore().getCounter()==3)
      		  				game.getNewScore().givePoint(game.snake);
      					//key listener is added
      					cn.getTextWindow().addKeyListener(keyboard);
      					game.getMove(game.rkey);
      					
      					//controlling game over or not ?
      					if(game.gameOver(game.snake,game.maps)) {
      						cn.getTextWindow().setCursorPosition(0, 26);
      						System.out.println("Game over!");
      						//HighScores methods.
      						game.getNewScore().addHighScores(game.getNewScore().getScore());
      						game.getNewScore().getHighscores().display();
      						myTimer.cancel();
      						try {
								menu();
							} catch (InterruptedException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
      						//return;
      						//unused input variable.
      						//@SuppressWarnings("unused")
      						//String input=sc.nextLine();
      						//exit game.
      		  				//System.exit(1);
      					}
      					else {
      						//game methods.
      						game.grow(game.snake,game.maps);
      						game.move(game.snake,game.maps);
      						game.displayMap(cn);
      						game.getNewScore().printingCodons(cn);
      						game.generateWall(game.maps);
      						
      						//determining game level
      						game.setCounterOfTime(game.getCounterOfTime()+1);
      						if(game.getCounterOfTime()==1000/Main.t) {
      							game.setTime(game.getTime()+1);
      							game.setCounterOfTime(0);
      						}
      					}
      		  			
      		  		}
      		  };
      		  
      		  //playing game per t.
      		  myTimer.schedule(gorev,0,Main.t); //  10000ms = 1sn
      		 	
            }
            else if((px>=24 && px<=59) && (py>=16 && py<=20) && tutorialFlag && !openingFlag) {
            	playFlag=false;
            	exitFlag=false;
            	tutorialFlag=false;
            	menuFlag=true;
            	openingFlag=false;
            	scoreFlag=false;
            	clear();
            	tutorial();
            }
            else if((px>=32 && px<=49) && (py>=23 && py<=26) && exitFlag && !openingFlag) {
            	System.exit(1);
            }
            
            mousepr=0;     // last action  
         }
         
         Thread.sleep(40);
      }
     
   }
   
   public void opening() {
	   
	   cn.setTextAttributes(new TextAttributes(new Color(0,255,0),new Color(0,0,0)));
	   System.out.println("                                  ---_ ......._-_--.");
	   System.out.println("                                 (|\\ /      / /| \\  \\");
	   System.out.println("                                 /  /     .'  -=-'   `.");
	   System.out.println("                                /  /    .'             )");
	   System.out.println("                              _/  /   .'        _.)   /");
	   System.out.println("                             / o   o        _.-' /  .'");
	   System.out.println("                             \\          _.-'    / .'*|");
	   System.out.println("                              \\______.-'//    .'.' \\*|");
	   System.out.println("                               \\|  \\ | //   .'.' _ |*|");
	   System.out.println("                                `   \\|//  .'.'_ _ _|*|");
	   System.out.println("                                 .  .// .'.' | _ _ \\*|");
	   System.out.println("                                 \\`-|\\_/ /    \\ _ _ \\*\\");
	   System.out.println("                                  `/'\\__/      \\ _ _ \\*\\");
	   System.out.println("                                 /^|            \\ _ _ \\*");
	   System.out.println("                                '  `            _\\_");
	   System.out.println();
	   System.out.println("                            _                     _                _ ");
	   System.out.println("                           | |                   | |              | |");
	   System.out.println("   _ __  _ __ ___ ___ ___  | |__   ___ _ __ ___  | |_ ___    _ __ | | __ _ _   _");
	   System.out.println("  | '_ \\| '__/ _ / __/ __| | '_ \\ / _ | '__/ _ \\ | __/ _ \\  | '_ \\| |/ _` | | | |");
	   System.out.println("  | |_) | | |  __\\__ \\__ \\ | | | |  __| | |  __/ | || (_) | | |_) | | (_| | |_| |");
	   System.out.println("  | .__/|_|  \\___|___|___/ |_| |_|\\___|_|  \\___|  \\__\\___/  | .__/|_|\\__,_|\\__, |");
	   System.out.println("  | |                                                       | |             __/ |");
	   System.out.println("  |_|                                                       |_|            |___/ ");
	   
	                                            
	  
   }
   public void menuInterface() {
	   //System.out.println();
	   System.out.println("                                              _");
	   System.out.println("                              ___ _ __   __ _| | _____");
	   System.out.println("                             / __| '_ \\ / _` | |/ / _ \\");
	   System.out.println("                             \\__ \\ | | | (_| |   <  __/");
	   System.out.println("                 ____________|___/_| |_|\\__,_|_|\\_\\___|____________");
	   System.out.println("                |                      _                           |");
	   System.out.println("                |                _ __ | | __ _ _   _               |");
	   System.out.println("                |               | '_ \\| |/ _` | | | |              |");
	   System.out.println("                |               | |_) | | (_| | |_| |              |");
	   System.out.println("                |               | .__/|_|\\__,_|\\__, |              |");
	   System.out.println("                |               |_|            |___/               |");
	   System.out.println("                |                                                  |");
	   System.out.println("                |            ___  ___ ___  _ __ ___ ___            |");
	   System.out.println("                |           / __|/ __/ _ \\| '__/ _ / __|           |");
	   System.out.println("                |           \\__ | (_| (_) | | |  __\\__ \\           |");
	   System.out.println("                |           |___/\\___\\___/|_|  \\___|___/           |");
	   System.out.println("                |                                                  |");
	   System.out.println("                |        _         _             _       _         |");
	   System.out.println("                |       | |_ _   _| |_ ___  _ __(_) __ _| |        |");
	   System.out.println("                |       | __| | | | __/ _ \\| '__| |/ _` | |        |");
	   System.out.println("                |       | |_| |_| | || (_) | |  | | (_| | |        |");
	   System.out.println("                |        \\__|\\__,_|\\__\\___/|_|  |_|\\__,_|_|        |");
	   System.out.println("                |                                                  |");
	   System.out.println("                |                          _ _                     |");
	   System.out.println("                |                 _____  _(_| |_                   |");
	   System.out.println("                |                / _ \\ \\/ | | __|                  |");
	   System.out.println("                |               |  __/>  <| | |_                   |");
	   System.out.println("                |                \\___/_/\\_|_|\\__|                  |");
	   System.out.println("                |__________________________________________________|                                              ");
   }
   public void gameOver() {
	   System.out.println("                                              _");
	   System.out.println("                   __ _  __ _ _ __ ___   ___    _____   _____ _ __          ");
	   System.out.println("                  / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ | '__|");
	   System.out.println("                 | (_| | (_| | | | | | |  __/ | (_) \\ V |  __| |");
	   System.out.println("   | PLAY |      \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|      | EXIT |");
	   System.out.println("                 |___/");
	   System.out.println("                ___________________________________________________");
	   System.out.println("               |                      _                            |");
	   System.out.println("               |                _ __ | | __ _ _   _                |");
	   System.out.println("               |               | '_ \\| |/ _` | | | |              |");
	   System.out.println("               |               | |_) | | (_| | |_| |               |");
	   System.out.println("               |               | .__/|_|\\__,_|\\__, |               |");
	   System.out.println("               |               |_|            |___/                |");
	   System.out.println("               |                                                   |");
	   System.out.println("               |            ___  ___ ___  _ __ ___ ___             |");
	   System.out.println("               |           / __|/ __/ _ \\| '__/ _ / __|            |");
	   System.out.println("               |           \\__ | (_| (_) | | |  __\\__ \\            |");
	   System.out.println("               |           |___/\\___\\___/|_|  \\___|___/            |");
	   System.out.println("               |                                                   |");
	   System.out.println("               |        _         _             _       _          |");
	   System.out.println("               |       | |_ _   _| |_ ___  _ __(_) __ _| |         |");
	   System.out.println("               |       | __| | | | __/ _ \\| '__| |/ _` | |         |");
	   System.out.println("               |       | |_| |_| | || (_) | |  | | (_| | |         |");
	   System.out.println("               |        \\__|\\__,_|\\__\\___/|_|  |_|\\__,_|_|         |");
	   System.out.println("               |                                                   |");
	   System.out.println("               |                          _ _                      |");
	   System.out.println("               |                 _____  _(_| |_                    |");
	   System.out.println("               |                / _ \\ \\/ | | __|                   |");
	   System.out.println("               |               |  __/>  <| | |_                    |");
	   System.out.println("               |                \\___/_/\\_|_|\\__|                   |");
	   System.out.println("               |___________________________________________________|");
   }
   public void tutorial() {
	   System.out.println("                        _         _             _       _         ");
	   System.out.println("                       | |_ _   _| |_ ___  _ __(_) __ _| |        ");
	   System.out.println("                       | __| | | | __/ _ \\| '__| |/ _` | |        ");
	   System.out.println("                       | |_| |_| | || (_) | |  | | (_| | |        ");
	   System.out.println("    ____________________\\__|\\__,_|\\__\\___/|_|  |_|\\__,_|_|  ___________________");
	   System.out.println("   |                                                                           |");
	   System.out.println("   |        The game is played controlling a moving snake in 25*60 area.       |");
	   System.out.println("   |  There are four letters which are A (Adenine), C (Cytosine), G (Guanine)  |");
	   System.out.println("   |  ,T (Thymine) in the game area. The snake starts with 3 letters assigned  |");
	   System.out.println("   |  randomly out of four letter(A,C,G,T).When the snake eats these letters,  |");
	   System.out.println("   |  it becomes longer. If the snake bumps into a wall or its own body the    |");
	   System.out.println("   |  game is over.                                                            |");
	   System.out.println("   |                		           RULES                                      |");
	   System.out.println("   |        	   # The snake starts with 3 letters assigned randomly            |");
	   System.out.println("   |       # There will be 3 letters randomly generated in the game area       |");
	   System.out.println("   |     at the start. When the snake eats a letter, a new letter must be      |");
	   System.out.println("   |     generated in the game area to maintain starting number of letters.    |");
	   System.out.println("   |         # The snake will be moved by the player using arrow keys.         |");
	   System.out.println("   |      # When the snake eats a letter, the letter will be added to the      |");
	   System.out.println("   |    front (head) of the snake.                                             |");
	   System.out.println("   |        # When the snake eats a letter, the player earns 5 points.         |");
	   System.out.println("   |   The player will also earn extra points when the snake completes an      |");
	   System.out.println("   |   amino-acid codon                                                        |");
	   System.out.println("   |      # “#” character shows the wall, if the snake bumps into a wall       |");
	   System.out.println("   |   or its own body the game will be over.                                  |");
	   System.out.println("   |                                                                           |");
	   System.out.println("   |                            | BACK TO MENU |                               |");
	   System.out.println("   |___________________________________________________________________________|");
   }
//   public void scores() throws IOException {
//	   score.allTimeHighScores();
//   }
   public void clear() {
	   for (int i = 0; i < 100; i++) {
		   System.out.println();
	}
	   cn.getTextWindow().setCursorPosition(0, 0);
   }
}
