package rolit;

import java.util.LinkedList;

public class DeBug {
	
	public static final int DIM = 8;

	private static Mark[] fields;
	static LinkedList<LinkedList<Integer>> aanliggendeVelden = new LinkedList<LinkedList<Integer>>();
	static LinkedList<LinkedList<Integer>> veldenMetSlaMogelijkheid = new LinkedList<LinkedList<Integer>>();
	
	public void Board(){
		fields = new Mark[DIM * DIM];
		reset();
	}
	
	// herstelt veld naar beginpositie
	public void reset(){
		for (int col = 0; col < DIM; col++){
			for (int row = 0; row < DIM; row++){
				setField(col, row, Mark.EMPTY);
			}
		}
		setField(3, 3, Mark.ROOD);
		setField(4, 3, Mark.GEEL);
		setField(3, 4, Mark.GROEN);
		setField(4, 4, Mark.BLAUW);
	}
	
	public static void setField(int i, Mark m){
		fields[i] = m;
	}
	public static void setField(int col, int row, Mark m){
		setField(index(col, row),m);
	}
	
	public static int index (int col, int row){
		return DIM * col + row;
	}
	
	public static Mark getField(int col, int row){
		return fields[index(col, row)];
	}
	
	public static boolean isEmptyField(int col, int row){
		return getField(col, row) == Mark.EMPTY;
	}
	
	

