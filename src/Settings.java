import java.awt.Dimension;
import java.awt.Toolkit;

public class Settings {

	
	static Settings instance = new Settings();
	private int frameWidth = 990;
	private int frameHeight = 660;
	private int speed = 125;
	
	
	private Settings() {}
	
	
	public static Settings get() {
		
		return instance;
		
	}
	
	
	public int getWidth() {
		
		return this.frameWidth;
		
	}
	
	
	public int getHeight() {
		
		return this.frameHeight;
		
	}
	
	
	public int getSpeed() {
		
		return this.speed;
		
	}
	
}
