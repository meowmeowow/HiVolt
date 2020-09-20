package HiVolt2;
import java.util.*;
public class Player {
	Random r = new Random();
	int x;
	int y;
	int moves = 0;
	public Player(int x, int y)
	{
		this.x =x;
		this.y=y;
	}
	public void move(char keypressed)
	{
		moves ++;
		switch(keypressed)
		{
			case 'Q':
				x--;
				y--;
				break;
			case 'W':
				y--;
				break;
			case 'E':
				y--;
				x++;
				break;
			case 'A':
				x--;
				break;
			case 'S':
				// No change
				break;
			case 'D':
				x++;
				break;
			case 'Z':
				y++;
				x--;
				break;
			case 'X':
				y++;
				break;
			case 'C':
				y++;
				x++;
				break;
			case 'J':
				x = r.nextInt(10);
				y= r.nextInt(10);
				break;
			default:
				moves --;
				break;	
		}	
	}	
}
