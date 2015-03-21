package connectFour;

import utils.PlayerColor;
import gui.BoardGUI;
import connectFour.Game;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class Board {

	static int MaxRow = 6;
	static int buttonNumber;
	static boolean nextCheck = true;

	public static int indexButton(int row, int col) {
		return MaxRow * row + col;
	}

	public static Color getColor(int row, int col) {
		buttonNumber = MaxRow * row + col;
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}

	public static Color getColor(int buttonNumber) {
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}

	public static void setColor(int buttonNumber, PlayerColor playercolor) {
		if (playercolor == PlayerColor.RED) {
			BoardGUI.buttons[buttonNumber].setBackground(Color.RED);
		} else if (playercolor == PlayerColor.YELLOW) {
			BoardGUI.buttons[buttonNumber].setBackground(Color.YELLOW);
		} else {
			BoardGUI.buttons[buttonNumber].setBackground(Color.BLACK);
		}
	}

	public static void setStone(int col){
		nextCheck = true;
		PlayerColor currentplayer = Game.getCurrentPlayer();
		for(int row = 0; row < MaxRow; row++){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buttonNumber = MaxRow * row + col;
			if(row == 0 && getColor(buttonNumber) == Color.BLACK){
				setColor(buttonNumber, currentplayer);
			}else{
				int previousButton = MaxRow * (row-1) + col;
				if(getColor(buttonNumber) == Color.BLACK){
					setColor(previousButton, PlayerColor.EMPTY);
					System.out.println(buttonNumber);
					setColor(buttonNumber, currentplayer);
				}
			}
		}
		
	}
}
