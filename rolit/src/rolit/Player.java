package rolit;

import java.util.LinkedList;

public abstract class Player {
	
	// -- Instance variables -----------------------------------------

    private String name;
    private Mark mark;
    
    

    // -- Constructors -----------------------------------------------
    
    
    // Creates a new Player
    public Player(String theName, Mark theMark) {
        this.name = theName;
        this.mark = theMark;
    }
    
    
    
    // -- Queries ----------------------------------------------------

    
    // Returns the name of the player.
    public String getName() {
        return name;
    }

    // Returns the mark of the player.
    public Mark getMark() {
        return mark;
    }
    
    public abstract LinkedList<Integer> determineMove(Board board);
    
    
    
    // -- Commands ---------------------------------------------------

 
    // Makes a move on the board. <br>
    
    public void makeMove(Board board) {
        LinkedList<Integer> keuze = determineMove(board);
        int x = 0;
        int y = 0;
        x = keuze.get(0);
        y = keuze.get(1);
        board.recolorFields(x, y, getMark());
    } 

}
