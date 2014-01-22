package rolit;

public enum Mark {
	
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
	
	public Mark next(int i) {
		if(i == 4){
			if (this == ROOD){
				return GEEL; 
			}
			else if (this == GEEL){
				return GROEN;
			}
			else if (this == GROEN){
				return BLAUW;
			}
			else if (this == BLAUW){
				return ROOD;
			}
			else{
				return EMPTY;
			}
		}
		else if(i == 3){
			if (this == ROOD){
				return GEEL;
			}
			else if (this == GEEL){
				return GROEN;
			}
			else if(this == GROEN){
				return ROOD;
			}
			else{
				return EMPTY;
			}	
		}
		else if (i == 2){
			if (this == ROOD){
				return GROEN;
			}
			else if (this == GROEN){
				return ROOD;
			}
			else{
				return EMPTY;
			}
		}
		else{
			return EMPTY;
		}
	}
	
	public boolean isOtherMark(Board b, int col, int row){
		if (this == ROOD && (b.getField(col, row) == GEEL || b.getField(col, row) == GROEN || b.getField(col, row) == BLAUW)){
			return true;
		}
		else if (this == GEEL && (b.getField(col, row) == ROOD || b.getField(col, row) == GROEN || b.getField(col, row) == BLAUW)){
			return true;
		}
		else if (this == GROEN && (b.getField(col, row) == ROOD || b.getField(col, row) == GEEL || b.getField(col, row) == BLAUW)){
			return true;
		}
		else if (this == BLAUW && (b.getField(col, row) == ROOD || b.getField(col, row) == GEEL || b.getField(col, row) == GROEN)){
			return true;
		}
		return false;
	}

}


