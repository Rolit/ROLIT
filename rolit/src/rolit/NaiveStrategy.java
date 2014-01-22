package rolit;

import java.util.LinkedList;

public class NaiveStrategy implements Strategy{
	
	public String getName(){
		return "Naive";
	}
	
	public LinkedList<Integer> determineMove(Board b, Mark1 m){
		LinkedList<LinkedList<Integer>> possibleMove = b.activeFields(m);
		int randomField = (int) Math.random() * possibleMove.size();
		return possibleMove.get(randomField);
	}
}
