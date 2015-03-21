package connectFour;

import utils.PlayerColor;
import utils.GameState;
import connectFour.Board;

public class Game {
	
	static int Dimension = 7;
	static int maxCol = 7;
	static int maxRow = 6;
	static String playerColor;
	static PlayerColor currentplayer = PlayerColor.RED;
	static PlayerColor winner;
	static GameState gamestate = GameState.NOTSTARTED;
	static int horizontalCount = 0;
	static int verticalCount = 0;
	static int diagonalLeftCount = 0;
	static int diagonalRightCount = 0;
	
	public static GameState getGameState(){
		return gamestate;
	}
	
	public static PlayerColor getCurrentPlayer(){
		return currentplayer;
	}
	
	public static void setGameState(String gameState){
		if(gameState.equals("finished")){
			gamestate = GameState.FINISHED;
		}else if(gameState.equals("draw")){
			gamestate = GameState.DRAW;
		}else if(gameState.equals("inprogress")){
			gamestate = GameState.INPROGRESS;
		}else if(gameState.equals("notstarted")){
			gamestate = GameState.NOTSTARTED;
		}else {
			System.out.println("The gamestate '" + gameState + "' is not supported.\n Try another one, maybe a typo?");
		}
	}
	
	public static void nextTurn(){
		if(currentplayer == PlayerColor.RED){
			currentplayer = PlayerColor.YELLOW;
		}else{
			currentplayer = PlayerColor.RED;
		}
	}
	
	public static String toStringPlayer(){
		if(currentplayer == PlayerColor.RED){
			playerColor = "RED";
		}else{
			playerColor = "YELLOW";
		}
		return playerColor;
	}
	
	public static void countColor(int row, int col){
		int buttonNumber = Dimension * row + col;
		//Check Left
		if(col != 0){
			for(int x = col; x >= 0; x--){
				if(Board.getColor(row, x) == Board.getColor(buttonNumber)){
					horizontalCount++;
				}
			}
		}
		//Check Right
		if(col != maxCol-1){
			for(int x = col; x >= col && x < maxCol; x++){
				if(Board.getColor(row, x) == Board.getColor(buttonNumber)){
					horizontalCount++;
				}
			}
		}
		//Check Up
		if(row != 0){
			for(int x = row; x >= 0; x--){
				if(Board.getColor(x, col) == Board.getColor(buttonNumber)){
					verticalCount++;
				}
			}
		}
		//Check Down
		if(row != maxRow-1){
			for(int x = row; x >= row && x <= maxRow; x++){
				if(Board.getColor(x, col) == Board.getColor(buttonNumber)){
					verticalCount++;
				}
			}
		}
		//Check Diagonal Left Up
		if(row != 0 || col != 0 || (row != 0 && col != 0)){
			for(int x = col; x >= 0; x--){
				for(int y = row; y >= 0; y--){
					if(Board.getColor(y,x) == Board.getColor(buttonNumber)){
						diagonalLeftCount++;
					}
				}
			}
		}
		//Check Diagonal Left Down
		if(row != maxRow-1 || col != maxCol-1 || (row != maxRow-1 && col != maxCol-1)){
			for(int x = col; x >= col && x < maxCol; x++){
				for(int y = row; y >= row && y < maxRow; y++){
					if(Board.getColor(y,x) == Board.getColor(buttonNumber)){
						diagonalLeftCount++;
					}
				}
			}
		}
		//Check Diagonal Right Up
		if(row != 0 || col != maxCol-1 || (row != 0 && col != maxCol-1)){
			for(int x = col; x >= 0; x--){
				for(int y = row; y >= 0; y--){
					if(Board.getColor(y, x) == Board.getColor(buttonNumber)){
						diagonalRightCount++;
					}
				}
			}
		}
		//Check Diagonal Right Down
		if(row != maxRow -1 || col != 0 || (row != maxRow-1 && col != 0)){
			for(int x = col; x >= col && x < maxCol; x++){
				for(int y = row; y >= row && y < maxRow; y++){
					if(Board.getColor(y, x) == Board.getColor(buttonNumber)){
						diagonalRightCount++;
					}
				}
			}
		}
		countFour();
		if(getGameState() != GameState.FINISHED || getGameState() != GameState.DRAW){
			nextTurn();
		}
	}
	
	public static void countFour(){
		if(horizontalCount >= 4 || verticalCount >= 4 || diagonalLeftCount >= 4 || diagonalRightCount >= 4){
			setGameState("finished");
			winner = currentplayer;
		}
	}
	
	
}
