import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartWindow extends JPanel{


	Settings settings = Settings.get();
	static StartWindow instance = new StartWindow();
	private RandomColor randomColor = RandomColor.getInstance();
	JComboBox level; 
	
	
	private StartWindow(){
				
		this.setPreferredSize(new Dimension(settings.getWidth(),
											settings.getHeight()));
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.BLACK);
		topPanel.setPreferredSize(new Dimension(settings.getWidth(),
												settings.getHeight()/2));
		this.add(topPanel, BorderLayout.NORTH);
		
		JLabel title = new JLabel("Snake3000", JLabel.CENTER);
		title.setFont(new Font("Calibri", Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		topPanel.add(title, BorderLayout.PAGE_END);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setPreferredSize(new Dimension(settings.getWidth(),
												   settings.getHeight()/2));
		this.add(bottomPanel, BorderLayout.SOUTH);	
		
		String levelNames[]={"Level 1", 
							 "Level 2", 
							 "Level 3"};     
		
		level = new JComboBox(levelNames);    
		bottomPanel.add(level);      
				
		JButton start = new JButton("Start");
		bottomPanel.add(start);
				
		start.addActionListener(new ActionListener() { 
			
			  public void actionPerformed(ActionEvent e) { 
				  
					Window window = Window.getInstance();
					window.switchWindow("StartWindow","Game");
					
			  } 
			  
		});
	
	}
	
	
	public static StartWindow getInstance() {
		
		return instance;
		
	}
}