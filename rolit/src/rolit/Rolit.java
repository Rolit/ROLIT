package rolit;

public class Rolit {

	private static Player createPlayer(String name, Mark m){
		if (name.equals("Naive")){
			return new ComputerPlayer(m, new NaiveStrategy());
		}
		else {
			return new HumanPlayer(name, m);
		}
	}
	
	public static void main(String[] args){
		if (args.length == Game.NUMBER_PLAYERS){
			System.out.println("Computer Rolit");
			System.out.println("--------------");
			Player s1 = createPlayer(args[0], Mark.ROOD);
			Player s2 = createPlayer(args[1], Mark.GROEN);
			Game spel = new Game(s1, s2);
			spel.start();
		}
		else{
			System.out.println("usage: TicTacToe <name1> <nam2>");
		}
	}
}
