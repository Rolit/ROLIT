package rolit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;

public class RolitController implements ActionListener{
	Game game;
	JButton replay;
	public JButton[] buttons;
	JButton hint;
	Board board;
	
	
	public RolitController(JButton[] buttons, Game game, JButton replay, JButton hint){
		this.replay = replay;
		this.game = game;
		this.buttons = buttons;
		for(int x = 0; x < buttons.length; x++){
			buttons[x].addActionListener(this);
		}
		
		this.replay = replay;
		this.replay.addActionListener(this);
		this.hint = hint;
		this.hint.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean tTurn = false;
		if (ae.getSource() == replay){
			replay.setEnabled(false);
		}
		else if(ae.getSource() == hint){
			game.hint();
			for (int i = 0; i < game.posmove.size(); i++){
	    		LinkedList<Integer> coordinaat = new LinkedList<Integer>();
	    		coordinaat = game.posmove.get(i);
	    		int x1 = coordinaat.get(0);
	    		int y1 = coordinaat.get(1);
				buttons[Board.index(x1, y1)].setBackground(Color.GRAY);
			}	
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
