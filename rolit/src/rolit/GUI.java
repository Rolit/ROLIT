package rolit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class GUI extends JFrame implements Observer, ActionListener {
	
	// -- Instance variables -----------------------------------------

	private JLabel  lb;
	public JLabel validMoveLabel;
	private JButton[] buttons;
	private JButton replay;
	public JFrame rolitFrame;
	private JButton hint;

	Game game = new Game();
	
	// -- Constructors -----------------------------------------------
	
	public GUI(Game game) {
	    super("GUI");
	    init(game);
	}
	
	// -- Commands ---------------------------------------------------
	
	public void init(Game game) {
		
		rolitFrame();
		new RolitController(buttons, game, replay, hint);
		game.addObserver(this);
	}
	
	
	public void rolitFrame(){
		rolitFrame = new JFrame();
		rolitFrame.setLayout(new BorderLayout());
		rolitFrame.add(rolitPanel(), BorderLayout.CENTER);
		rolitFrame.add(commentPanel(), BorderLayout.EAST);
		
		rolitFrame.setSize(1000,900); 
		rolitFrame.setVisible(true);
	}
	
	private JPanel commentPanel(){
		JPanel commentPanel = new JPanel(); 
		commentPanel.setLayout(new GridLayout(0,1));
		
		lb = new JLabel("It is ROOD's turn");
		
		commentPanel.add(lb);
		JPanel innerPanel = new JPanel();
		JPanel innerPanel2 = new JPanel();
		hint = new JButton("hint");
		
		replay = new JButton("New Game?");
		replay.setEnabled(false);
		replay.addActionListener(this);
		innerPanel.add(replay);
		innerPanel2.add(hint);
		commentPanel.add(innerPanel);
		commentPanel.add(innerPanel2);
		return commentPanel;
	}

	private JPanel rolitPanel(){
		JPanel rolitPanel = new JPanel();
		rolitPanel.setLayout(new GridLayout(0,8));
		buttons = new JButton[64]; 
		for(int x = 0; x < Board.DIM; x++){
			for(int y = 0; y < Board.DIM; y++){
				int ind = Board.index(x, y);
				buttons[ind] = new JButton();
				if(x == 3 && y == 3){
					buttons[ind].setBackground(Color.RED);
					buttons[ind].setEnabled(false);
				}
				else if(x == 4 && y == 3){
					buttons[ind].setBackground(Color.BLUE);
					buttons[ind].setEnabled(false);
				}
				else if(x == 3 && y == 4){
					buttons[ind].setBackground(Color.YELLOW);
					buttons[ind].setEnabled(false);
				}
				else if(x == 4 && y == 4){
                   buttons[ind].setBackground(Color.GREEN);
                   buttons[ind].setEnabled(false);
				}
				else{
                   buttons[ind].setBackground(Color.BLACK);
				}
				rolitPanel.add(buttons[ind]);
			}
		}
		return rolitPanel;
	}
	
	
	public void update(Observable o, Object arg) {
		Board board = ((Game)o).getBoard();
		Mark1 mark = ((Game)o).getCurrentMark();
		
		
		
		for (int x = 0; x < Board.DIM; x++){
			for(int y = 0; y < Board.DIM; y++){
				buttons[Board.index(x, y)].setBackground(board.toColor(board.getField(x, y)));
			}
		}
		if (board.hasWinner() && board.isFull()){
			lb.setText(board.getWinner());
			
			replay.setEnabled(true);
			
			for (int x = 0; x < Board.DIM; x++){
				for (int y = 0; y < Board.DIM; y++){
					buttons[Board.index(x, y)].setEnabled(false);
				}
			}
		}
		else{
			lb.setText("It's " + board.toString(mark) + "'s turn");
			for (int x = 0; x < Board.DIM; x++){
				for(int y = 0; y < Board.DIM; y++){
					Color k = ((AbstractButton) buttons[Board.index(x, y)]).getBackground();
					if(k.equals(Color.BLACK) || k.equals(Color.GRAY)){
						
						buttons[Board.index(x, y)].setEnabled(true);
					}
					else{
						buttons[Board.index(x, y)].setEnabled(false);
					}
				}
			}
		}
	}
	
	
	
	static public void main(String[] args) {
	    Game game = new Game();
	    new GUI(game);  
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == replay){
			rolitFrame.dispose();
			new Start();
		}
		
	}
}
