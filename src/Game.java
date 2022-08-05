import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class Game extends JPanel{

	static Game instance = new Game();
	private LevelBuilder levelBuilder = LevelBuilder.getInstance();
	private Snake snake = Snake.getInstance();
	private Grid grid = Grid.getInstance();
	
	boolean playing = false;
	boolean timeFirstLoad = true;
	private Timer timer = null;
	private TimerTask task;
	public int numFoods = 1;
	
	Up up;
	Down down;
	Left left;
	Right right;
	
	private Game(){
		
		this.setBackground(Color.RED);
		this.setPreferredSize(new Dimension(420, 420));
		this.setLayout(null);
		this.setOpaque(true);
		
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
	
	public void start() {
		StartWindow startWindow = StartWindow.getInstance();
		
		this.levelBuilder.build((String)startWindow.cb.getSelectedItem());
	}

		
	public void play() {
		snake.move();	

		if(snake.eating == true) {		
			snake.grow();	
			snake.changeColor();
			levelBuilder.addFood();
			snake.eating = false;
			numFoods ++;
	
		} else if(snake.hit == true) {		
			this.playing = false;
			this.stopTimer();	
			Window window = Window.getInstance();

			RestartWindow restartWindow = RestartWindow.getInstance();
			restartWindow.score.setText(Integer.toString(this.numFoods-1));
			this.reset();
			window.switchWindow("Game","RestartWindow");
		}
				
		if(this.playing==true){		
			this.snake.paste();
		} 
		
		//grid.print();

	}
	
	public void startTimer() {
		if(this.timer == null) {
			this.timer = new Timer();
			this.task = new TimerTask() {
				public void run() {
					play();
				}
			};
			this.timer.scheduleAtFixedRate(task,0,150);	
		}
	
	}

	public void stopTimer() {
		this.timer.cancel();
		this.timer = null;
	}
	
	
	public static Game getInstance() {
		return instance;
	}
	

	public class Up extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			playing();
			snake.up();
			
		}
		
	}
	
	public class Down extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("down");
			playing();
			snake.down();
			
		}
		
	}
	
	public class Left extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			playing();
			snake.left();
			
		}
		
	}
	
	public class Right extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			playing();
			snake.right();
			
		}
		
	}
	
	
	public void playing() {
		if(!this.playing) {
			this.startTimer();
			this.playing = true;
		}
	}
	
	public void reset() {
		System.out.println("RESET GAME");
		this.playing = false;
		this.timer = null;
		this.task = null;
		this.numFoods = 1;

		this.snake.reset();
		this.grid.reset();
		this.removeAll();
		this.revalidate();
		this.repaint();
		
	}


}