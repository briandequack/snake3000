import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {

	static Settings instance = new Settings();
	private int frameWidth = 600;
	private int frameHeight = 600;
	//public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//double widthscr = screenSize.getWidth();
	//double height = screenSize.getHeight();
	
	private Settings() {
	    int dividend = 700, divisor = 20;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;
        System.out.println("The Quotient is = " + quotient);
        System.out.println("The Remainder is = " + remainder);
	}
	
	public static Settings get() {
		return instance;
	}
	
	public int getWidth() {
		return this.frameWidth;
	}
	
	public int getHeight() {
		return this.frameHeight;
	}
	
	
}
