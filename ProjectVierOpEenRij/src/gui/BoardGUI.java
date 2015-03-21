package gui;

import connectFour.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class BoardGUI {
	
	JPanel connectFourPanel;
	JFrame board;
	public static JButton[] buttons;
	
	public BoardGUI(){
		boardFrame();
	}
	
	public JFrame boardFrame(){
		board = new JFrame("Vier Op Een Rij");
		
		BorderLayout border = new BorderLayout();
		board.setLayout(border);
		board.add(connectFourPanel(), BorderLayout.CENTER);
		board.setVisible(true);
		board.setSize(700,700);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return board;
	}
	
	private JPanel connectFourPanel(){
		connectFourPanel = new JPanel();
		GridLayout gridlayout = new GridLayout(6,7);
		connectFourPanel.setLayout(gridlayout);
		buttons = new JButton[42];
		for(int x = 0; x < 6; x++){
			for(int y = 0; y < 7; y++){
				int index = Board.indexButton(x, y);
				buttons[index] = new JButton();
				buttons[index].setBackground(Color.black);
				buttons[index].setText(Integer.toString(index));
				connectFourPanel.add(buttons[index]);
			}
		}
		return connectFourPanel;
	}
	
	public static void main(String [] args){
		new BoardGUI();
	}
}
