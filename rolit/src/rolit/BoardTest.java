package rolit;
import java.util.LinkedList;

import rolit.Board;

public class BoardTest {
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
        setUp();
        testIsField();
        setUp();
        testGetField();
        setUp();
        testAantalMark();
        setUp();
        testActiveFields();
        setUp();
        testIsValidMove();
        setUp();
        testRecolorFields();
        setUp();
        
        

        if (errors == 0) {
            System.out.println("    OK");
        }
        return errors;
    }

    /**
     * Sets the instance variable <tt>hotel</tt> to a well-defined initial value.
     * All test methods should be preceded by a call to this method.
     */
    public void setUp() {
        // initialisation of password-variable
    	Board b = new Board();
    	b.reset();
    }

    // check of isField werkt
    public void testIsField() {
        beginTest("isField method");
        
        boolean isVeld0 = b.isField(0, 0);
        assertEquals("Eerste veld", true, isVeld0);
        
        boolean isVeld1 = b.isField(7, 7);
        assertEquals("Laatste veld", true, isVeld1);
        
        boolean isVeld2 = b.isField(-8, -8);
        assertEquals("coordinaat buiten het veld", false, isVeld2);
    }
    
    
    
    //check of reset werkt
    public void testGetField(){
    	beginTest("getField method");
    	Mark1 mark0 = b.getField(3, 3);
    	assertEquals("controlleer of veld ROOD is", Mark1.ROOD, mark0);
    	
    	Mark1 mark1 = b.getField(4, 3);
    	assertEquals("controlleer of veld GEEL is", Mark1.GEEL, mark1);
    	
    	Mark1 mark2 = b.getField(3, 4);
    	assertEquals("controlleer of veld BLAUW is", Mark1.BLAUW, mark2);
    	
    	Mark1 mark3 = b.getField(4, 4);
    	assertEquals("controlleer of veld GROEN is", Mark1.GROEN, mark3);

    	Mark1 mark4 = b.getField(5, 4);
    	assertEquals("controlleer of veld EMPTY is", Mark1.EMPTY, mark4);
    	
    	

    }
    
    //controlleer aantal mark
    public void testAantalMark(){
    	beginTest("aantalMark method");
    	b.setField(3, 0, Mark1.ROOD);
    	b.setField(0, 0, Mark1.GEEL);
    	b.setField(0, 1, Mark1.GEEL);
    	b.setField(0, 2, Mark1.GEEL);
    	b.setField(0, 3, Mark1.GEEL);
    	b.setField(1, 0, Mark1.GROEN);
    	b.setField(1, 1, Mark1.GROEN);
    	b.setField(1, 2, Mark1.GROEN);
    	b.setField(2, 0, Mark1.BLAUW);
    	b.setField(2, 1, Mark1.BLAUW);
    	
    	int aantalKleur0 = b.aantalMark(Mark1.ROOD);
    	assertEquals("aantal ROOD", 2, aantalKleur0);
    	
    	int aantalKleur1 = b.aantalMark(Mark1.GEEL);
    	assertEquals("aantal GEEL", 5, aantalKleur1);
    	
    	int aantalKleur2 = b.aantalMark(Mark1.GROEN);
    	assertEquals("aantal GROEN", 4, aantalKleur2);
    	
    	int aantalKleur3 = b.aantalMark(Mark1.BLAUW);
    	assertEquals("aantal BLAUW", 3, aantalKleur3);
    }
    
    public void testActiveFields(){
    	beginTest("activeFields method");
    	b.reset();
    	LinkedList<LinkedList<Integer>> mogelijkeVelden = b.activeFields(Mark1.ROOD);
    	mogelijkeVelden = b.activeFields(Mark1.GEEL);
    	System.out.println(mogelijkeVelden);
    }
    
    
    
    //test isValidMove
    public void testIsValidMove(){
    	beginTest("isValidMove method");
    	b.reset();
    	b.activeFields(Mark1.ROOD);
    	
    	boolean magSet0 = b.isValidMove(3, 5, Mark1.ROOD);
    	assertEquals("kijken of rood op 3, 5", true, magSet0);
    	
    	boolean magSet1 = b.isValidMove(2, 3, Mark1.ROOD);
    	assertEquals("kijken of rood in 2, 3", false, magSet1);
    	
    	boolean magSet2 = b.isValidMove(1, 1, Mark1.ROOD);
    	assertEquals("kijken of rood 1, 1", false, magSet2);
    	
    	
    }
    
    public void testRecolorFields(){
    	beginTest("recolorFields method");
    	b.reset();
    	
    	b.recolorFields(3, 5, Mark1.ROOD);
    	b.recolorFields(5, 5, Mark1.ROOD);
    	b.recolorFields(5, 3, Mark1.ROOD);
    	Mark1 mark0 = b.getField(3, 5);
    	Mark1 mark1 = b.getField(3, 4);
    	Mark1 mark2 = b.getField(5, 5);
    	Mark1 mark3 = b.getField(4, 4);
    	Mark1 mark4 = b.getField(5, 3);
    	Mark1 mark5 = b.getField(4, 3);
    	assertEquals("Kijekn of 3, 5 rood is", Mark1.ROOD, mark0);
    	assertEquals("Kijken of 3, 4 rood is", Mark1.ROOD, mark1);
    	assertEquals("Kijekn of 5, 5 rood is", Mark1.ROOD, mark2);
    	assertEquals("Kijken of 4, 4 rood is", Mark1.ROOD, mark3);
    	assertEquals("Kijekn of 5, 3 rood is", Mark1.ROOD, mark4);
    	assertEquals("Kijken of 4, 3 rood is", Mark1.ROOD, mark5);
    
    	
    	b.reset();
    	
    	b.recolorFields(2, 5, Mark1.GEEL);
    	b.recolorFields(2, 3, Mark1.GEEL);
    	Mark1 mark6 = b.getField(2, 5);
    	Mark1 mark7 = b.getField(3, 4);
    	Mark1 mark8 = b.getField(2, 3);
    	Mark1 mark9 = b.getField(3, 3);
    	assertEquals("Kijekn of 2, 5 rood is", Mark1.GEEL, mark6);
    	assertEquals("Kijken of 3, 4 rood is", Mark1.GEEL, mark7);
    	assertEquals("Kijekn of 2, 3 rood is", Mark1.GEEL, mark8);
    	assertEquals("Kijken of 3, 3 rood is", Mark1.GEEL, mark9);
    	
    	b.reset();
    	
    	b.recolorFields(4, 2, Mark1.GROEN);
    	b.recolorFields(2, 2, Mark1.GROEN);
    	Mark1 mark10 = b.getField(4, 2);
    	Mark1 mark11 = b.getField(4, 3);
    	Mark1 mark12 = b.getField(2, 2);
    	Mark1 mark13 = b.getField(3, 3);
    	assertEquals("Kijken of 4, 2 groen is", Mark1.GROEN, mark10);
    	assertEquals("Kijken of 4, 3 groen is", Mark1.GROEN, mark11);
    	assertEquals("Kijken of 2, 2 groen is", Mark1.GROEN, mark12);
    	assertEquals("Kijken of 3, 3 groen is", Mark1.GROEN, mark13);
    	
    	b.reset();
    	b.recolorFields(5, 2, Mark1.BLAUW);
    	Mark1 mark14 = b.getField(5, 2);
    	Mark1 mark15 = b.getField(4, 3);
    	LinkedList<LinkedList<Integer>> mogelijkeVelden = b.activeFields(Mark1.ROOD);
    	mogelijkeVelden = b.activeFields(Mark1.BLAUW);
    	System.out.println(mogelijkeVelden);
    	assertEquals("Kijekn of 5, 2 groen is", Mark1.BLAUW, mark14);
    	assertEquals("Kijken of 4, 3 groen is", Mark1.BLAUW, mark15);
    	
    }
    
    /**
     * Fixes the status for the testmethod's description.
     * @param text The description to be printed
     */
    private void beginTest(String text) {
        description = text;
        // the description hasn't been printed yet
        isPrinted = false;
    }

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
		new BoardTest().runTest();
	}
}
