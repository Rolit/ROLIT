package rolit;

public enum Mark {
	
	EMPTY, ROOD, GEEL, GROEN, BLAUW;
	
	/*@
    ensures this == Mark.ROOD ==> \result == Mark.ROOD;
    ensures this == Mark.GEEL ==> \result == Mark.GEEL;
    ensures this == Mark.GROEN ==> \result == Mark.GROEN;
    ensures this == Mark.BLAUW ==> \result == Mark.BLAUW;
    ensures this == Mark.EMPTY ==> \result == Mark.EMPTY;
  */
	
	public Mark next4() {
		if (this == ROOD){
			return GEEL; 
		}
		else if (this == GEEL){
			return GROEN;
		}
		else if (this == GROEN){
			return BLAUW;
		}
		else{
			return ROOD;
		}
	}
	
	public Mark next3(){
		if (this == ROOD){
			return GEEL;
		}
		else if (this == GEEL){
			return GROEN;
		}
		else{
			return ROOD;
		}
	}
	
	public Mark next2(){
		if (this == ROOD){
			return GROEN;
		}
		else{
			return ROOD;
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


