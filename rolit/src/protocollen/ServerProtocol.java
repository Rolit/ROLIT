package protocollen;

import protocollen.StandardProtocol;

import java.io.IOException;

//Abstract class met alle constanten en methodes die gebruikt kunnen worden

public abstract class ServerProtocol extends StandardProtocol {
	//Constante voor het handshake commando
	public static final String HANDSHAKE = "hello";
	
	//Constante voor het authOk commando
	public static final String AUTH_OK = "authOk";
	
	//Constante voor het error commando
	public static final String ERROR = "error";
	
	//Constante voor het game commando
	public static final String GAME = "game";
	
	//Constante voor het start commando
	public static final String START = "start";
	
	//Constante voor het move commando
	public static final String MOVE = "move";
	
	//Constante voor het move-done commando
	public static final String MOVE_DONE = "moveDone";
	
	//Constante voor het game-over commando
	public static final String GAME_OVER = "gameOver";
	
	//Constante voor het message commando
	public static final String MESSAGE = "message";
	
	//Constante voor het challenge commando
	public static final String CHALLENGE = "challenge";
	
	//Constante voor het challenge-response commando
	public static final String CHALLENGE_RESPONSE = "challengeResponse";
	
	//Constante voor het can-be-challenged commando
	public static final String CAN_BE_CHALLENGED = "canBeChallenged";
	
	//Constante voor online
	public static final String ONLINE = "online";
	
	//Constante voor highscore
	public static final String HIGHSCORE = "highscore";
	
	//Error declaratie
	public static final int ERROR_GENERIC = -1;
	public static final int ERROR_INVALID_LOGIN = 1;
	public static final int ERROR_GAME_FULL = 2;
	public static final int ERROR_TOO_LITTLE_PLAYERS = 3;
	public static final int ERROR_INVALID_MOVE = 4;
	public static final int ERROR_NO_SUCH_GAME = 5;
	public static final int ERROR_USER_HAS_NO_GAME = 6;
	public static final int ERROR_HANDSHAKE_MISSING = 7;
	public static final int ERROR_USER_ALREADY_HAS_GAME = 8;
	
	public static final int HIGHSCORE_UNAVAILABLE = -1;
	
	//Status om aan te geven dat de creator voortijdig is weggegaan
	public static final int STATUS_PREMATURE_LEAVE = -1;
	
	//Status om aan te geven dat het spel nog niet gestart is
	public static final int STATUS_NOT_STARTED = 0;
	
	//Status om aan te geven dat het spel gestart is
	public static final int STATUS_STARTED = 1;
	
	//Antwoord op de handshake van de client. Moet altijd eerst verzonden commando zijn, met uitzondering van errors
	public abstract void handshake(int supports, String version) throws IOException;
	//Of
	public abstract void handshake(int supports, String version, String nonce) throws IOException;
	
	//Antwoord op het auth-pakket van de client
	public abstract void authOk() throws IOException;
	
	//Commando om de client te laten weten dat hij iets fout heeft gedaan, waardoor de verbinding wordt verbroken
	public abstract void error(int errorCode) throws IOException;
	
	/*
	 * Commando om de client te laten weten dat er of een nieuw spel is, of dat er een spel is veranderd in status.
	 * Clients krijgen een serie van deze commando's na de handshake om zo een lijst van alle spellen op te bouwen
	 * Als er daarna iets verandert aan het aantal spelers  of dat het spel is begonnen moet de server weer een update sturen 
	 */
	public abstract void game(String creator, int status, int noPlayers) throws IOException;
	
	//Commando om een spel te starten met twee spelers, die in die volgorde een zet moeten doen
	public abstract void start(String playerOne, String playerTwo) throws IOException;
	
	//Commando om een spel te starten met drie spelers, die in die volgorde een zet moeten doen
	public abstract void start(String playerOne, String playerTwo, String playerThree) throws IOException;
	
	//Commando om een spel te starten met vier speler, die in die volgorde een zet moeten doen
	public abstract void start(String playerOne, String playerTwo, String playerThree, String playerFour) throws IOException;
	
	//Commando om de client te vertellen dat hij een zet moet gaan doen
	public abstract void move() throws IOException;
	
	//Commando om de client te laten weten dat iemand een zet heeft gedaan in het huidige spel
	public abstract void moveDone(String name, int x, int y) throws IOException;
	
	/*
	 * Commando om de client te laten weten dat het spel is afgelopen, om welke reden dan ook,.
	 * Eventueel zijn er winnaars als het spel helemaal is voltooid. De server mag bepalen wat er gebeurt als er meerdere mense dezelfde score hebben
	 */
	public abstract void gameOver(int score, String[] winners) throws IOException;
	
	//Commando om de client op de hoogte te stellen van een chatbericht
	public abstract void message(String name, String body) throws IOException;
	
	//Commando om de client op de hoogte te stellen van een uitdaging met twee mensen
	public abstract void challenge(String challenger, String oter1) throws IOException;
	
	//Commando om de client op de hoogte te stellen van een uitdagen met drie mensen
	public abstract void challenge(String challenger, String other1, String other2) throws IOException;
	
	//Commando om de client op de hoogte te stellen van een uitdaging met vier mensen
	public abstract void challenge(String challenger, String other1, String other2, String other3) throws IOException;
	
	//Commando om mensen die in een uitdaging zitten op de hoogte te stellen van de status van de uitgedaagden
	public abstract void challengeResponse(String name, boolean accept) throws IOException;
	
	//Commando om de client op de hoogte te stellen van de gevraagde highscores
	public abstract void highscore(String[] args) throws IOException;
	
	//Commando om de client op de hoogte te stellen van een gebruiker die inlogt of weggaat
	public abstract void online(String name, boolean isOnline) throws IOException;
	
}


