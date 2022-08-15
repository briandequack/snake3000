import java.awt.Color;
import javax.swing.JFrame;


public class Window extends JFrame{
	
	static Window instance = new Window();
	StartWindow startWindow = StartWindow.getInstance();
	Game game = Game.getInstance();
	RestartWindow restartWindow = RestartWindow.getInstance();
		
	private Window(){
		
		this.add(startWindow);		
		this.setTitle("Snake3000");
		this.getContentPane().setBackground( Color.WHITE );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);	
		this.pack();

	}
	
	
	public void switchWindow(String current, String replacement) {
		
		if(current=="StartWindow") {
			
			this.remove(startWindow);
			
		}
		
		if(current=="Game") {
			
			this.remove(game);
			
		}
		
		if(current=="RestartWindow") {
			
			this.remove(restartWindow);
			
		}
		
		if(replacement=="Game") {
			
			this.add(game);
			game.load();
			game.requestFocus();
			
		}
		
		if(replacement=="RestartWindow") {
			
			this.add(restartWindow);
			
		}
		
		if(replacement=="StartWindow") {
			
			this.add(startWindow);
			
		}
		
		this.revalidate();
		this.repaint();
		
	}
	

	public static Window getInstance() {
		
		return instance;
		
	}
	
}
