import java.awt.Color;
import java.awt.Graphics;

abstract class BaseObj {
	
	int width;
	int height;
	int x;
	int y;
	int signature;
	int column;
	int row;
	int prevColumn;
	int prevRow;
	int nextColumn;
	int nextRow;
	Boolean placed = false;
	int type;
	static int n;
	Color color;

    abstract public int getColumn();
    abstract public int getRow();
    public void draw(Graphics g){}
    
}