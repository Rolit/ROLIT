package rolit;

import java.awt.*;

import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

public class GUI extends JFrame implements Observer{
	
	// -- Instance variables -----------------------------------------

	private JLabel  lb;
	public JLabel validMoveLabel;
	private JButton[] buttons;
	private JButton replay;
	
	// -- Constructors -----------------------------------------------
	
	public GUI(Game game) {
	    super("GUI");
	    init(game);
	}
	
	// -- Commands ---------------------------------------------------
	
	private void init(Game game) {
		 Container c = getContentPane();
		 c.setLayout(new GridLayout(0,8));
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
				c.add(buttons[ind]);
			}
		}

		lb = new JLabel("It is ROOD's turn");
		validMoveLabel = new JLabel("");
		
		new RolitController(buttons, game, replay);
		
		c.add(lb);
		c.add(validMoveLabel);
		setSize(800,900); 
		setVisible(true);
		
		replay = new JButton("New Game?");
		replay.setEnabled(false);
		c.add(replay);
		
		game.addObserver(this);
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
			lb.setText("It's " + board.toString(mark) + "turn");
			for (int x = 0; x < Board.DIM; x++){
				for(int y = 0; y < Board.DIM; y++){
					Color k = ((AbstractButton) buttons[Board.index(x, y)]).getBackground();
					if(!k.equals(Color.BLACK)){
						
						buttons[Board.index(x, y)].setEnabled(false);
					}
					else{
						buttons[Board.index(x, y)].setEnabled(true);
					}
				}
			}
		}
		
	}
	
	static public void main(String[] args) {
	    Game game = new Game();
	    new GUI(game);  
	}
}
