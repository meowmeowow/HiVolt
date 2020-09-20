import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display {
	int height;
	int width;
	JFrame frame;
	JPanel panel;
	//ArrayList<Enemy> enemyList = getEnemyList();
	//ArrayList<JPanel> panelList;
	
	
	public Display(int height,int width) {
		this.height = height;
		this.width = width;
		this.frame = new JFrame("Test");
		this.panel = new JPanel(new GridLayout(height, width));
		frame.setContentPane(panel);
		
		for (int j = 0; j < height; j++){

			for (int i = 0; i < width; i++){
				JLabel label = new JLabel();
				panel.add(label);
				//panelList.add(label);
			}
		}	

	}
	
	public void displayBoard(Board b) {
		//loop through panel
		//get label
		//update label to be b[i][j] draw or something
		int loopthrough = 0;
		int cellX = 0;
		int cellY = 0;
		for (Component c : panel.getComponents()) {
			
			if(b.gameBoard[cellY][cellX] == null ) {
				((JLabel) c).setText(" ");	
			}
			
			else if(b.get(cellY, cellX).getClass() == Player.class) {
				((JLabel) c).setText(b.gameBoard[cellY][cellX].draw());	
			}
			else if(b.get(cellY, cellX).getClass() == Fence.class) {
				((JLabel) c).setText(b.gameBoard[cellY][cellX].draw());	
			}
			else if (b.get(cellY, cellX).getClass() == Enemy.class) {
				((JLabel) c).setText(b.gameBoard[cellY][cellX].draw());	
			}
			loopthrough = loopthrough +1;
			cellY = cellY +1;
			
			if(loopthrough % width == 0) {
				cellX = cellX +1;
				cellY = 0;
			}
		}
		frame.setVisible(true);
		
		
	}
	
	public void displayConsole(Board b) {
		for (int j = 0; j < height; j++){

			for (int i = 0; i < width; i++){
				if (b.get(i,j) == null) {
					System.out.print(" ");
				}
				else {
					System.out.print(b.gameBoard[i][j].draw());
				}
				System.out.print(" ");
				
			}
			System.out.println();
		}
		
	}
	
	public void gameOver() {
		//if the game is over then...
		frame.setVisible(false); 
		frame.dispose();
	}
}
