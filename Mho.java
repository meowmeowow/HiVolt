package HiVolt2;
import java.util.*;
import java.lang.*;
public class Mho {
	int x;
	int y;
	boolean alive = true;
	public Mho(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public boolean check(int x, int y, int value, int arr[][])
	{
		return arr[y+1][x+1] == value;
	}
	public void moves(int x1, int y1, int arr[][])
	{
		for (int i = 0; i <2; i++)
		{
			int dx = x1-x;
			int dy = y1-y;
			int tempx = x;
			int tempy = y;
			double slope = (double) (double)dy/(double)dx;
			if (Math.abs(dx) == Math.abs(dy))
			{
				if (dx > 0)
				{
					tempx++;
					if (dy > 0)
					{
						tempy++;
					}
					else
					{
						tempy--;
					}
				}
				else
				{
					tempx--;
					if (dy > 0)
					{
						tempy++;
					}
					else
					{
						tempy--;
					}
				}
				if (check(tempx,tempy,i,arr))
				{
					x = tempx;
					y = tempy;
					break;
				}
			} 
			else if (Math.abs(dx) > Math.abs(dy))
			{
				if (dx > 0)
				{
					tempx++;
				}
				else
				{
					tempx--;
				}
				if (check(tempx,tempy,i,arr))
				{
					x = tempx;
					y = tempy;
					break;
				}
			}
			else if (Math.abs(dx) < Math.abs(dy))
			{
				if (dy > 0)
				{
					tempy++;
				}
				else
				{
					tempy--;
				}
				if (check(tempx,tempy,i,arr))
				{
					x = tempx;
					y = tempy;
					break;
				}
			}
		}
	}
}
