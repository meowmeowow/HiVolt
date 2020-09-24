import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Display implements KeyListener, ActionListener{
	int height;
	int width;
	JPanel panel;
	JPanel endpanel;
	JPanel startpanel;
	//ArrayList<Enemy> enemyList = getEnemyList();
	//ArrayList<JPanel> panelList;
	//JFrame startframe;
	JFrame frame;
	//JFrame endframe;
	
	final Condition notPressed;
	final Lock lock;
	char key;
	
	final Condition notPressedStart;
	final Lock lockStart;
	boolean start;
	
	JLabel jlabel_end;

	
	
	public Display(int height,int width) {
		this.height = height;
		this.width = width;
		
		
		this.frame = new JFrame("Game");
		
		this.panel = new JPanel(new GridLayout(height, width));
		frame.setContentPane(panel);
		frame.setSize(400, 400);
		
		this.lock = new ReentrantLock();
		this.notPressed  = lock.newCondition(); 
		this.key = ' ';
		
		this.lockStart = new ReentrantLock();
		this.notPressedStart  = lockStart.newCondition(); 
		this.start = false;

		this.setup_startScreen();
		this.frame.addKeyListener(this);  

		
		jlabel_end = new JLabel("");
		
		this.setup_gameOver();
		
		
		for (int j = 0; j < height; j++){
			for (int i = 0; i < width; i++){
				JLabel label = new JLabel();
				panel.add(label);
			}
		}	
		this.frame.setVisible(true);
		this.frame.setFocusable(true);


	}
	
	public void displayBoard(Board b,int turn) {
		//loop through panel
		//get label
		//update label to be b[i][j] draw or something
		
		startpanel.setVisible(false); 
		
		int loopthrough = 0;
		int cellX = 0;
		int cellY = 0;
		for (Component c : panel.getComponents()) {
			JLabel label = (JLabel) c;
			
			if(b.get(cellX, cellY) == null ) {
				label.setText(" ");	
			} else {
				label.setText(b.get(cellX,cellY).draw());	
			}

			loopthrough = loopthrough +1;
			cellX = cellX +1;
			
			if(loopthrough % width == 0) {
				cellY = cellY +1;
				cellX = 0;
			}
			
			//label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		frame.setContentPane(panel);
		panel.setVisible(true); 

		//frame.setVisible(true);
		frame.setTitle("Turns: "+ turn +", Mhos killed: " + (b.totalEnemies-b.getEnemyList().size())+"/"+b.totalEnemies); // add timer
		
		
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
	
	public void gameOver(boolean win, int turn) {
		//if the game is over then...
		panel.setVisible(false); 
		//frame.dispose();
			    
	    if (win == false) {
	    	jlabel_end.setText("You lost in "+ turn + " turn(s)!");
	    }else {
	    	jlabel_end.setText("You won in "+ turn + " turn(s)!");
	    }
	    
	    //endframe.setSize(400, 400);
	    frame.setContentPane(endpanel);
	    endpanel.setVisible(true); 
		
	}
	
	
	public void setup_gameOver() {
		this.endpanel = new JPanel();

	    JLabel jlabel = new JLabel("Game Over!");
	    jlabel.setFont(new Font("Verdana",1,40));
	    endpanel.add(jlabel);
	    
	    //jlabel = new JLabel("");
	    endpanel.add(jlabel_end);
	    
	    
//	    JButton jlabel=new JButton("Start Game!");
//	    jlabel.setFont(new Font("Verdana",1,40));
//	    startpanel.add(jlabel);
//	    jlabel.addActionListener(this);
	    
	    JButton tryAgain =new JButton("Click to play Again!");
	    tryAgain.setFont(new Font("Verdana",1,30));
	    endpanel.add(tryAgain);
	    tryAgain.addActionListener(this);
	    
	    endpanel.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
	    //endframe.setSize(400, 400);
		
	}
	
	public void startScreen(){
		endpanel.setVisible(false); 
	    frame.setContentPane(startpanel);
	    startpanel.setVisible(true); 
	    //frame.setVisible(true);
	    
	}	
	
	public void setup_startScreen(){
		this.startpanel = new JPanel();

	    JButton jlabel=new JButton("Start Game!");
	    jlabel.setFont(new Font("Verdana",1,40));
	    startpanel.add(jlabel);
	    jlabel.addActionListener(this);
	    	    
	    startpanel.setBorder((Border) new LineBorder(Color.BLACK)); // make it easy to see
	}	

	
	public char waitForKey() throws InterruptedException {
		char returnKey;
		lock.lock();
		try {
			while (key == ' ') notPressed.await();

			returnKey = key;
			key = ' ';
		} finally {
			lock.unlock();
		}	
		return(returnKey);
	}
	
	public boolean waitFor() throws InterruptedException{
		//boolean returnStart;
		lockStart.lock();
		
		try {
			while (start == false) notPressedStart.await();

			//eturnStart = start;
			start = false;
		} finally {
			lockStart.unlock();
		}	
		//System.out.println("clicked");
		return(true);
	}
	public void actionPerformed(ActionEvent e) {
//		this.lockStart = new ReentrantLock();
//		this.notPressedStart  = lock.newCondition(); 
		//System.out.println("clicked");
//		this.start = false;
		lockStart.lock();
		try {
			start = true;
			notPressedStart.signal();
		} finally {
			lockStart.unlock();
		}


	}
	
//	@Override
//	public void keyTyped(KeyEvent ke) {
//		System.out.println("You have typed "+ke.getKeyChar());
//	}
//	 
//	@Override
//	public void keyPressed(KeyEvent ke) {
//		System.out.println("You have pressed "+ke.getKeyChar());
//	}
	 
	@Override
	    public void keyReleased(KeyEvent ke) {
		System.out.println(("You have released "+ke.getKeyChar()));
		lock.lock();
		try {
			key = ke.getKeyChar();
			notPressed.signal();
		} finally {
			lock.unlock();
		}
	  }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
