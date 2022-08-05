import java.awt.Color;
import javax.swing.JFrame;


public class Window extends JFrame{
	
	static Window instance = new Window();
	StartWindow startWindow = StartWindow.getInstance();
	Game game = Game.getInstance();
	RestartWindow restartWindow = RestartWindow.getInstance();

		
	private Window(){
		
		this.add(startWindow);
		//game.start();
		//game.requestFocus();
			
		this.setTitle("Snake3000");
		this.getContentPane().setBackground( Color.WHITE );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);	
		this.pack();

	}
	

	
	public void switchWindow(String current, String replacement) {
		
		if(current=="StartWindow") {
			this.removeWindow(startWindow);
		}
		if(current=="Game") {
			this.removeWindow(game);
		}
		if(current=="RestartWindow") {
			this.removeWindow(restartWindow);
		}
		if(replacement=="Game") {
			this.addWindow(game);
			game.start();
			game.requestFocus();
		}
		if(replacement=="RestartWindow") {
			this.addWindow(restartWindow);
		}
		if(replacement=="StartWindow") {
			this.addWindow(startWindow);
		}
		this.revalidate();
		this.repaint();
	}
	
	
	public void addWindow(Game game) {
		this.add(game);
	}
	
	public void addWindow(StartWindow startWindow) {
		this.add(startWindow);
	}
	
	public void addWindow(RestartWindow restartWindow) {
		this.add(restartWindow);
	}
	

	public void removeWindow(Game game) {
		this.remove(game);
	}
	
	public void removeWindow(StartWindow startWindow) {
		this.remove(startWindow);
	}
	
	public void removeWindow(RestartWindow restartWindow) {
		this.remove(restartWindow);
	}

	public void clear() {
		//this.remove(game); 
	
		
		//this.remove(this.body[i]);  
		//canvas.revalidate();
		//canvas.repaint();
		//*/
	}


	public static Window getInstance() {
		return instance;
	}
}
