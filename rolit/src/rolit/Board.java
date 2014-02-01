package rolit;

import java.awt.Color;
import java.util.LinkedList;

public class Board {
	// start: 3,3 rood		3,4 geel	4,3 blauw		4,4 groen
	// col = x-as   en   row = y-as
	// 2 spelers: rood, groen
	// 3 spelers: rood, geel, groen
	
	
	// -- Constants --------------------------------------------------
	
	public static final int DIM = 8;
	
	// -- Instance variables -----------------------------------------
	
	private Mark1[] fields;
	LinkedList<LinkedList<Integer>> aanliggendeVelden = new LinkedList<LinkedList<Integer>>();
	LinkedList<LinkedList<Integer>> veldenMetSlaMogelijkheid = new LinkedList<LinkedList<Integer>>();
	LinkedList<LinkedList<Integer>> mogelijkeSet = new LinkedList<LinkedList<Integer>>();

	
	// -- Constructors -----------------------------------------------
	
	public Board(){
		fields = new Mark1[DIM * DIM];
		reset();
	}
	
	/*@
    requires 0 <= row & row < DIM;
    requires 0 <= col & col < DIM;
	*/
	// returnt een index voor de velden van boven naar beneden van links naar rechts
	public static int index (int col, int row){
		return DIM * col + row;
	}
	
	// returnt true als veld bestaat
	// returnt false als veld niet bestaat
	public boolean isField(int col, int row){
		return (0 <= row) && (row < DIM) && (0 <= col) && (col < DIM);
	}
	
	// returnt het mark dat bij het veld hoort
	public Mark1 getField(int col, int row){
		return fields[index(col, row)];
	}
	
	// returnt true als veld leeg is
	// returnt false als een veld een kleur heeft.
	public boolean isEmptyField(int col, int row){
		return getField(col, row) == Mark1.EMPTY;
	}
	
	// returnt true als het bord vol is
	// returnt false als bord niet vol is
	public boolean isFull(){
		boolean result = true;
		for (int col = 0; col < DIM; col++){
			for (int row = 0; row < DIM; row++){
				if (isEmptyField(col, row)){
					result = false;
				}
			}
		}
		return result;
	}
	
	public int aantalMark(Mark1 m){
		int aantalMark = 0;
		for (int col = 0; col < DIM; col++){
			for (int row = 0; row < DIM; row++){
				if(getField(col, row) == m){
					aantalMark++;
				}
			}
		}
		return aantalMark;
	}
	
	// returnt true als spel over is
	// returnt false als spel niet over is
	public boolean gameOver(){
		return isFull();
	}
	
	//create Color for coloring the fields
	public Color toColor(Mark1 mark){
		Color kleur = Color.BLACK;
			if (mark.equals(Mark1.ROOD)){
				kleur = Color.RED;
			}
			else if (mark.equals(Mark1.GEEL)){
				kleur = Color.YELLOW;
			}
			else if (mark.equals(Mark1.GROEN)){
				kleur = Color.GREEN;
			}
			else if (mark.equals(Mark1.BLAUW)){
				kleur = Color.BLUE;
			}
			else if (mark.equals(Mark1.EMPTY)){
				kleur = Color.BLACK;
			}
		return kleur;
	}
	
	public String toString(Mark1 mark){
		String stringMark = null;
		if (mark.equals(Mark1.ROOD)){
			stringMark = "ROOD";
		}
		else if (mark.equals(Mark1.GEEL)){
			stringMark = "GEEL";
		}
		else if (mark.equals(Mark1.GROEN)){
			stringMark = "GROEN";
		}
		else if (mark.equals(Mark1.BLAUW)){
			stringMark = "BLAUW";
		}
		else if (mark.equals(Mark1.EMPTY)){
			stringMark = "EMPTY";
		}
		return stringMark;
	}
	
	
	public boolean isWinner(Mark1 m){
		boolean winner = false;
		int aantalRood = aantalMark(Mark1.ROOD);
		int aantalGeel = aantalMark(Mark1.GEEL);
		int aantalGroen = aantalMark(Mark1.GROEN);
		int aantalBlauw = aantalMark(Mark1.BLAUW);
		if(isFull()){
			if(m.equals(Mark1.ROOD) && aantalRood >= aantalGeel && aantalRood >= aantalGroen && aantalRood >= aantalBlauw){
				winner = true;
			}
			if(m.equals(Mark1.GEEL) && aantalGeel >= aantalRood && aantalGeel >= aantalGroen && aantalGeel >= aantalBlauw){
				winner = true;
			}
			if(m.equals(Mark1.GROEN) && aantalGroen >= aantalRood && aantalGroen >= aantalGeel && aantalGroen >= aantalBlauw){
				winner = true;
			}
			if(m.equals(Mark1.BLAUW) && aantalBlauw >= aantalRood && aantalBlauw >= aantalGeel && aantalBlauw >= aantalGroen){
				winner = true;
			}	
		} 
		return winner;
	}
	
