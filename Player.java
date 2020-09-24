public class Player extends Cell{
	public boolean alive;
	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub


	}
	@Override
	public String draw() {
		//draws itself
		return("😃");
	}
	public boolean movePlayer(Board b, char keypressed) { 
			//get input from keyboard
			//does the logic for moving
			boolean moveRobots = true;
			//Scanner myObj = new Scanner(System.in); // change this 
			
//			System.out.print("Enter Character: ");
//			String input = myObj.nextLine();
//			char keypressed = input.charAt(0);
//			
			
			
			b.set(x,y,null);
			
			if (keypressed == 'q' || keypressed == 'w' || keypressed == 'e') y--;
			if (keypressed == 'z' || keypressed == 'x' || keypressed == 'c') y++;
			if (keypressed == 'q' || keypressed == 'a' || keypressed == 'z') x--;
			if (keypressed == 'e' || keypressed == 'd' || keypressed == 'c') x++;
			//if (keypressed == 'S')  stays the same;
			if (keypressed == 'j') {
				 x = (int)(Math.random() * (b.width - 2 + 1) + 1);
				 y = (int)(Math.random() * (b.height - 2 + 1) + 1);		
				 moveRobots = false;
				
			}
			
			//check if die from movement;

			if(b.get(x,y) != null && (b.get(x,y).getClass() == Enemy.class || b.get(x,y).getClass() == Fence.class)) {
				b.player = null;
			}
			else {
				b.set(x,y,this);
			}
			return(moveRobots);
			
		 
	}

}
