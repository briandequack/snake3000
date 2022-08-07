import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends JPanel{
	
	static Grid object = new Grid();
	public JPanel canvas = this;
	private Integer gridWidth = null;
	private Integer gridHeight = null;
	private int columns = 0;
	private int rows = 0;
	public Integer cellWidth = null;
	public Integer cellHeight = null;	
	private int[][] cells;
	ArrayList<Cell> walls = new ArrayList<Cell>();
	
	Map<String, ArrayList> groups = new HashMap<String, ArrayList>();
	
			
	private Grid(){
		
		this.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(420, 420));
		this.setLayout(null);
		this.setOpaque(true);
		
		ArrayList<Cell> group = new ArrayList<Cell>();
		groups.put("boyds",group);
		
		groups.get("bodys").add(new Cell(2));
		
		//System.out.println(groups.get("bodys"));
		
	}
	
	
	public static Grid get() {
		return object;
	}
	
	
	public void group(Cell cell) {
		if(cell.type == 4) {
	
		} else if(cell.type == 2) {
	
		} else if(cell.type == 3) {
		
		} else if(cell.type == 8) {
			walls.add(cell);
		}		
	}
	
	public void place(Cell cell) {
		
		boolean place = false;
		
		if(this.getSignature(cell.getColumn(),cell.getRow())!=cell.signature) {
				
			// clear previous cell
			if(this.getSignature(cell.prevColumn,cell.prevRow)==cell.signature) {			
				this.write(0, cell.prevColumn, cell.prevRow, cell.signature);
			}
			
			// override
			this.write(cell.type, cell.getColumn(), cell.getRow(), cell.signature);	
			place = true;	
			group(cell);
			
		} 
		
		if(place) {
			
			int x = cell.getColumn()*this.cellWidth;
			int y = cell.getRow()*this.cellHeight;
			if(cell.placed) {
				cell.setLocation(x,y);
			} else {
				cell.setLocation(x,y);
				cell.setSize(this.cellWidth,this.cellHeight);		
				canvas.add(cell);
				cell.placed = true;
							
			}
		}
		
		
	}
	
	public void write(int type, int column, int row, int signature) {
		int i = 0;
		System.out.println("type: "+type+" column: "+column+" row: "+row+" signature: "+ signature);
		while (i<this.cells.length) {
			if(this.cells[i][1]==column && cells[i][2]==row) {
				this.cells[i][0]=type;
				this.cells[i][1]=column;
				this.cells[i][2]=row;
				this.cells[i][3]=signature;
				break;
			}
			i++;
		}	
	}
	
	public void mapper(int[] level){
		
		for (int i = 0; i < level.length; i++) {
			  if(level[i]==7 && this.columns==0 ) {
				  this.columns = i;
				  this.rows = level.length/(i+1);
				  this.cells = new int[this.columns*this.rows][4];
				  break;
			  }	  	  
		}
		
		this.gridWidth = Settings.get().getWidth();
		this.gridHeight = Settings.get().getHeight();
		this.cellWidth = this.gridWidth /this.columns;
		this.cellHeight = this.gridHeight/this.rows;	
		
		int cellIndex = 0;
		int columnIndex = 0;
		int rowIndex = 0;
		for (int j = 0; j < level.length; j++) {
			if(level[j]!=7) {
				this.cells[cellIndex][1] = columnIndex;
				this.cells[cellIndex][2] = rowIndex;
				if(level[j]!=0) {
					Cell cell = new Cell(level[j]);
					cell.setColumn(columnIndex);
					cell.setRow(rowIndex);
					this.place(cell);
				}
				cellIndex++;
				columnIndex++;
			} else {
				columnIndex = 0;
				rowIndex++;
			}
		}	
		
	}
	
	
	/*
	public void insert(int type, int column, int row, int signature) {
		int i = 0;
		System.out.println("running");
		while (i<this.cells.length) {
			if(this.cells[i][1]==column && cells[i][2]==row) {
				this.cells[i][0]=type;
				this.cells[i][1]=column;
				this.cells[i][2]=row;
				this.cells[i][3]=signature;
				break;
			}
			i++;
		}
		
	}*/
	
	public void print() {
		System.out.println("\n");
		
		int columnIndex = 0;
		int rowIndex = 0;
		int cellIndex = 0;
		int rowEnd = 10;

		
		while (cellIndex < this.cells.length) {
			if(cellIndex==rowEnd) {
				columnIndex = 0;
				rowIndex +=1;
				rowEnd += 10;			
				System.out.print("\n");	
			}	
			//System.out.println(cellIndex);
			System.out.print(cells[cellIndex][0]);
			columnIndex++;
			cellIndex++;	

		}

	
	}
	


	
	
	
	
	public int[][] getOccupied(){
		
		int occupied[][] = new int[countOccupied()][3];
		int i = this.cells.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.cells[i][0]!=0) {
				occupied[nFound] = cells[i];
				nFound++;
			}
			i--;		
		}
		return occupied;
	}
	
	private int countOccupied() {
		
		int i = this.cells.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.cells[i][0]!=0) {
				nFound++;
			}
			i--;		
		}
		return nFound;
	}
	
	
	public int[][] getEmpty(){
		
		int nEmpty = this.cells.length - this.countOccupied();
		int empty[][] = new int[nEmpty][3];
		int i = this.cells.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.cells[i][0]==0) {
				empty[nFound] = cells[i];
				nFound++;
			}
			i--;		
		}
		return empty;
		
	}

	
	
	public int[] getRandom() {

		Random rand = new Random();
		int n = rand.nextInt(((this.getEmpty().length-1) - 0) + 1) + 0;
		return this.getEmpty()[n];
	
	}
	
	public int getSignature(int column, int row) {

		int i = 0;
		
		while (i<this.cells.length) {
			if(this.cells[i][1]==column && this.cells[i][2]==row) {
				 return cells[i][3];
			}
			i++;		
		}
		return 999;
	
	}
	
	public int getType(int column, int row) {

		int i = 0;
		
		while (i<this.cells.length) {
			if(this.cells[i][1]==column && this.cells[i][2]==row) {
				 return cells[i][0];
			}
			i++;		
		}
		return 999;
	
	}

	
	public int[] getXY(int numColumn, int numRow) {	
		
		int[] coordinates={numColumn*this.cellWidth,numRow*this.cellHeight}; 
		return coordinates;  
		
	}
	
	@Override
	public String toString() { 	
		// Return a string representation of the Grid class
	    return "Grid width '" + this.gridWidth + 
	    	   "', Grid heigridHeightt: '" + this.gridHeight + 
	    	   "', Columns: '" + this.columns +
	    	   "', Rows: '" + this.rows +
	    	   "', cellW: '" + this.cellWidth +
	    	   "', cellH: '" + this.cellHeight;
	    
	} 
	
	public void reset() {
		
		this.gridWidth = null;
		this.gridHeight = null;
		this.columns = 0;
		this.rows = 0;
		this.cellWidth = null;
		this.cellHeight = null;	

		for (int i=0; i<cells.length; i++) {
			this.cells[i][0] = 0;
			this.cells[i][1] = 0;
			this.cells[i][2] = 0;
			this.cells[i][3] = 0;
		}
		
	}
	
}
