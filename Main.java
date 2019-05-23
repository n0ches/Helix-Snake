


public class Main {
	
	//changeable timer clock.
	static int tSlow=120;
	static int tNormal=90;
	static int tFast=60;
	
	static int tSlowAdvanced=90;
	static int tNormalAdvanced=70;
	static int tFastAdvanced=50;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		/*char wall=(int)255;
		System.out.println(wall);*/
		
		
		//new SnakeGame object.
		Game newgame = new Game();
		do{
			newgame.menu();
		}while(newgame.playAgain);
		
		
		
				
	}
	
}
