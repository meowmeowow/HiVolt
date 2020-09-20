
public class Main {
	static int turn;
	
	public static void playGame() {
		turn = 0;
		Board b = new Board(10,10);
		

		Display d = new Display(b.width,b.height);
		
		d.displayBoard(b);
		d.displayConsole(b);
		while(b.player != null) {
			turn++;
			boolean moveRobots = b.player.movePlayer(b);			
			
			if (b.player == null) {
				break;
			}
			
			if (moveRobots == true) {
				b.turn();
			}
			
			d.displayBoard(b);
			d.displayConsole(b);
			//check if all robots are dead
			
			if (b.getEnemyList().size() == 0) {
				break;
			}
		}
		d.displayConsole(b);

		System.out.println("Game Over!");
		d.gameOver();
		if(b.player == null) {
			System.out.println("You lost :(");
		}
		else {
			System.out.println("You Win :)");
		}
		System.out.println("You made " + turn + " turn(s)!");
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//if player is null, end game
		
		// in a while loop
		System.out.println("Welcome to HiVolts!!!!");
		System.out.println("Would you like to play: "); // get input
		playGame();
		System.out.println("Would you like to play again: ");// get input


}
}
