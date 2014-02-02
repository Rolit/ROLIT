package rolit;

public enum Mark1 {
	
	EMPTY, ROOD, GEEL, GROEN, BLAUW;
	
	/*@
	 * if(x == 4);
     * ensures this == Mark.ROOD ==> \result == Mark.GEEL;
     * ensures this == Mark.GEEL ==> \result == Mark.GROEN;
     * ensures this == Mark.GROEN ==> \result == Mark.BLAUW;
     * ensures this == Mark.BLAUW ==> \result == Mark.ROOD;
     * ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     * 
     * if(x == 3);
     * ensures this == Mark.ROOD ==> \result == Mark.GEEL;
     * ensures this == Mark.GEEL ==> \result == Mark.GROEN;
     * ensures this == Mark.GROEN ==> \result == Mark.ROOD;
     * ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
     * 
     * if(x == 2);
     * ensures this == Mark.ROOD ==> \result == Mark.GROEN;
     * ensures this == Mark.GROEN ==> \result == Mark.ROOD;
     * ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
  */
	
	public Mark1 next(int i) {
		Mark1 nextColor = EMPTY;
		if (this == ROOD){
			if (i == 4 || i == 3){
				nextColor = GEEL; 
			}
			else if (i == 2){
				nextColor = GROEN;
			}
		}
		else if (this == GEEL){
				nextColor = GROEN;
		}
		else if (this == GROEN){
			if(i == 4){
				nextColor = BLAUW;
			}
			else if(i == 3 || i == 2){
				nextColor = ROOD;
			}	
		}
		else if (this == BLAUW){
			nextColor = ROOD;
		}
		else{
			nextColor = EMPTY;
		}
		return nextColor;
	}
}


