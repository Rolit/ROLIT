package rolit;

public class Rolit {

	private static Player createPlayer(String name, Mark1 m){
		if (name.equals("Naive")){
			return new ComputerPlayer(m, new NaiveStrategy());
		}
		else {
			return new HumanPlayer(name, m);
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		if (args.length == Game.NUMBER_PLAYERS){
			System.out.println("Computer Rolit");
			System.out.println("--------------");
			if (2 == Game.NUMBER_PLAYERS){
				Player s1 = createPlayer(args[0], Mark1.ROOD);
				Player s2 = createPlayer(args[1], Mark1.GROEN);
			}
			if (3 == Game.NUMBER_PLAYERS){
				Player s1 = createPlayer(args[0], Mark1.ROOD);
				Player s2 = createPlayer(args[1], Mark1.GEEL);
				Player s3 = createPlayer(args[2], Mark1.GROEN);
			}
			if (4 == Game.NUMBER_PLAYERS){
				Player s1 = createPlayer(args[0], Mark1.ROOD);
				Player s2 = createPlayer(args[1], Mark1.GEEL);
				Player s3 = createPlayer(args[2], Mark1.GROEN);
				Player s4 = createPlayer(args[3], Mark1.BLAUW);
			}
			Game spel = new Game();
			spel.start();
		}
		else{
			System.out.println("usage: Rolit <name1> <name2> <name3> <name4>");
		}
	}
}
