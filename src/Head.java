import java.awt.Color;
import java.awt.Graphics;

public class Head extends BaseObj{

	Head(){
		
		this.type = type;
		n++;
		this.signature = n;	
		this.color = Color.black;
		
	}
	

	public void setColumn(int column) {
		
		if(this.placed) {
			
			this.prevColumn = this.column;
			
		} else {
			
			this.prevColumn = column;
			
		}
		
		this.column = column; 
		
	}
	
	
	public int getColumn() {
		
		return this.column;
		
	}
	
	
	public void setRow(int row)	{
		
		if(this.placed) {
			
			this.prevRow = this.row;
			
		} else {
			
			this.prevRow = row;
			
		}
		
		this.row = row;
		
	}
	
	
	public int getRow() {
		
		return this.row;
		
	}

	@Override
	public void draw(Graphics g) {
	
		int eyeWidth = this.width/3;
		int eyeHeight = this.height/3;	
		Snake snake = Snake.get();
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		
		if(snake.direction == "up" || snake.direction == "down") {
			
			g.fillOval(this.x+eyeWidth/10,
					   this.y+this.height/2-eyeHeight/2,
					   eyeWidth,
					   eyeHeight);
			
			g.fillOval(this.x+this.width-eyeWidth-eyeWidth/10,
					   this.y+this.height/2-eyeHeight/2,
					   eyeWidth,
					   eyeHeight);
			 
		}  else if(snake.direction == "left" || snake.direction == "right" ) {
			
			g.fillOval(this.x+this.width/2-eyeWidth/2,
					   this.y+eyeHeight/10,
					   eyeWidth,
					   eyeHeight);
			
			g.fillOval(this.x+this.width/2-eyeWidth/2,
					   this.y+this.height-eyeHeight-eyeHeight/10,
					   eyeWidth,
					   eyeHeight);		
			
		} 
			
	}
	
}
