package rolit;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class GUI extends JFrame implements ActionListener{
	
	// -- Instance variables -----------------------------------------

	private JLabel  lb; 
	private Mark1 m = Mark1.ROOD;
	private Color kleur = Color.RED;
	private Board board;
	
	// -- Constructors -----------------------------------------------
	
	public GUI() {
	    super("GUI");
	    init();
	    board = new Board();
	}
	
	// -- Commands ---------------------------------------------------
	
	private void init() {
		 Container c = getContentPane();
		 c.setLayout(new GridLayout(0,8));
		 lb = new JLabel("It is ROOD's turn");
		 
		 for(int x = 0; x < board.DIM; x++){
			 for(int y = 0; y < board.DIM; y++){
				JButton button = new JButton();
				if(x == 3 && y == 3){
					button.setBackground(Color.RED);
				}
				else if(x == 4 && y == 3){
					button.setBackground(Color.BLUE);
				}
				else if(x == 3 && y == 4){
					button.setBackground(Color.YELLOW);
				}
				else if(x == 4 && y == 4){
					button.setBackground(Color.GREEN);
				}
				else{
					button.setBackground(Color.BLACK);
				}
				button.addActionListener(this);
				c.add(button);
			}
		}
		
 
		c.add(lb);
		setSize(800,900); 
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev){
		Object active = ev.getSource();
		Color k = ((AbstractButton) active).getBackground();
		if(k.equals(Color.BLACK) && Game.NUMBER_PLAYERS > 1 && Game.NUMBER_PLAYERS < 5){
			if(m.equals(Mark1.ROOD)){
				kleur = Color.RED;
			}
			else if(m.equals(Mark1.GEEL)){
				kleur = Color.YELLOW;
			}
			else if(m.equals(Mark1.GROEN)){
				kleur = Color.GREEN;
			}
			else if(m.equals(Mark1.BLAUW)){
				kleur = Color.BLUE;
			}
			lb.setText("It is " + m.next(Game.NUMBER_PLAYERS) + "'s turn");
			((AbstractButton) active).setBackground(kleur);
			m = m.next(Game.NUMBER_PLAYERS);
		}	
	}
	
	static public void main(String[] args) {
	    new GUI(); 
	    
	}
}
