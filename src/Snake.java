import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Snake extends JPanel{
	
	private Grid grid = Grid.get();
	private RandomColor randomColor = RandomColor.getInstance();
	static Snake object = new Snake();
	
	Square body[] = new Square[100];
	int numSegments = 0;
	boolean eating = false;
	boolean hit = false;
	int vx = 0;
	int vy = 0;
	

	private Snake(){
		
		this.setBackground(Color.GREEN);
		this.setBounds(0,0,420,420);
		this.setLayout(null);
		this.setOpaque(false);
				
	}
	

	public static Snake getInstance() {
		return object;
	}
	
		
	public void grow() {		
		this.addSegment(body[numSegments-1].column, body[numSegments-1].row,2);
	}
	
	public void changeColor() {
		Color myColor = this.randomColor.getCurrent();
		for (Square object : body) {
			if(object != null) {
				object.color = myColor;
			}
		}
	}
	

	
	public void addSegment(int column,int row, int type){
		Game game = Game.getInstance();
		System.out.println("ADD SEGMENT");
		Square bodySegment = new Square(42,42);	
		this.body[this.numSegments] = bodySegment;
		bodySegment.nextColumn = column;
		bodySegment.nextRow = row;
		bodySegment.type = type;
		bodySegment.signature = bodySegment.type+this.numSegments;
		game.add(bodySegment);		
		this.numSegments++;
		
	}
	

	
	public void up() {
		this.vx = 0;
		this.vy = -1;
	}
	
	public void down() {
		this.vx = 0;
		this.vy = 1;
	}
	
	public void left() {
		this.vx = -1;
		this.vy = 0;
	}
	
	public void right() {
		this.vx = 1;
		this.vy = 0;
	}

	
	
	public void move() {
		int index = 0;
		for (Square object : body) {
			if(object != null) {
				if(index == 0) {
					object.nextColumn = object.column;
					object.nextRow = object.row;
					object.nextColumn += this.vx;
					object.nextRow += this.vy;
							
					int targetType = this.grid.getType(object.nextColumn, object.nextRow);
					int targetSignature = this.grid.getSignature(object.nextColumn, object.nextRow);
					int tailSignature = this.body[numSegments-1].signature;
					
					if(targetType==3) { //  food
						this.eating = true;
					} else if (targetType==1) { // wall
						this.hit = true;
					} else if (targetType==2) { // body
						if(targetSignature!=tailSignature) { //tail
							this.hit = true;
						}
					}	
				
								
				} else {
					object.nextColumn = body[index-1].column;
					object.nextRow = body[index-1].row;
				}

				
			}
			index +=1;
		}		
	}
	
	
	public void paste() {
		
		for (Square object : body) {
			if(object != null) {
				//System.out.println(object.type);
				object.paste(object.nextColumn, object.nextRow);
			}
		}			
	}
	
	public void reset() {
		
		this.numSegments = 0;
		this.eating = false;
		this.hit = false;
		this.vx = 0;
		this.vy = 0;
		
		this.body = new Square[100];
		
	}
	
	
}
