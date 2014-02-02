package rolit;

import java.util.LinkedList;
import java.util.Observable;

public class Game extends Observable{
	// -- Instance variables -----------------------------------------
    private Board board;
    public static int NUMBER_PLAYERS = 2;
    private Player[] players;
    
    private Mark1 currentMark;
    
    public int currentPlayer;
    private Player s0;
    private Player s1;
    private Player s2;
    private Player s3;
    public String notValidMoveString;
    LinkedList<LinkedList<Integer>> posmove;
    
    // -- Constructors -----------------------------------------------
    
    public Game() {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        if( players.length == 3){
        	players[2] = s2; 
        }
        else if(players.length == 4){
        	players[2] = s2;
        	players[3] = s3;
        }
        currentMark = Mark1.ROOD;
        currentPlayer = 0;
    }
    
    // -- Commands ---------------------------------------------------

	public void start() {
        boolean doorgaan = true;
        while (doorgaan) {
            reset();
            play();
        }
    }
    
    public void reset() {
        currentPlayer = 0;
        currentMark = Mark1.ROOD;
        board.reset();
        
        setChanged();
        notifyObservers();
    }
    
    public void play() {
        while (!board.gameOver()) {
            players[currentPlayer].makeMove(board);
            currentPlayer = (currentPlayer + 1) % NUMBER_PLAYERS;
            currentMark = currentMark.next(NUMBER_PLAYERS);
        }
    }
    
    public Board getBoard(){
    	return board;
    }
    
    public Mark1 getCurrentMark(){
    	return currentMark;
    }
    
    public void takeTurn(int x, int y){
    	board.activeFields(getCurrentMark());
    	for (int i = 0; i < board.mogelijkeSet.size(); i++){
    		LinkedList<Integer> coordinaat = new LinkedList<Integer>();
    		coordinaat = board.mogelijkeSet.get(i);
    		int x1 = coordinaat.get(0);
    		int y1 = coordinaat.get(1);
    		if( x == x1 && y == y1){
    			board.setField(x, y, currentMark);
    			board.recolorFields(x, y, currentMark);
    			currentMark = currentMark.next(NUMBER_PLAYERS);
    			
    		}
    		else{
    		}
    		
    	}
    	
    	setChanged();
    	notifyObservers();
    }
    
    public void hint(){
    	posmove = board.activeFields(getCurrentMark());
    }
    	
}
