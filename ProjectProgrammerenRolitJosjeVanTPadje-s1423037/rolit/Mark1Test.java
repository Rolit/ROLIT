package rolit;

public class Mark1Test {
	/** Test variable for a <tt>Hotel</tt> object. */
	Board b = new Board();
    
    /** Number of errors. */
    private int errors;
    /** Notice belonging to test method. */
    private boolean isPrinted;
    /** Indication that an errors was found in test method. */
    private String description;

    /** Calls all tests in this class one-by-one. */
    public int runTest() {
        System.out.println("Test class: " + this.getClass());
        
        testNext2();
        
        testNext3();
        
        testNext4();
        
        
        if (errors == 0) {
            System.out.println("    OK");
        }
        return errors;
    }

  
    //test next(2)
    public void testNext2(){
    	assertEquals("ROOD => GROEN", Mark1.GROEN, Mark1.ROOD.next(2));
    	
    	assertEquals("GROEN => ROOD", Mark1.ROOD, Mark1.GROEN.next(2));
    	
    	assertEquals("EMPTY => EMPTY", Mark1.EMPTY, Mark1.EMPTY.next(2));
    }
    
    //test next(3)
    public void testNext3(){
    	assertEquals("ROOD => GEEL", Mark1.GEEL, Mark1.ROOD.next(3));
    	
    	assertEquals("GEEL => GROEN", Mark1.GROEN, Mark1.GEEL.next(3));
    	
    	assertEquals("GROEN => ROOD", Mark1.ROOD, Mark1.GROEN.next(3));
    	
    	assertEquals("EMPTY => EMPTY", Mark1.EMPTY, Mark1.EMPTY.next(3));
    }
    
    // test next(4)
    public void testNext4(){
    	assertEquals("ROOD => GEEL", Mark1.GEEL, Mark1.ROOD.next(4));
    	
    	assertEquals("GEEL => GROEN", Mark1.GROEN, Mark1.GEEL.next(4));
    	
    	assertEquals("GROEN => BLAUW", Mark1.BLAUW, Mark1.GROEN.next(4));
    	
    	assertEquals("BLAUW => ROOD", Mark1.ROOD, Mark1.BLAUW.next(4));
    	
    	assertEquals("EMPTY => EMPTY", Mark1.EMPTY, Mark1.EMPTY.next(4));
    }
    
    /**
     * Fixes the status for the testmethod's description.
     * @param text The description to be printed
     */


    /**
     * Tests if the resulting value of a tested expression equals the 
     * expected (correct) value. This implementation prints both values, 
     * with an indication of what was tested, to the standard output. The 
     * implementation does not actually do the comparison.
     */
    private void assertEquals(String text, Object expected, Object result) {
        boolean equal;
        // tests equality between expected and result
        // accounting for null
        if (expected == null) {
            equal = result == null;
        } else {
            equal = result != null && expected.equals(result);
        }
        if (!equal) {
            // prints the description if necessary
            if (!isPrinted) {
                System.out.println("    Test: " + description);
                // now the description is printed
                isPrinted = true;
            }
            System.out.println("        " + text);
            System.out.println("            Expected:  " + expected);
            System.out.println("            Result: " + result);
            errors++;
        }
    }
	
	
	public static void main(String[] args) {
		new Mark1Test().runTest();

	}

}
