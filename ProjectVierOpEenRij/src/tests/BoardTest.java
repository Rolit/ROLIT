package tests;

import java.util.concurrent.TimeUnit;

import connectFour.Board;
import connectFour.Game;
import gui.BoardGUI;
import utils.PlayerColor;

public class BoardTest {

	private static int row = 6;
	private static int col = 7;
	
	public BoardTest(){
		new BoardGUI();
//		for(int x = 0; x < 8; x++){
//			System.out.println("x is: " + x);
//			testSetStone(2);
//		}
		testOldSetStone();
	}
	
	private static void testSetStone(int col){
		Board.setStone(col);
	}
	
	private static void testOldSetStone(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int y = 0; y < col; y++){
			Board.setStone(y);
		}
	}
	
	public static void main(String[] args) {
		new BoardTest();

	}

}
