package rolit;

import java.util.LinkedList;
import java.util.Scanner;

public class HumanPlayer extends Player{
	
	// -- Constructors -----------------------------------------------

    public HumanPlayer(String name, Mark1 mark) {
        super(name, mark);
    }

    // -- Commands ---------------------------------------------------

    public LinkedList<Integer> determineMove(Board board) {
    	LinkedList<Integer> choice = new LinkedList<Integer>();
        String prompt = "> " + getName() + " (" + getMark().toString() + ")" + ", what is your choice? ";
        choice = readInt(prompt);
        int x = choice.get(0);
        int y = choice.get(1);
        boolean valid = board.isField(x, y) && board.isEmptyField(x, y);
        while (!valid) {
            System.out.println("ERROR: field " + x + ", " + y + " is no valid x, y.");
            choice = readInt(prompt);
            x = choice.get(0);
            y = choice.get(1);
            valid = board.isField(x, y) && board.isEmptyField(x, y);
        }
        return choice;
    }
    
    private LinkedList<Integer> readInt(String prompt) {
        LinkedList<Integer> value = new LinkedList<Integer>();
        int aantalElementen = 0;
        boolean intRead = false;
        do {
            System.out.print(prompt);
            @SuppressWarnings("resource")
			String line = (new Scanner(System.in)).nextLine();
            Scanner scannerLine = new Scanner(line);
            if (scannerLine.hasNextInt()) {
            	aantalElementen++;
            	if (aantalElementen == 2){
            		intRead = true;
            	}
                value.add(scannerLine.nextInt());
            }
            scannerLine.close();
        } while (!intRead);

        return value;
    }
}
