import java.awt.Color;
import java.awt.Graphics;

public class Body extends BaseObj{

	Body(){
		this.type = type;
		n++;
		this.signature = n;	
	}
	

	public void setColumn(int column)	{
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
		
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, this.width, this.height);
		
	}
	
}
