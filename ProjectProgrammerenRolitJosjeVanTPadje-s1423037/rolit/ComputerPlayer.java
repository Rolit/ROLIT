package rolit;

import java.util.LinkedList;

public class ComputerPlayer extends Player{
	//------------- Constructors-------------
	Strategy strategy;
		
	public ComputerPlayer (Mark1 mark, Strategy strategy){
		super(strategy +"-"+ mark, mark);
		this.strategy = strategy;
	}
		
	public ComputerPlayer (Mark1 mark){
		this(mark, new NaiveStrategy());
	}
	
	//public String getName(){
	//	return strategy +"-"+ mark;
	//}
	
	public LinkedList<Integer> determineMove(Board board) {
		return strategy.determineMove(board, super.getMark());
	}
}
