import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelBuilder{

	static LevelBuilder object = new LevelBuilder();
	private Canvas canvas = Canvas.getInstance();
	private Levels levels = Levels.getInstance();
	private Grid grid = Grid.getInstance();
	private Food food = Food.getInstance();
	
	
	private LevelBuilder(){
	
	}
		
	
	public void build(String levelName) {
		Game game = Game.getInstance();
		
		 int[] level;
		
		if(levelName.equals("Level 1")) {
			level = levels.level1;
		} else if(levelName.equals("Level 2")) {
			System.out.println("LOAD LEVEL 2");
			level = levels.level2;
		} else {
			level = levels.level1;
		}
		
		
	
		
		int [][] gridMapped = grid.map(level, 10, 10, 420, 420);
		System.out.println("LOLOLOOLOLO");
		int i = 0;
		
		while (i < gridMapped.length) {		
			if(gridMapped[i][0]==1) {
				
				
				Square block = new Square(42,42);
				block.type = 1;
				block.paste(gridMapped[i][1], gridMapped[i][2]);
				game.add(block);
			
			} else if (gridMapped[i][0]==9) {
				
				this.addHead(gridMapped[i][1], gridMapped[i][2]);
				
			} else if (gridMapped[i][0]==2) {
				
				this.addBody(gridMapped[i][1], gridMapped[i][2]);
				
			} 
			i++;
		}
		this.addFood();	
		
		
	}
	
	public void addFood() {
		Game game = Game.getInstance();
		food.paste();
		game.add(food);	
	}
	
	public void addHead(int column, int row) {
		System.out.println("ADD HEAD");
		Snake snake = Snake.getInstance();
		snake.addSegment(column,row,9);
		snake.paste();
		
	}
	
	public void addBody(int column, int row) {
		Snake snake = Snake.getInstance();
		snake.addSegment(column,row,2);
		snake.paste();
		
	}
	
	
	
	//levelBuilder.addFood();
	
	public static LevelBuilder getInstance() {
		return object;
	}
	
}



/*
int columnIndex = 0;
int rowIndex = 0;
int cellIndex = 0;
int rowEnd = this.numColumns;
for (int cell : grid) {

	if(cellIndex==rowEnd) {
		columnIndex = 0;
		rowIndex +=1;
		rowEnd += this.numColumns;			
		System.out.print("\n");	
	}
	
	if(rowIndex==0 || rowIndex==this.numRows-1) {
		grid[cellIndex] = 1;
	} else if(columnIndex==0 || columnIndex==this.numColumns-1) {
		grid[cellIndex] = 1;
	}
			
	System.out.print(grid[cellIndex]);
	columnIndex++;
	cellIndex++;
}*/



