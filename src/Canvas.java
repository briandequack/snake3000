import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Canvas extends JPanel{


	private Grid grid = Grid.getInstance();
	static Canvas instance = new Canvas();
	final int PANEL_WIDTH = 420;
	final int PNALE_HEIGHT = 420;
	Timer timer;
	TimerTask task;
	int x = 100;
	int y = 100;

	Square square = new Square(42,42);
	
	private Canvas(){
		
		this.setBackground(Color.GREEN);
		this.setBounds(0,0,420,420);
		this.setLayout(null);
		this.setOpaque(true);
		
		
	}
	
	public static Canvas getInstance() {
		return instance;
	}
}