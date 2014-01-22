package rolit;

public class LeaderboardTest {
	
	//---------------------------------Variables---------------------------------
	private int errors;
	public static Leaderboard leaderboard;
	
	//---------------------------------The actual Test method---------------------------------
	
	//Method to run the test
	public int runTest(){
		System.out.println("Testclass" + this.getClass());
		setUp();
		testGetScorePerson();
		setUp();
		testGetAllPersonScore();
		setUp();
		testGetBestScorePerson();
		setUp();
		testGetTopNScore(3);
		setUp();
		testGetTopNScore(9);
		setUp();
		testGetAverageDayScore("Date");
		System.out.println("	Expected: 7");
		System.out.println(" ");
		setUp();
		testGetAverageDayScore("Niks");
		System.out.println("	Expected: Error");
		System.out.println(" ");
		setUp();
		testGetScoreOnePerson("Henk");
		setUp();
		testGetScoreOnePerson("Josje");
		setUp();
		testGetScoreAboveValue(10);
		setUp();
		testGetScoreAboveValue(9000);
		setUp();
		testGetScoreUnderValue(50);
		setUp();
		testGetScoreUnderValue(10);
		setUp();
		testGetSortedLeaderboard();
		setUp();
		testGetEmptySortedLeaderboard();
		setUp();
		if (errors == 0){
			System.out.println("	OKIDOKI!");
		}
		return errors;
	}
	
	//Set new leaderboard
	public static void setUp(){
		leaderboard = new Leaderboard();
		Leaderboard.reset();
	}
	
	//---------------------------------Tests---------------------------------
	
	//Tests if getScoreAboveValue returns all scores above the requested value
	public static void testGetScoreAboveValue(int value){
		System.out.println("Test getScoreAboveValue(" + value + "): ");
		leaderboard.addScorePerson("Henk", 9, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 11, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 10, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 13, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 8, "Date", "Time");
		System.out.println(leaderboard.getScoreAboveValue(value));
		System.out.println(" ");
	}
	
	//Tests if getScoreUnderValue returns all scores under the requested value
	public static void testGetScoreUnderValue(int value){
		System.out.println("Test getScoreUnderPerson(" + value + "): ");
		leaderboard.addScorePerson("Henk", 12, "Date", "Time");
		leaderboard.addScorePerson("Henk", 65, "Date", "Time");
		leaderboard.addScorePerson("Henk", 75, "Date", "Time");
		leaderboard.addScorePerson("Henk", 25, "Date", "Time");
		leaderboard.addScorePerson("Henk", 50, "Date", "Time");
		System.out.println(leaderboard.getScoreUnderValue(value));
		System.out.println(" ");
	}
	
	//Tests if getScoreOnePerson returns a list with all the scores of one person
	public static void testGetScoreOnePerson(String person){
		System.out.println("Test getScoreOnePerson(" + person + "): ");
		leaderboard.addScorePerson("Henk", 9000, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 9001, "Date", "Time");
		leaderboard.addScorePerson("Henk",9002, "Date","Time");
		leaderboard.addScorePerson("Gerwin", 9003, "Date", "Time");
		leaderboard.addScorePerson("Henk", 9004, "Date", "Time");
		System.out.println(leaderboard.getScoreOnePerson(person));
		System.out.println(" ");
	}
	
	//Tests if getAverageDayScore returns the average score of the requested day
	public static void testGetAverageDayScore(String askedDate){
		System.out.println("Test getAverageDayScore(" + askedDate + "):");
		leaderboard.addScorePerson("Henk", 2, "Date", "Time");
		leaderboard.addScorePerson("Piet", 10, "Date+1", "Time");
		leaderboard.addScorePerson("Gerwin", 10, "Date", "Time");
		leaderboard.addScorePerson("Henk", 12, "Date+1", "Time");
		leaderboard.addScorePerson("Gerwin", 9, "Date", "Time");
		System.out.println(leaderboard.getAverageDayScore(askedDate));
	}
	
	//Tests if getAllPersonScore returns a list of all the scores on the leaderboard
	public static void testGetAllPersonScore(){
		System.out.println("Test getAllPersonScore: ");
		leaderboard.addScorePerson("Henk", 9000, "Date", "Time+2");
		leaderboard.addScorePerson("Piet", 90002, "Date", "Time-1");
		leaderboard.addScorePerson("Grietje", 900, "Date", "Time+1");
		System.out.println(leaderboard.getAllPersonScore());
		System.out.println(" ");
	}
	
	//Tests if getScorePerson returns a list of the score of the last person added to the leaderboard
	public static void testGetScorePerson(){
		System.out.println("Test getScorePerson:");
		leaderboard.addScorePerson("Henk", 9000, "Date", "Time0");
		System.out.println(leaderboard.getScorePerson());
		System.out.println("Expected: " + "[Henk, 9000, Date, Time0]");
		System.out.println(" ");
	}
	
	//Tests if getBestScorePerson returns the best person on the leaderboard
	public static void testGetBestScorePerson(){
		System.out.println("Test getBestScorePerson");
		leaderboard.addScorePerson("Henk", 9000, "Date", "Time");
		leaderboard.addScorePerson("Josje", 9001, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 8999, "Date", "Time");
		leaderboard.addScorePerson("Piet", 9002, "Date", "Time");
		System.out.println(leaderboard.getBestScorePerson());
		System.out.println(" ");
	}
	
	//Tests if getTopNScore returns the top n off the leaderboard
	public static void testGetTopNScore(int n){
		System.out.println("Test getTopNScore(" + n + "):");
		leaderboard.addScorePerson("Henk", 9000, "Date", "Time");
		leaderboard.addScorePerson("Josje", 9001, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 8999, "Date", "Time");
		leaderboard.addScorePerson("Piet", 9002, "Date", "Time");
		leaderboard.getTopNScore(n);
		System.out.println(" ");
	}
	
	//Tests if getSortedLeaderboard the sorted leaderboard by score returns
	public static void testGetSortedLeaderboard(){
		System.out.println("Test getSortedLeaderboard:");
		leaderboard.addScorePerson("Henk", 8999, "Date", "Time");
		leaderboard.addScorePerson("Gerwin", 9001, "Date", "Time");
		leaderboard.addScorePerson("Piet", 9000, "Date", "Time");
		leaderboard.addScorePerson("Josje", 9002, "Date", "Time");
		System.out.println(leaderboard.getSortedLeaderboard());
		System.out.println(" ");
	}
	
	//Tests if getSortedLeaderboard an error returns when the leaderboard is empty
	public static void testGetEmptySortedLeaderboard(){
		System.out.println("Test getSortedLeaderboard with empty leaderboard: ");
		System.out.println(leaderboard.getSortedLeaderboard());
		System.out.println(" ");
	}
	
	
	//---------------------------------Main method--------------------------------
	
	//Makes the test run
	public static void main(String[] args) {
		LeaderboardTest test;
		test = new LeaderboardTest();
		test.runTest();
	}

}
