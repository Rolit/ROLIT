package rolit;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DeBug extends JFrame implements ActionListener{
	
	private JButton humanPlayer;
	private JButton computerPlayer;
	Game game = new Game();
	
	public DeBug(){
		JFrame startFrame = new JFrame();
		startFrame.setLayout(new FlowLayout());
		humanPlayer = new JButton("Human Player");
		computerPlayer = new JButton("Computer Player");
		startFrame.add(humanPlayer);
		startFrame.add(computerPlayer);
		
		startFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new DeBug();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == humanPlayer){
		}
	}
}


