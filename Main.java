

public class Main {
	static int turn;
	
	public static void playGame(Board b, Display d) throws InterruptedException {
		turn = 0;		
		d.displayBoard(b,turn);
		boolean win;
		//d.displayConsole(b);
		while(b.player != null) {
			turn++;
			char keypressed = d.waitForKey();
			boolean moveRobots = b.player.movePlayer(b,keypressed);			
			
			if (b.player == null) {
				break;
			}
			
			if (moveRobots == true) {
				b.turn();
			}
			
			d.displayBoard(b,turn);
			//d.displayConsole(b);
			//check if all robots are dead
			
			if (b.getEnemyList().size() == 0) {
				break;
			}
		}

		System.out.println("Game Over!");
		if(b.player == null) {
			System.out.println("You lost :(");
			//return ..
			win = false;
		}
		else {
			System.out.println("You Win :)");
			//return .
			win = true;
		}
		d.gameOver(win,turn);
		//System.out.println("You made " + turn + " turn(s)!");
		
		
	}
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Board b = new Board(10,10);
		Display d = new Display(b.width,b.height);
		
		while(true) {
	        //System.out.println("Welcome to HiVolts!!!!");
			// in a while loop
			//System.out.print("Enter coin: ");// make this a input on the screen
			d.startScreen();
			
			d.waitFor(); 
			//System.out.println("play");
			
			playGame(b,d);
			
			d.waitFor();
			
			b = new Board(10,10);
		}
}
}
