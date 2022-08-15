import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Snake extends JPanel{
	
	private Grid grid = Grid.get();
	Settings settings = Settings.get();
	private RandomColor randomColor = RandomColor.getInstance();
	static Snake object = new Snake();
	int vx = 0;
	int vy = 0;
	String direction ="";
	

	private Snake(){
		
		this.setBackground(Color.GREEN);
		this.setBounds(0,0,settings.getWidth(),settings.getHeight());
		this.setLayout(null);
		this.setOpaque(false);
		this.direction = "left";
				
	}
	

	public static Snake get() {
		
		return object;
		
	}
	
		
	public void grow() {		

		Body segment = new Body();		
		segment.type = 2;
		segment.column = 0;
		segment.row = 0;
		grid.place(segment);
		
	}
	
	
	public void changeColor() {
		
		Color randomColor = this.randomColor.getCurrent();
		BaseObj head = (BaseObj) grid.getGroup("Head").get(0);
		head.color = randomColor;
		
		ArrayList body = grid.getGroup("Body");
		
		for (int i = 0; i<body.size();i++) {
			
			BaseObj bodySegment = (BaseObj) body.get(i);
			bodySegment.color = randomColor;
			
		}
		
	}
	
	
	
	public void move() {
		 
		this.moveHead();
		this.moveBody();

	}
	
	
	public void moveHead() {
		
		BaseObj head = (BaseObj) grid.getGroup("Head").get(0);
		head.prevColumn = head.column;
		head.prevRow = head.row;	
		head.column += this.vx;
		head.row += this.vy;	
		grid.place(head);
		
	}
	
	
	public void moveBody() {
		
	BaseObj head = (BaseObj) grid.getGroup("Head").get(0);
		
		ArrayList body = grid.getGroup("Body");
		
		for (int i = 0; i<body.size();i++) {
			
			BaseObj bodySegment = (BaseObj) body.get(i);
			bodySegment.prevColumn = bodySegment.column;
			bodySegment.prevRow = bodySegment.row;
			
			if(i == 0) {	
				
				bodySegment.column = head.prevColumn;
				bodySegment.row = head.prevRow;	
				
			} else {
				
				BaseObj prevSegment = (BaseObj) body.get(i-1);
				bodySegment.column = prevSegment.prevColumn;
				bodySegment.row = prevSegment.prevRow;
				
			}
			
			grid.place(bodySegment);	
			
		}
		
	}
	
	
	public void reset() {
		
		this.vx = 0;
		this.vy = 0;
		
	}
	
	
}
