import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Cell extends BaseObj{
		

	int w;
	int h;
	int id;
	public Color color = Color.MAGENTA;
	
	
	
	Cell(int type) {
		
		
		if(type==8) {
			//this.color = Color.BLACK;
		} else if(type==3) {
		//	this.color = Color.RED;
		} else if(type==4) {
		//	this.color = Color.MAGENTA;
		} else if(type==3) {
		//	this.color = Color.YELLOW;
		}
		this.type = type;
		n++;
		this.signature = n;
		
				//System.out.println(" ASDF"+ this.signature);
		//this.setBackground(this.color);
		//this.setOpaque(true);

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
		
		g.setColor(Color.RED);
		g.fillRect(this.x, this.y, this.width, this.height);
		
	}
	

	
	
}
