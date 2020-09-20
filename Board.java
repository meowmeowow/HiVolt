package HiVolt2;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Board extends JFrame implements KeyListener{
	Random r= new Random();
	JFrame f = new JFrame();
	Mho Mhos[];
	Player p1;
	Fence fence[];
	JLabel l;
	int arr[][] = new int[12][12];
	public JButton arr2[][];
	boolean gameover = false;
	public Board()
	{
		arr2 = new JButton[12][12];
		f.setSize(800, 800);
		f.setLayout(new GridLayout(12,12));
		f.setResizable(false);
		f.setTitle("Moves: 0");
		for (int i =0;i<12; i++)
		{
			for (int ii =0; ii <12; ii++)
			{
				arr2[i][ii] = new JButton();
				//arr2[i][ii].setBackground(new Color(0,0,0));
				arr2[i][ii].setBorderPainted(true);
				arr2[i][ii].setOpaque(true);
				f.add(arr2[i][ii]);
			}
		}
		Mhos = new Mho[12];
		fence = new Fence[20];
		for (int i =0; i < 20; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				arr[y+1][x+1] = 1;
				fence[i] = new Fence(x,y);
				ImageIcon imageIcon = new ImageIcon("D:/Fence.jpg");
				Image image = imageIcon.getImage();
				Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image2));
			}
		}
		for (int i = 0; i < 12; i++)
		{
			arr[0][i] = 1;
			arr[i][0] = 1;
			arr[11][i] = 1;
			arr[i][11] = 1;
			ImageIcon imageIcon = new ImageIcon("D:/Fence.jpg");
			Image image = imageIcon.getImage();
			Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
			arr2[0][i].setIcon(new ImageIcon(image2));
			arr2[i][0].setIcon(new ImageIcon(image2));
			arr2[11][i].setIcon(new ImageIcon(image2));
			arr2[i][11].setIcon(new ImageIcon(image2));
			
		}
		for (int i =0; i < 12; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				arr[y+1][x+1] = 2;
				Mhos[i] = new Mho(x,y);
				ImageIcon imageIcon2 = new ImageIcon("D:/Mho.png");
				Image image23 = imageIcon2.getImage();
				Image image22 = image23.getScaledInstance(66, 66,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image22));
			}
		}
		for (int i =0; i < 1; i++)
		{
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			if (arr[y+1][x+1] != 0)
			{
				i--;
				continue;
			}
			else
			{
				p1= new Player(x,y);
				ImageIcon imageIcon = new ImageIcon("D:/Player.jpg");
				Image image = imageIcon.getImage();
				Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
				arr2[y+1][x+1].setIcon(new ImageIcon(image2));
			}
		}
		f.setVisible(true);
	}
	public boolean check(int x, int y, int value)
	{
		return arr[y+1][x+1] == value;
	}
	public boolean deadP(int x, int y)
	{
		if (arr[y][x] == 0)
			return false;
		return true;
	}
	public boolean deadM(int x, int y)
	{
		if (arr[y][x] == 0)
			return false;
		return true;
	}
	public void process(char keyPressed)
	{
		arr2[p1.y+1][p1.x+1].setIcon(new ImageIcon());
		p1.move(keyPressed);
		if (deadP(p1.x+1,p1.y+1))
		{
			//gameover();
			System.out.println("UR");
			return;
		}
		f.setTitle("Moves: " + p1.moves);
		ImageIcon imageIcon = new ImageIcon("D:/Player.jpg");
		Image image = imageIcon.getImage();
		Image image2 = image.getScaledInstance(59, 59,  java.awt.Image.SCALE_SMOOTH);
		arr2[p1.y+1][p1.x+1].setIcon(new ImageIcon(image2));
		f.setVisible(true);
		boolean alldead = true;
		for (int i =0; i < 12; i++)
		{
			if (Mhos[i].alive && keyPressed != 'J')
			{
				alldead= false;
				arr[Mhos[i].y+1][Mhos[i].x+1] = 0;
				arr2[Mhos[i].y+1][Mhos[i].x+1].setIcon(new ImageIcon());
				Mhos[i].moves(p1.x, p1.y, arr);
				if (deadM(Mhos[i].x+1,Mhos[i].y+1))
				{
					Mhos[i].alive = false;
					continue;
				}
				ImageIcon imageIcon2 = new ImageIcon("D:/Mho.png");
				Image image23 = imageIcon2.getImage();
				Image image22 = image23.getScaledInstance(66, 66,  java.awt.Image.SCALE_SMOOTH);
				arr2[Mhos[i].y+1][Mhos[i].x+1].setIcon(new ImageIcon(image22));
				arr[Mhos[i].y+1][Mhos[i].x+1] = 2;
			}
		}
		if (alldead || p1.moves >= 12)
		{
			System.out.println(p1.moves);
		}
		f.setVisible(true);
		if (deadP(p1.x+1,p1.y+1))
		{
			System.out.println("DEAD");
			//gameover();
			return;
		}
	}
	public void gameover()
	{
		gameover= true;
	    add(l,BorderLayout.CENTER);
	    ImageIcon img = new ImageIcon("gameover.jpg");
	    l.setIcon(img);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		String str = "";
		str += e.getKeyChar();
		str.toUpperCase();
		process(str.charAt(0));
	}
	@Override
	public void keyReleased(KeyEvent e) {}
}
