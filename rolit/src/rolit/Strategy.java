package rolit;

import java.util.LinkedList;

public interface Strategy {
	
	public String getName();
	public LinkedList<Integer> determineMove(Board b, Mark m);

}
