import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RestartWindow extends JPanel{

	static RestartWindow instance = new RestartWindow();
	public JLabel score;
	
	private RestartWindow(){
		
		this.setBackground(Color.RED);
		this.setPreferredSize(new Dimension(420, 420));
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.BLUE);
		topPanel.setPreferredSize(new Dimension(420, 210));
		this.add(topPanel, BorderLayout.NORTH);
		
		score = new JLabel("0", JLabel.CENTER);
		score.setFont(new Font("Calibri", Font.BOLD, 30));
		score.setForeground(Color.WHITE);
		topPanel.add(score, BorderLayout.PAGE_END);
				
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLUE);
		bottomPanel.setPreferredSize(new Dimension(420, 210));
		this.add(bottomPanel, BorderLayout.SOUTH);	
		
	    JButton restart = new JButton("Try again!");
	    bottomPanel.add(restart);
	
	    restart.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				Window window = Window.getInstance();
				window.switchWindow("RestartWindow","StartWindow");
			  } 
		});	
	}
		
	public static RestartWindow getInstance() {
		return instance;
	}
}