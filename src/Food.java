import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;

public class Food extends JLabel{

	static Food object = new Food();
	private Grid grid = Grid.get();
	private RandomColor randomColor = RandomColor.getInstance();
	int w = 42;
	int h = 42;
	int signature = 999;
	Integer column = null;
	Integer row = null;
	
	private Food() {

		this.setBounds(0,0,42,42);
		this.setBackground(this.randomColor.getCurrent());		
			
	}
	
	public static Food getInstance() {
		return object;
	}
	
	
	
	public void paste() {
		
		//this.setBackground(randomColor.getNext());
		/*
		this.setBackground(Color.WHITE);
		this.setForeground(Color.WHITE);
		int[] randomPos = grid.getRandom();

		//System.out.print("CURRENT CELL: "+this.column+ " ");
		//System.out.print("CURRENT CELL: "+this.row+ " ");
		//System.out.println("TARGET CELL TYPE: "+grid.getType(randomPos[1],randomPos[2]));

		//System.out.println("TARGET CELL SIG: "+grid.getSignature(randomPos[1],randomPos[2]));
		
		
		if(this.column!=null && this.row!=null) {
			this.grid.insert(0, this.column, this.row, this.signature);
		}	
			
		this.column = randomPos[1];
		this.row = randomPos[2];
		
		this.setLocation(grid.getXY(randomPos[1], randomPos[2])[0],
						 grid.getXY(randomPos[1], randomPos[2])[1]);
		
		this.grid.insert(3, randomPos[1], randomPos[2], this.signature);
		this.setOpaque(true);
		
		System.out.println("INSERT FOOD");

		//grid.print();
		 * 
		 */
	}
}
