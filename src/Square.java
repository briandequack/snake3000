import java.awt.Color;
import java.awt.Label;
import java.util.Random;

import javax.swing.JLabel;

public class Square extends JLabel{
	
	Integer column = null;
	Integer row = null;
	int signature = 0;
	Grid grid = Grid.getInstance();
	int nextColumn;
	int nextRow;
	public int type = 0;
	Color color = Color.BLACK;
	public int x;
	public int y;
	
	
	Square(int w, int h){
		
		this.setBounds(0,0,w,h);
		this.setOpaque(true);
		this.setBackground(color);	
	}
	
	public void place(int column,int row) {
		
		this.column = column;
		this.row = row;
		this.setLocation(grid.getXY(column, row)[0],grid.getXY(column, row)[1]);
		
	}
	
	
	public void paste(int column, int row) {
		//System.out.println("MOVE "+ this.type);
		this.setBackground(color);
				
		if(this.column!=null && this.row!=null) {	
			if(this.grid.getSignature(this.column, this.row)==this.signature){	
				this.grid.insert(0, this.column, this.row, this.signature);
			}
		} 
		this.column = column;
		this.row = row;
		
		this.setLocation(grid.getXY(column, row)[0],
						 grid.getXY(column, row)[1]);
		
		this.grid.insert(this.type, column, row, this.signature);

		this.setOpaque(true);
		
	}	
	
	
}
