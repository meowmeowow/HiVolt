
public class Enemy extends Cell{




	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String draw() {
		//draws itself
		return("Ω");
	}
	
	@Override
	public void move(Board b) { 
			//does the logic for moving
			// checks if it dies	
			
			//get players location
			if(b.player == null) {
				return;
			}
			int playerX = b.player.x;
			int playerY = b.player.y;
			
			int dx = playerX-x;
			int dy = playerY - y;
			
			int pastX = x;
			int pastY = y;
			//select location to move to based on players location
			
			b.set(x,y,null);
			
			if (dx == dy) {
				//Diagonal 
				if (dx > 0)
				{
					x++;
					if (dy > 0)
					{
						y++;
					}
					else
					{
						y--;
					}
				}
				else
				{
					x--;
					if (dy > 0)
					{
						y++;
					}
					else
					{
						y--;
					}
					}
				}
				
			else if(dy == 0) {
				//perfectly horizantal
				if (dx > 0)
				{
					x++;
				}
				else
				{
					x--;
				}
			}
			
			else if(dx == 0) {
				//perfectly vertical
				if (dy > 0)
				{
					y++;
				}
				else
				{
					y--;
				}				
			}
			
			else if(Math.abs(dx) > Math.abs(dy)) { 
				//move towards the player horizantally
				if (dx > 0)
				{
					x++;
				}
				else
				{
					x--;
				}
			}
			else if(Math.abs(dy) > Math.abs(dx)) { 
				//move towards the player vertically
				if (dy > 0)
				{
					y++;
				}
				else
				{
					y--;
				}
			}
			//checks what is in the location to move too
			//then based on the certain values of what in the location, either die, move, or stay put 
			if(b.get(x,y) == null) {
				b.set(x,y,this);
			}
			else if(b.get(x, y).getClass() == Fence.class) {
				//died
			}
			else if (b.get(x, y).getClass() == Enemy.class) {
				//stay put
				x = pastX;
				y = pastY;
				b.set(x,y,this);
				
			}
			else if (b.get(x, y).getClass() == Player.class) {
				//move to comsume
				b.player = null;
				b.set(x,y,this);
			}
		
			
			}
		}
