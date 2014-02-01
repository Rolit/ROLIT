package leaderboard;

import java.util.LinkedList;

public class Leaderboard {
	
	//---------------------------------Variables---------------------------------
	
	private static LinkedList<LinkedList<Object>> allPersonScore = new LinkedList<LinkedList<Object>>();
	private static LinkedList<Object> personScore;
	//public static String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//---------------------------------Queries---------------------------------
	
	/**Get a list of all the scores of one person
	* @return list of all the scores of the requested person
	* @return empty list if requested person is not found
	*/
	public LinkedList<LinkedList<Object>> getScoreOnePerson(String person){
		LinkedList<LinkedList<Object>> scoreOnePerson = new LinkedList<LinkedList<Object>>();
		int n = allPersonScore.size();
		for(int i = 0; i < n; i++){
			LinkedList<Object> personDay = allPersonScore.get(i);
			String personName = (String)personDay.get(0);
			if(person.equals(personName)){
				scoreOnePerson.add(personDay);
			}
		}
		if(scoreOnePerson.size() == 0){
			System.out.println("Error: Person not found");
			return scoreOnePerson;
		}
		else{
			return scoreOnePerson;
		}
	}
	
	/**Get the list of the top n persons on the leaderboard
	 * @return one score at a time, from the first one up untill the number n in the list.
	 * @return all scores if n > bestNOfAll.size()
	 */
	public void getTopNScore(int n){
		LinkedList<LinkedList<Object>> bestNOfAll = sortLeaderboard(allPersonScore);
		if(n > bestNOfAll.size()){
			System.out.println("Error: List to short");
			System.out.println("The whole leaderboard");
			System.out.println(bestNOfAll);
		}
		else{
			for(int i = 0; i < n; i++){
				int h = i + 1;
				System.out.println(h + ": " + bestNOfAll.get(i));
			}	
		}
		
	}
	
	/**Get the average score of the requested day
	 * @return an integer representing the average score of the requested day
	 * @return 0 and an error if the requested day is not found
	 */
	public int getAverageDayScore(String askedDate){
		LinkedList<LinkedList<Object>> dayScore = new LinkedList<LinkedList<Object>>();
		int totalScore = 0;
		int averageScore = 0;
		int n = allPersonScore.size();
		for(int i = 0; i < n; i++){
			LinkedList<Object> personDay = allPersonScore.get(i);
			String currentDate = (String)personDay.get(2);
			if(askedDate.equals(currentDate)){
				dayScore.add(personDay);
				totalScore = totalScore + (int)personDay.get(1);
			}
		}
		if(dayScore.size() == 0){
			System.out.println("Error: Date not found");
			return 0;
		}
		else{
			averageScore = totalScore/dayScore.size();
			System.out.print("Average Score of " + askedDate + " is: ");
			return averageScore;
		}
	}
	
	/**Get list of scores above a certain value
	 * @return a list of all the scores above a certain value
	 * @return an empty list if there a no scores above the requested value
	 */
	public LinkedList<LinkedList<Object>> getScoreAboveValue(int value){
		LinkedList<LinkedList<Object>> sortedLeaderboard = sortLeaderboard(allPersonScore);
		LinkedList<LinkedList<Object>> scoresAboveValue = new LinkedList<LinkedList<Object>>();
		int n = sortedLeaderboard.size();
		for(int i = 0; i < n; i++){
			LinkedList<Object> oneScore = sortedLeaderboard.get(i);
			int score = (int)oneScore.get(1);
			if(score > value){
				scoresAboveValue.add(oneScore);
			}
		}
		if(scoresAboveValue.size() == 0){
			System.out.println("No scores above " + value);
			return scoresAboveValue;
		}
		else{
			return scoresAboveValue;
		}
	}
	
	/**Get list of scores under a certain value
	 * @return a list of the scores below a certain value
	 * @return an empty list if there a no scores below the requested value
	 */
	public LinkedList<LinkedList<Object>> getScoreUnderValue(int value){
		LinkedList<LinkedList<Object>> sortedLeaderboard = sortLeaderboard(allPersonScore);
		LinkedList<LinkedList<Object>> scoresUnderValue = new LinkedList<LinkedList<Object>>();
		int n = sortedLeaderboard.size();
		for(int i = 0; i < n; i++){
			LinkedList<Object> oneScore = sortedLeaderboard.get(i);
			int score = (int)oneScore.get(1);
			if(score < value){
				scoresUnderValue.add(oneScore);
			}
		}
		if(scoresUnderValue.size() == 0){
			System.out.println("No scores under " + value);
			return scoresUnderValue;
		}
		else{
			return scoresUnderValue;

		}
	}
		
	/**Get the number 1 player on the leaderboard
	 * @return the number 1 player on the leaderboard
	 */
	public String getBestScorePerson(){
		LinkedList<LinkedList<Object>> bestOfPersonScore = sortLeaderboard(allPersonScore);
		return bestOfPersonScore.get(0).toString();
	}
	
	/**Get the score of the last person added to the leaderboard
	 * @return the score and name of the last person added to the leaderboard
	 */
	public String getScorePerson(){
		return "Added Person Score: " +  personScore.toString();
	}
	
	/**Get the list of all scores on the leaderboard
	 * @return the leaderboard as a list of lists
	 */
	public String getAllPersonScore(){
		return allPersonScore.toString();
	}
	
	/**Get the leaderboard sorted by score
	 * @return a list of all the scores on the leaderboard sorted by score from high to low
	 * @return an error if the leaderboard is empty
	 */
	public String getSortedLeaderboard(){
		if(allPersonScore.size() == 0){
			return "Error: no scores found on leaderboard";
		}
		else{
			LinkedList<LinkedList<Object>> sortedLeaderboard = sortLeaderboard(allPersonScore);
			return sortedLeaderboard.toString();
		}
		
	}
	
	
	//---------------------------------Commands---------------------------------
	
	/**
	 * Adds a person to the Leaderboard 
	 */
	public void addScorePerson(String name, int score, String date, String time){
		LinkedList<Object> scorePerson = new LinkedList<Object>();
		personScore = scorePerson;
		scorePerson.add(name);
		scorePerson.add(score);
		scorePerson.add(date);
		scorePerson.add(time);
		LinkedList<Object> copyList = scorePerson;
		allPersonScore.add(copyList);
	}
	
	/**
	 * Resets the leaderboard by emptying allPersonScore
	 */
	public static void reset(){
		while(!allPersonScore.isEmpty()){
			allPersonScore.removeFirst();
		}
	}
	
	/**
	 * Sorts the leaderboard by score from high to low
	 */
	public static LinkedList<LinkedList<Object>> sortLeaderboard(LinkedList<LinkedList<Object>> list){
		LinkedList<LinkedList<Object>> copyLeaderboard = list;
		LinkedList<Object> highOfList = new LinkedList<Object>();
		LinkedList<LinkedList<Object>> sortedScore = new LinkedList<LinkedList<Object>>();
		while(copyLeaderboard.size() > 0){
			int highScore = 0;
			int indexOfHighScore = 0;
			for(int i = 0; i < copyLeaderboard.size(); i++){
				LinkedList<Object> scoreI = list.get(i);
				int testScore = (int) scoreI.get(1);
				if(testScore > highScore){
					indexOfHighScore = i;
					highScore = testScore;
					highOfList = scoreI;
				}
			}
			sortedScore.add(highOfList);
			copyLeaderboard.remove(indexOfHighScore);
		}
		return sortedScore;
	}
}