	public String getWinner(){
		LinkedList<String> winnersList = new LinkedList<String>();
		String winner1 = null;
		String winners = null;
		if(hasWinner()){
			if (isWinner(Mark1.ROOD)){
				winnersList.add("ROOD");
			}
			if (isWinner(Mark1.GEEL)){
				winnersList.add("GEEL");
			}
			if (isWinner(Mark1.GROEN)){
				winnersList.add("GROEN");
			}
			if (isWinner(Mark1.BLAUW)){
				winnersList.add("BLAUW");
			}
		}
		
		winner1 = winnersList + "is the winner!";
		winners = winnersList + "are the winners!";
		
		if (winnersList.size() > 1){
			winner1 = winners;
		}
		
		return winner1;
	}
	
	public boolean hasWinner(){
		return isWinner(Mark1.ROOD) || isWinner(Mark1.GEEL) || isWinner(Mark1.GROEN) || isWinner(Mark1.BLAUW);
	}
	
	// returnt true als gekozen veld aanliggend is en in dien mogelijk slaat
	// returnt false wanneer gekozen veld
	public boolean isValidMove(int col, int row, Mark1 m){
		boolean validMove = false;
		activeFields(m);
		for (int i = 0; i < veldenMetSlaMogelijkheid.size(); i++){
			LinkedList<Integer> colRow = new LinkedList<Integer>();
			colRow = veldenMetSlaMogelijkheid.get(i);
			int x = colRow.get(0);
			int y = colRow.get(1);
			if (col == x && row == y){
				validMove = true;
			}
		}
		return !isFull() && isEmptyField(col, row) && validMove ;
	}
	
	// herstelt veld naar beginpositie
	public void reset(){
		for (int col = 0; col < DIM; col++){
			for (int row = 0; row < DIM; row++){
				setField(col, row, Mark1.EMPTY);
			}
		}
		setField(3, 3, Mark1.ROOD);
		setField(3, 4, Mark1.GEEL);
		setField(4, 3, Mark1.BLAUW);
		setField(4, 4, Mark1.GROEN);
	}

	public void setField(int col, int row, Mark1 m){
		fields[index(col, row)] = m;
	}