	public static LinkedList<LinkedList<Integer>> lijstje(int x, int y, Mark m){
		while (!aanliggendeVelden.isEmpty()) {
	        aanliggendeVelden.removeFirst();
	    }
		Board b = new Board();
		b.reset();
		boolean empty = false;
		boolean maakSlag = false;
		//if(isValidMove(x, y, m)){
			// kijken of er ergens boven een veld met mijn kleur aanwezig is
			if (y > 1 && !b.isEmptyField(x, y - 1) && b.getField(x, y - 1) != m){
				int row = y - 2;
				for (row = y - 2; row >= 0 && !empty && !maakSlag; row = row - 1){
					if(b.getField(x, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(x, row)){
						empty = true;
					}
				}
				if (maakSlag && !empty){
					for(int row1 = y; row1 > row && !empty; row1 = row1 - 1){
						b.setField(x, row1, m);
						LinkedList<Integer> xy = new LinkedList<Integer>();
						xy.add(x);
						xy.add(row1);
						aanliggendeVelden.add(xy);
					}
				}
			}
			
			// kijken of ergens naar beneden een veld met mijn kleur aanwezig is
			empty = false;
			maakSlag = false;
			if (y < DIM - 2 && !b.isEmptyField(x, y + 1) && b.getField(x, y + 1) != m){
				int row = y + 2;
				for (row = y + 2; row < DIM && !empty && !maakSlag; row++){
					if (b.getField(x, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(x, row)){
						empty = true;
					}
				}
				if (maakSlag && !empty){
					for(int row1 = y; row1 < row && !empty; row1++){
						b.setField(x, row1, m);
						LinkedList<Integer> xy = new LinkedList<Integer>();
						xy.add(x);
						xy.add(row1);
						aanliggendeVelden.add(xy);
					}
				}
			}
			
			empty = false;
			maakSlag = false;
			// kijken of ergens naar links een veld met mijn kleur aanwezig is
			if (x > 1 && !b.isEmptyField(x - 1, y) && b.getField(x - 1, y) != m){
				int col = x - 2;
				for (col = x - 2; col >= 0 && !empty && !maakSlag; col = col - 1){
					if (b.getField(col, y) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, y)){
						empty = true;
					}
				}
				if (maakSlag && !empty){
					for(int col1 = x; col1 > col && !empty; col1 = col1 - 1){
						b.setField(col1, y, m);
						LinkedList<Integer> xy = new LinkedList<Integer>();
						xy.add(col1);
						xy.add(y);
						aanliggendeVelden.add(xy);
					}
				}
			}
			
			empty = false;
			maakSlag = false;
			// kijken of ergens naar rechts een veld met mijn kleur aanwezig is
			if (x < DIM - 2 && !b.isEmptyField(x + 1, y) && b.getField(x + 1, y) != m){
				int col = x + 2;
				for (col = x + 2; col < DIM && !empty && !maakSlag; col++){
					if (b.getField(col, y) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, y)){
						empty = true;
					}
				}
				if (maakSlag && !empty){
					for(int col1 = x; col1 < col && !empty; col1++){
						b.setField(col1, y, m);
						LinkedList<Integer> xy = new LinkedList<Integer>();
						xy.add(col1);
						xy.add(y);
						aanliggendeVelden.add(xy);
					}
				}
			}
			
			empty = false;
			maakSlag = false;
			// naar links boven
			if (x > 1 && y > 1 && !b.isEmptyField(x - 1, y - 1) && b.getField(x - 1, y - 1) != m){
				int col = x - 2;
				int row = y - 2;
				for (col = x - 2; col >= 0 && !empty && !maakSlag; col = col - 1){
					if (b.getField(col, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, row)){
						empty = true;
					}
					row = row - 1;
				}
				if (maakSlag && !empty){
					int row1 = y;
					for(int col1 = x; col1 > col && !empty; col1 = col1 - 1){
							b.setField(col1, row1, m);
							LinkedList<Integer> xy = new LinkedList<Integer>();
							xy.add(col1);
							xy.add(row1);
							row1 = row1 - 1;
							aanliggendeVelden.add(xy);
						
					}
				}
			}
			empty = false;
			maakSlag = false;
			// naar links onder
			if (x > 1 && y > 1 && !b.isEmptyField(x - 1, y + 1) && b.getField(x - 1, y + 1) != m){
				int col = x - 2;
				int row = y + 2;
				for (col = x - 2; col >= 0 && !empty && !maakSlag; col = col - 1){
					if (b.getField(col, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, row)){
						empty = true;
					}
					row = row + 1;
				}
				if (maakSlag && !empty){
					int row1 = y;
					for(int col1 = x; col1 > col && !empty; col1 = col1 - 1){
							b.setField(col1, row1, m);
							LinkedList<Integer> xy = new LinkedList<Integer>();
							xy.add(col1);
							xy.add(row1);
							row1 = row1 + 1;
							aanliggendeVelden.add(xy);
						
					}
				}
			}
			empty = false;
			maakSlag = false;
			//naar rechts boven
			if (x > 1 && y > 1 && !b.isEmptyField(x + 1, y - 1) && b.getField(x + 1, y - 1) != m){
				int col = x + 2;
				int row = y - 2;
				for (col = x + 2; col < DIM && !empty && !maakSlag; col = col + 1){
					if (b.getField(col, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, row)){
						empty = true;
					}
					row = row - 1;
				}
				if (maakSlag && !empty){
					int row1 = y;
					for(int col1 = x; col1 < col && !empty; col1++){
							b.setField(col1, row1, m);
							LinkedList<Integer> xy = new LinkedList<Integer>();
							xy.add(col1);
							xy.add(row1);
							row1 = row1 - 1;
							aanliggendeVelden.add(xy);
						
					}
				}
			}
			
			empty = false;
			maakSlag = false;
			// naar rechts onder
			if (x > 1 && y > 1 && !b.isEmptyField(x + 1, y + 1) && b.getField(x + 1, y + 1) != m){
				int col = x + 2;
				int row = y + 2;
				for (col = x + 2; col < DIM && !empty && !maakSlag; col++){
					if (b.getField(col, row) == m){
						maakSlag = true;
					}
					else if(b.isEmptyField(col, row)){
						empty = true;
					}
					row = row + 1;
				}
				if (maakSlag && !empty){
					int row1 = y;
					for(int col1 = x; col1 < col && !empty; col1++){
							b.setField(col1, row1, m);
							LinkedList<Integer> xy = new LinkedList<Integer>();
							xy.add(col1);
							xy.add(row1);
							row1 = row1 + 1;
							aanliggendeVelden.add(xy);
						
					}
				}
			//}
			empty = false;
			maakSlag = false;
		}
	return aanliggendeVelden;
	}
	
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lijstje(3, 5, Mark.ROOD));
		System.out.println(lijstje(4, 2, Mark.GROEN));
		System.out.println(lijstje(2, 3, Mark.GEEL));
		System.out.println(lijstje(5, 3, Mark.ROOD));
		
		System.out.println(lijstje(2, 2, Mark.GROEN));
		System.out.println(lijstje(2, 5, Mark.GEEL));
		System.out.println(lijstje(5, 2, Mark.BLAUW));
		System.out.println(lijstje(5, 5, Mark.ROOD));
		

	}

}

