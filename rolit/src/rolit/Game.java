package rolit;

import java.util.Scanner;

public class Game {
	// -- Instance variables -----------------------------------------

    public static final int NUMBER_PLAYERS = 2;
    
    private Board board;
    
    private Player[] players;
    
    private int current;
    
    // -- Constructors -----------------------------------------------
    
    public Game(Player s0, Player s1) {
        board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
        current = 0;
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
    
    private void reset() {
        current = 0;
        board.reset();
    }
    
    private void play() {
        update();
        while (!board.gameOver()) {
            players[current].makeMove(board);
            current = (current + 1) % NUMBER_PLAYERS;
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

    
}
