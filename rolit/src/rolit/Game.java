package rolit;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

public class Game extends Observable{
	// -- Instance variables -----------------------------------------
    private Board board;
    public static int NUMBER_PLAYERS = 2;
    private Player[] players;
    
    private Mark1 currentMark;
    
    private int currentPlayer;
    private Player s0;
    private Player s1;
    
    
    // -- Constructors -----------------------------------------------
    
    public Game() {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        currentMark = Mark1.ROOD;
        currentPlayer = 0;
    }
    
    // -- Commands ---------------------------------------------------

	public void start() {
        boolean doorgaan = true;
        while (doorgaan) {
            reset();
            play();
            doorgaan = readBoolean("\n> Play another time? (y/n)?", "y", "n");
        }
    }
    
    private boolean readBoolean(String prompt, String yes, String no) {
        String answer;
        do {
            System.out.print(prompt);
            Scanner in = new Scanner(System.in);
            answer = in.hasNextLine() ? in.nextLine() : null;
            in.close();
        } while (answer == null || (!answer.equals(yes) && !answer.equals(no)));
        return answer.equals(yes);
    }
    
    public void reset() {
        currentPlayer = 0;
        currentMark = Mark1.ROOD;
        board.reset();
        
        setChanged();
        notifyObservers();
    }
    
    public void play() {
        update();
        while (!board.gameOver()) {
            players[currentPlayer].makeMove(board);
            currentPlayer = (currentPlayer + 1) % NUMBER_PLAYERS;
            currentMark = currentMark.next(NUMBER_PLAYERS);
            update();
        }
        printResult();
    }
    
    private void update() {
        System.out.println("\ncurrent game situation: \n\n" + board.toString()
                + "\n");
    }
    
    private void printResult() {
        if (board.hasWinner()) {
            Player winner = board.isWinner(players[0].getMark()) ? players[0]
                    : players[1];
            System.out.println("Speler " + winner.getName() + " ("
                    + winner.getMark().toString() + ") has won!");
        } else {
            System.out.println("Draw. There is no winner!");
        }
    }
    
    public Board getBoard(){
    	return board;
    }
    
    public Mark1 getCurrentMark(){
    	return currentMark;
    }
    
    public void takeTurn(int x, int y){
    	board.activeFields(currentMark);
    	
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
    
}