	public LinkedList<LinkedList<Integer>> activeFields(Mark1 m){
		while (!aanliggendeVelden.isEmpty()) {
	        aanliggendeVelden.removeFirst();
	    }
		while (!veldenMetSlaMogelijkheid.isEmpty()) {
			veldenMetSlaMogelijkheid.removeFirst();
		}
		boolean aanliggend = false;
		boolean moetSlaan = false;
		boolean empty = false;
		for (int x = 0; x < DIM; x++){
			for (int y = 0; y < DIM; y++){
				if (isEmptyField(x, y)){
					//check veld erboven:
					if(y > 0 && !isEmptyField(x, y - 1)){
						aanliggend = true;
					}
					//check veld eronder:
					else if (y < DIM - 1 && !isEmptyField(x, y + 1)){
						aanliggend = true;
					}
					//check veld links:
					else if (x > 0 &&!isEmptyField(x - 1, y)){
						aanliggend = true;
					}
					//check veld rechts
					else if (x < DIM - 1 && !isEmptyField(x + 1, y)){
						aanliggend = true;
					}
					//check veld links boven
					else if (x > 0 && y > 0 && !isEmptyField(x - 1, y - 1)){
						aanliggend = true;
					}
					//check veld links onder
					else if (x > 0 && y < DIM - 1 && !isEmptyField(x - 1, y + 1)){
						aanliggend = true;
					}
					//check veld rechts boven
					else if (y > 0 && x < DIM - 1 && !isEmptyField(x + 1, y - 1)){
						aanliggend = true;
					}
					//check veld rechts onder
					else if (y < DIM - 1 && x < DIM - 1 && !isEmptyField(x + 1, y + 1)){
						aanliggend = true;
					}
					if (aanliggend == true){
						aanliggend = false;
						LinkedList<Integer> xy = new LinkedList<Integer>();
						xy.add(x);
						xy.add(y);
						aanliggendeVelden.add(xy);
						
						
						// kijken of er ergens boven een veld met mijn kleur aanwezig is
						if (y > 1 && !isEmptyField(x, y - 1) && getField(x, y - 1) != m){
							for (int row = y - 2; row >= 0 && !empty; row = row - 1){
								if(getField(x, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(x, row)){
									empty = true;
								}
							}
						}
						
						// kijken of ergens naar beneden een veld met mijn kleur aanwezig is
						empty = false;
						if (y < DIM - 2 && !isEmptyField(x, y + 1) && getField(x, y + 1) != m){
							for (int row = y + 2; row < DIM && !empty; row++){
								if (getField(x, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(x, row)){
									empty = true;
								}
							}
						}
						empty = false;
						// kijken of ergens naar links een veld met mijn kleur aanwezig is
						if (x > 1 && !isEmptyField(x - 1, y) && getField(x - 1, y) != m){
							for (int col = x - 2; col >= 0 && !empty; col = col - 1){
								if (getField(col, y) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, y)){
									empty = true;
								}
							}
						}
						empty = false;
						// kijken of ergens naar rechts een veld met mijn kleur aanwezig is
						if (x < DIM - 2 && !isEmptyField(x + 1, y) && getField(x + 1, y) != m){
							for (int col = x + 2; col < DIM && !empty; col++){
								if (getField(col, y) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, y)){
									empty = true;
								}
							}
						}
						empty = false;
						// naar links boven
						if (x > 1 && y > 1 && !isEmptyField(x - 1, y - 1) && getField(x - 1, y - 1) != m){
							int row = y - 2;
							for (int col = x - 2; col >= 0 && !empty && row >= 0; col = col - 1){
								if (getField(col, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, row)){
									empty = true;
								}
								row = row - 1;
							}
						}
						empty = false;
						// naar links onder
						if (x > 1 && y < DIM - 2 && !isEmptyField(x - 1, y + 1) && getField(x - 1, y + 1) != m){
							int row = y + 2;
							for (int col = x - 2; col >= 0 && !empty && row < DIM; col--){
								
								if (getField(col, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, row)){
									empty = true;
								}
								row++;
							}
						}
						empty = false;
						//naar rechts boven
						if (x < DIM - 2 && y > 1 && !isEmptyField(x + 1, y - 1) && getField(x + 1, y - 1) != m){
							int row = y - 2;
							for (int col = x + 2; col < DIM && !empty && row >= 0; col++){
								
								if (getField(col, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, row)){
									empty = true;
								}
								row--;
							}
						}
						empty = false;
						// naar rechts onder
						if (x < DIM - 2 && y < DIM - 2 && !isEmptyField(x + 1, y + 1) && getField(x + 1, y + 1) != m){
							int row = y + 2;
							for (int col = x + 2; col < DIM && !empty && row < DIM; col++){
							
								if (getField(col, row) == m){
									moetSlaan = true;
								}
								else if(isEmptyField(col, row)){
									empty = true;
								}
								row++;
								
							}
						}
						empty = false;
						if (moetSlaan == true){
							moetSlaan = false;
							LinkedList<Integer> xy1 = new LinkedList<Integer>();
							xy1.add(x);
							xy1.add(y);
							veldenMetSlaMogelijkheid.add(xy1);
						}
					}
				}
			}
		}
		if (veldenMetSlaMogelijkheid.size() == 0){
			mogelijkeSet = aanliggendeVelden;
		}
		else{ 
			mogelijkeSet = veldenMetSlaMogelijkheid;
		}
		return  mogelijkeSet;
	}	
	
