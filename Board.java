import java.util.Random;
import java.util.ArrayList;
public class Board {
	
	Player player = null;
	public Cell[][] gameBoard;
	Random rand;
	
	public int width;
	public int height;
	public int totalInnerFences;
	public int totalEnemies;
	
	public Board(int width, int height){
		this.width = width;
		this.height = height;
		
		this.totalInnerFences = 20;
		this.totalEnemies = 12;
		this.rand = new Random(); 
		//initate board with width and height with Cells
		//assign Cells to be Fence Enemy or Player 
		
		this.gameBoard =  new Cell[width][height];

		
		//set walls on the edges
		assert(width == height);
		for(int i = 0; i < height; i++){ //hypoceticlly add another for loop for width too 
			//this currently makes a double instance 
			set(i,0,new Fence(i,0));
			set(i,height-1, new Fence(i,height-1));
			
			gameBoard[0][i] = new Fence(0,i);
			gameBoard[width-1][i] = new Fence(width-1,i);
		}
		//set player
		// random => int random_int = (int)(Math.random() * (max - min + 1) + min);
		int playerx = (int)(Math.random() * (width-2) + 1);
		int playery = (int)(Math.random() * (height-2) + 1);
		
		this.player = new Player(playerx,playery);
		set(playerx, playery, this.player); 
		
		
		//set fences
		for (int i = 0; i < totalInnerFences; i++){
			while(true) {
				int fencex = (int)(Math.random() * (width-2) + 1);
				int fencey = (int)(Math.random() * (height-2) + 1);
				
				if(get(fencex,fencey) == null) {
					set(fencex, fencey, new Fence(fencex,fencey));
					break;
				}
			}
		}
		
		
		//set enemies
		for (int i = 0; i < totalEnemies; i++){
			while(true) {
				int enemyx = (int)(Math.random() * (width - 2 + 1) + 1);
				int enemyy = (int)(Math.random() * (height - 2 + 1) + 1);
				
				if(gameBoard[enemyx][enemyy] == null) {
					gameBoard[enemyx][enemyy] = new Enemy(enemyx,enemyy);
					break;
				}
			}
			
		}
	}
	public ArrayList<Enemy> getEnemyList() {
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				if (get(i,j) != null && get(i,j).getClass() == Enemy.class) {
				    enemyList.add((Enemy) get(i,j));
	
				}
			}
		}
		return(enemyList);
	}
	
	public void turn() {
		//loop through the board: call move method on every cell with "phase"
		ArrayList<Enemy> enemyList = getEnemyList();
	    for (int i = 0; i < enemyList.size(); i++) {
	        enemyList.get(i).move(this);
	        
	      }
	}
	
	public void set(int x,int y, Cell value) {
		//set value of xy to Cell"value"
		gameBoard[x][y] = value;
	}
	
	public Cell get(int x,int y) {
		// returns value of cell
		return gameBoard[x][y];
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	 
}

