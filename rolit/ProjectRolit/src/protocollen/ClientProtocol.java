package protocollen;

import protocollen.StandardProtocol;

//Abstracte Class met alle constanten en methodes die gebruikt kunnnen worden
public abstract class ClientProtocol extends StandardProtocol {
	
	//Constante met handshake commando
	public static final String HANDSHAKE = "hello";
	
	//Constante voor het auth-commando
	public static final String AUTH = "auth";
	
	//Constante voor het create-game commando
	public static final String CREATE_GAME = "createGame";
	
	//Constante voor het join-game commando
	public static final String JOIN_GAME = "joinGame";
	
	//Constante voor het start-game commando
	public static final String START_GAME = "startGame";
	
	//Constante voor het move commando
	public static final String MOVE = "move";
	
	//Constante voor het message commando
	public static final String MESSAGE = "message";
	
	//Constante voor het challenge commando
	public static final String CHALLENGE = "challenge";
	
	//Constante voor het challenge-response commando
	public static final String CHALLENGE_RESPONSE = "challengeResponse";
	
	//Constante voor het highscore commando
	public static final String HIGHSCORE = "highscore";
	
	/*
	 * Handshake voor de server. Moet altijd het eerste verzonden pakket zijn, met uitzondering van de errors
	 */
	public abstract void hello(String clientName, int supports, String version);
	
	/*
	 * Authenticatie van de client
	 */
	public abstract void auth(String signature);
	
	/*
	 * Maak een spel
	 */
	public abstract void createGame();
	
	/*
	 * Join een spel
	 */
	public abstract void joinGame(String creator);
	
	/*
	 * Start de game
	 */
	public abstract void startGame();
	
	/*
	 * Doe een zet
	 */
	public abstract void move(int x, int y);
	
	/*
	 * Stuur een bericht naar iedereen in de lobby of iedereen in het spel
	 */
	public abstract void message(String body);
	
	/*
	 * Daag een ander uit voor een spel
	 */
	public abstract void challenge(String other);
	
	/*
	 * Daag twee anderen uit voor een spel
	 */
	public abstract void challenge(String other1, String other2);
	
	/*
	 * Daag drie anderen uit voor een spel
	 */
	public abstract void challenge(String other1, String other2, String other3);
	
	/*
	 * Reageer op een uitdaging
	 */
	public abstract void challengeResponse(boolean accept);
	
	/*
	 * Vraag highscores op bij de server
	 */
	public abstract void highscore(String type, String arg);
	
}