	public void recolorFields(int x, int y, Mark1 m){
		boolean empty = false;
		boolean maakSlag = false;
		// kijken of er ergens boven een veld met mijn kleur aanwezig is
		if (y > 1 && !isEmptyField(x, y - 1) && getField(x, y - 1) != m){
			int row = y - 2;
			for (row = y - 2; row >= 0 && !empty && !maakSlag; row = row - 1){
				if(getField(x, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(x, row)){
					empty = true;
				}
			}
			if (maakSlag && !empty){
				for(int row1 = y; row1 > row && !empty; row1 = row1 - 1){
					setField(x, row1, m);
				}
			}
		}	
		empty = false;
		maakSlag = false;
		// kijken of ergens naar beneden een veld met mijn kleur aanwezig is
				
		if (y < DIM - 2 && !isEmptyField(x, y + 1) && getField(x, y + 1) != m){
			int row = y + 2;
			for (row = y + 2; row < DIM && !empty && !maakSlag; row++){
				if (getField(x, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(x, row)){
					empty = true;
				}
			}
			if (maakSlag && !empty){
				for(int row1 = y; row1 < row && !empty; row1++){
					setField(x, row1, m);
				}
			}
		}
					
		empty = false;
		maakSlag = false;
		// kijken of ergens naar links een veld met mijn kleur aanwezig is
		if (x > 1 && !isEmptyField(x - 1, y) && getField(x - 1, y) != m){
			int col = x - 2;
			for (col = x - 2; col >= 0 && !empty && !maakSlag; col = col - 1){
				if (getField(col, y) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, y)){
					empty = true;
				}
			}
			if (maakSlag && !empty){
				for(int col1 = x; col1 > col && !empty; col1 = col1 - 1){
					setField(col1, y, m);
				}
			}
		}
					
		empty = false;
		maakSlag = false;
		// kijken of ergens naar rechts een veld met mijn kleur aanwezig is
		if (x < DIM - 2 && !isEmptyField(x + 1, y) && getField(x + 1, y) != m){
			int col = x + 2;
			for (col = x + 2; col < DIM && !empty && !maakSlag; col++){
				if (getField(col, y) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, y)){
					empty = true;
				}
			}
			if (maakSlag && !empty){
				for(int col1 = x; col1 < col && !empty; col1++){
					setField(col1, y, m);
				}
			}
		}
			
		empty = false;
		maakSlag = false;
		// naar links boven
		if (x > 1 && y > 1 && !isEmptyField(x - 1, y - 1) && getField(x - 1, y - 1) != m){
			int col = x - 2;
			int row = y - 2;
			for (col = x - 2; col >= 0 && !empty && !maakSlag; col = col - 1){
				if (getField(col, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, row)){
					empty = true;
				}
				row = row - 1;
			}
			if (maakSlag && !empty){
				int row1 = y;
				for(int col1 = x; col1 > col && !empty; col1 = col1 - 1){
					setField(col1, row1, m);
					row1 = row1 - 1;
				}
			}
		}
		
		empty = false;
		maakSlag = false;
		// naar links onder
		if (x > 1 && y < DIM - 2 && !isEmptyField(x - 1, y + 1) && getField(x - 1, y + 1) != m){
			int col = x - 2;
			int row = y + 2;
			for (col = x - 2; col >= 0 && !empty && !maakSlag && row < DIM; col--){
				if (getField(col, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, row)){
					empty = true;
				}
				row++;
			}
			if (maakSlag && !empty){
				int row1 = y;
				for(int col1 = x; col1 > col && !empty; col1--){
					setField(col1, row1, m);
					row1++;
				}
			}
		}
		empty = false;
		maakSlag = false;
		//naar rechts boven
		if (x < DIM - 2 && y > 1 && !isEmptyField(x + 1, y - 1) && getField(x + 1, y - 1) != m){
			int col = x + 2;
			int row = y - 2;
			for (col = x + 2; col < DIM && !empty && !maakSlag; col++){
				if (getField(col, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, row)){
					empty = true;
				}
				row--;
			}
			if (maakSlag){
				int row1 = y;
				for(int col1 = x; col1 < col && !empty; col1++){
					setField(col1, row1, m);
					row1--;
				}
			}
		}
					
		empty = false;
		maakSlag = false;
		// naar rechts onder
		if (x < DIM - 2 && y < DIM - 2 && !isEmptyField(x + 1, y + 1) && getField(x + 1, y + 1) != m){
			int col = x + 2;
			int row = y + 2;
			for (col = x + 2; col < DIM && !empty && !maakSlag && row < DIM; col++){
				if (getField(col, row) == m){
					maakSlag = true;
				}
				else if(isEmptyField(col, row)){
					empty = true;
				}
				row++;
			}
			if (maakSlag && !empty){
				int row1 = y;
				for(int col1 = x; col1 < col && !empty; col1++){
					setField(col1, row1, m);
					row1++;
				}
			}
		}
		
		empty = false;
		maakSlag = false;
	}
}
