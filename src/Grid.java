import java.awt.Color;
import java.util.Random;

public class Grid {

	private LevelBuilder levelBuilder = LevelBuilder.getInstance();
	private Integer gWidth = null;
	private Integer gHeight = null;
	private int nColumns = 10;
	private int nRows = 10;
	private Integer cWidth = null;
	private Integer rHeight = null;	
	static Grid object = new Grid();
	private int gridMapped[][] = new int[100][4];
			
	private Grid(){
		
	}
	
	
	public static Grid getInstance() {
		return object;
	}
	

	
	public void insert(int type, int column, int row, int signature) {
		int i = 0;
		while (i<=this.gridMapped.length) {
			if(gridMapped[i][1]==column && gridMapped[i][2]==row) {
				gridMapped[i][0]=type;
				gridMapped[i][1]=column;
				gridMapped[i][2]=row;
				gridMapped[i][3]=signature;
				break;
			}
			i++;
		}
	}
	
	public void print() {
		System.out.println("\n");
		
		int columnIndex = 0;
		int rowIndex = 0;
		int cellIndex = 0;
		int rowEnd = 10;

		
		while (cellIndex < this.gridMapped.length) {
			if(cellIndex==rowEnd) {
				columnIndex = 0;
				rowIndex +=1;
				rowEnd += 10;			
				System.out.print("\n");	
			}	
			//System.out.println(cellIndex);
			System.out.print(gridMapped[cellIndex][0]);
			columnIndex++;
			cellIndex++;	

		}

	
	}
	
	
	public int[][] map(int[] grid, int columns, int rows, int w, int h) {
		
		this.nColumns = columns;
		this.nRows = rows;
		this.gWidth = w;
		this.gHeight = h;
		this.cWidth = w/columns;
		this.rHeight = h/rows;	
			
		int columnIndex = 0;
		int rowIndex = 0;
		int cellIndex = 0;
		int rowEnd = columns;

		//System.out.print("\n");	
		
		while (cellIndex < grid.length) {
			if(cellIndex==rowEnd) {
				columnIndex = 0;
				rowIndex +=1;
				rowEnd += columns;			
				//System.out.print("\n");	
			}	
			gridMapped[cellIndex][0] = grid[cellIndex];
			gridMapped[cellIndex][1] = columnIndex;
			gridMapped[cellIndex][2] = rowIndex;
			gridMapped[cellIndex][3] = 0;
			//System.out.print(gridMapped[cellIndex][0]);
			columnIndex++;
			cellIndex++;	

		}
		System.out.println("MAPPING COMPLETE");
		return gridMapped;
	}
	
	public int[][] getOccupied(){
		
		int occupied[][] = new int[countOccupied()][3];
		int i = this.gridMapped.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.gridMapped[i][0]!=0) {
				occupied[nFound] = gridMapped[i];
				nFound++;
			}
			i--;		
		}
		return occupied;
	}
	
	private int countOccupied() {
		
		int i = this.gridMapped.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.gridMapped[i][0]!=0) {
				nFound++;
			}
			i--;		
		}
		return nFound;
	}
	
	
	public int[][] getEmpty(){
		
		int nEmpty = this.gridMapped.length - this.countOccupied();
		int empty[][] = new int[nEmpty][3];
		int i = this.gridMapped.length-1;
		int nFound = 0;
		while (i>=0) {
			if(this.gridMapped[i][0]==0) {
				empty[nFound] = gridMapped[i];
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
		
		while (i<this.gridMapped.length) {
			if(this.gridMapped[i][1]==column && this.gridMapped[i][2]==row) {
				 return gridMapped[i][3];
			}
			i++;		
		}
		return 999;
	
	}
	
	public int getType(int column, int row) {

		int i = 0;
		
		while (i<this.gridMapped.length) {
			if(this.gridMapped[i][1]==column && this.gridMapped[i][2]==row) {
				 return gridMapped[i][0];
			}
			i++;		
		}
		return 999;
	
	}

	
	public int[] getXY(int numColumn, int numRow) {	
		
		int[] coordinates={numColumn*this.cWidth,numRow*this.rHeight}; 
		return coordinates;  
		
	}
	
	@Override
	public String toString() { 	
		// Return a string representation of the Grid class
	    return "Grid width '" + this.gWidth + 
	    	   "', Grid heigHeightt: '" + this.gHeight + 
	    	   "', Columns: '" + this.nColumns +
	    	   "', Rows: '" + this.nRows +
	    	   "', cellW: '" + this.cWidth +
	    	   "', cellH: '" + this.rHeight;
	    
	} 
	
	public void reset() {
		
		this.gWidth = null;
		this.gHeight = null;
		this.nColumns = 10;
		this.nRows = 10;
		this.cWidth = null;
		this.rHeight = null;	
		for (int i=0; i<gridMapped.length; i++) {
			this.gridMapped[i][0] = 0;
			this.gridMapped[i][1] = 0;
			this.gridMapped[i][2] = 0;
			this.gridMapped[i][3] = 0;
		}
		
	}
	
}
