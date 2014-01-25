package rolit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class RolitController implements ActionListener{
	Game game;
	JButton replay;
	JButton[] buttons;
	Board board;
	
	public RolitController(JButton[] buttons, Game game, JButton replay){
		this.replay = replay;
		this.game = game;
		this.buttons = buttons;
		for(int x = 0; x < buttons.length; x++){
			buttons[x].addActionListener(this);
		}
		replay = new JButton();
		replay.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean tTurn = false;
		if (ae.getSource() == replay){
			game.reset();
			replay.setEnabled(false);
		}
		else{
			for (int x = 0; x < Board.DIM && !tTurn; x++){
				for(int y = 0; y < Board.DIM && !tTurn; y++){
					if(ae.getSource() == buttons[Board.index(x, y)]){
						game.takeTurn(x, y);
						tTurn = true;
					}
				}
			}
			tTurn = false;
		}
	}

}
