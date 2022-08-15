import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Game extends JPanel{

	// Get the global settings
	private Settings settings = Settings.get();
		
	static Game instance = new Game();
	private Grid grid;
	private Levels levels = Levels.get();
	private Snake snake = Snake.get();
	boolean playing = false;
	boolean timeFirstLoad = true;
	private Timer timer = null;
	private TimerTask task;
	private int score = 0;
	private String direction = ""; 
	private JLabel scoreDisplay;
	Up up;
	Down down;
	Left left;
	Right right;
	
	
	private Game(){
		
		// Create the game panel
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(settings.getWidth(), 
											settings.getHeight()));
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		
		// Add score display to top of frame
		scoreDisplay = new JLabel("0", JLabel.CENTER);
		scoreDisplay.setFont(new Font("Calibri", Font.BOLD, 24));
		scoreDisplay.setForeground(Color.WHITE);
		this.add(scoreDisplay, BorderLayout.PAGE_START);
		
		// Add controls
		up = new Up();
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up", up);
		down = new Down();
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		this.getActionMap().put("down", down);
		left = new Left();
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left", left);
		right = new Right();
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right", right);
			
	}
	
	
	public void load() {
		
		// Get the map from the start page
		String level = StartWindow.getInstance().level.getSelectedItem().toString();
		
		// Initialize the grid
		grid = Grid.get();
		grid.reset();
		
		// Connect all numbers in grid to a class
		grid.connectClass(8,"Wall");
		grid.connectClass(3,"Food");
		grid.connectClass(4,"Head");
		grid.connectClass(2,"Body");
		
		// Build the grid
		grid.build(levels.getLevel(level));
		
		// Set the score to zero
		scoreDisplay.setText(String.valueOf(this.score));
		
		// Set the current direction of the snake
		this.setDirection();
			
	}
	
	
	public void paintComponent(Graphics g) {
		  
		Graphics2D g2D = (Graphics2D) g;	
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paintComponent(g);
		
		// Draw all groups of the grid
		for (int key : grid.groups.keySet()) {
			
			for (int i = 0; i < grid.groups.get(key).size(); i++) {
				
				((BaseObj) grid.groups.get(key).get(i)).draw(g);
				
			}
		
		}	
		
	 }
	
	
	public void setDirection() {
		
		// Set the direction of the snake
		BaseObj head = (BaseObj) grid.getGroup("Head").get(0);
		
		// Check if the snake is vertical
		if(grid.getIndex(4) > grid.getIndex(2)) {
		
			Collections.reverse(grid.groups.get(2));
			BaseObj body = (BaseObj) grid.getGroup("Head").get(0);

			if(head.row != body.row) {

				this.direction = "down";
				
			} else {

				this.direction = "right";
				
			}
			
		} 
		
		// Else the snake is horizontal
		else {
			System.out.println("leftup");
			BaseObj body = (BaseObj) grid.getGroup("Body").get(0);
			
			if(head.row != body.row) {
				
				this.direction = "up";
				
			} else {
				
				this.direction = "left";
			}
			
		}	
		
		snake.direction = this.direction;
		
	}
	
	


		
	public void play() {
		
		snake.direction = this.direction;
		
		BaseObj head = (BaseObj) grid.getGroup("Head").get(0);
		
		// Get the cell class of the next position of the head
		String targetType = this.grid.getType(head.column + snake.vx,
											  head.row + snake.vy);
		
		// Get the cell signature of the next position of the head
		int targetSignature = this.grid.getSignature(head.column + snake.vx, 
													 head.row + snake.vy);
		
		
		// If the head get placed on food
		if(targetType.equals("Food")) {
			
			// Add tail segment
			snake.grow();	
			
			// change the color of the snake
			snake.changeColor();
			
			// Get the food object from the grid
			BaseObj food = (BaseObj) grid.getGroup("Food").get(0);
			
			// Get a random empty spot in the grid
			int[] randomCell = grid.getRandom();
			food.column = randomCell[1];
			food.row = randomCell[2];
			
			// Place the food again in a random spot
			food.color = RandomColor.getInstance().getNext();		
			grid.place(food);
			
			// Add to score and update the score board
			score++;
			scoreDisplay.setText(String.valueOf(this.score));
			
		// If the head hit the body or the wall
		} else if(targetType.equals("Wall") || targetType.equals("Body")) {
			
			// Get the tail object
			BaseObj tail = (BaseObj) grid.getGroup("Body").get(grid.getGroup("Body").size()-1);
			
			// Get the signature in the grid where the tail is
			int tailSignature = this.grid.getSignature(tail.column, tail.row);
			
			// Only stop the game when its not the last segment
			if(tailSignature!=targetSignature) {
				
				// Stop the timer
				this.playing = false;
				this.stopTimer();	
			
			}
	
		} 
		
		// Continue the game
		if(this.playing==true) {
			
			snake.move();		
			this.repaint();
			
		// End the game and continue to restart window
		} else {
			
			Window window = Window.getInstance();
			window.restartWindow.score.setText(String.valueOf(this.score));
			window.switchWindow("Game","RestartWindow");
			this.reset();
			snake.reset();
			grid.reset();
		}
	
	}
	
	
	public void startTimer() {
		
		if(this.timer == null) {
			
			// Only create a new timer when not exist
			this.timer = new Timer();
			this.task = new TimerTask() {
				
				public void run() {
					
					// Run play method
					play();
					
				}
			};
			// Set the take to run the play method at fixed rate
			this.timer.scheduleAtFixedRate(task,0,settings.getSpeed());	
		}
	
	}

	
	public void stopTimer() {
		
		// Empty the timer object
		this.timer.cancel();
		this.timer = null;
		
	}
	

	public class Up extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(snake.direction != "down")	{
				
				// Start the game if not yet started
				playing();
				
				// Change direction and velocity
				direction = "up";
				snake.vx = 0;
				snake.vy = -1;
				
			}
			
		}
		
	}
	
	
	public class Down extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(snake.direction != "up")	{
				
				// Start the game if not yet started
				playing();
				
				// Change direction and velocity
				direction = "down";
				snake.vx = 0;
				snake.vy = 1;
				
			}
			
		}
		
	}
	
	
	public class Left extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {	
	
			if(snake.direction != "right")	{
				
				// Start the game if not yet started
				playing();
						
				// Change direction and velocity
				direction = "left";
				snake.vx = -1;
				snake.vy = 0;
				
			}	
			
		}
		
	}
	
	
	public class Right extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(snake.direction != "left")	{
				
				// Start the game if not yet started
				playing();
				
				// Change direction and velocity
				direction = "right";
				snake.vx = 1;
				snake.vy = 0;
				
			}
		
		}
		
	}
	
	
	public void playing() {
		
		if(!this.playing) {
			
			// Start the game if it has not yet been started
			this.startTimer();
			this.playing = true;
			
		}
	}
	
	
	public static Game getInstance() {
		
		// Return instance of this class
		return instance;
		
	}
	
	
	public void reset() {
		
		// Reset variables
		this.playing = false;
		this.timer = null;
		this.task = null;
		this.score = 0;
		
	}

}