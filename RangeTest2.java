/*
Class: SENG 438
File: RangeTest.java
Purpose: Test the getLowerBound, getUpperBound, getLength, contains, and intersectstests method in the class Range. 
# of Tests: 37
Failures: 7
Author: Sobia Khan, Maitry Rohit, Jamie Stade
Date: March 3, 2023
*/

package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest2 {
    private Range exampleRange;
    private Range exampleEqualRange;
    private Range testRange;
    private Range addedRange;


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	exampleEqualRange = new Range(1, 1);
        testRange = new Range(-5.0, 5.0);
        addedRange = new Range(-1, 3);
    }

    
    /*
    * getLowerBound() tests
    */
    
    //testing method getLowerBound() for case where upper bound != lower bound
    @Test
    public void lowerBoundRange() {
    	assertEquals("Lower bound of -1 and 1 ",
    	-1, exampleRange.getLowerBound(), .000000001d);
    }
    
    //testing method getLowerBound() for case where upper bound == lower bound
    @Test
    public void lowerBoundEqualRange() {
    	assertEquals("Lower bound of 1 and 1 ",
    	1, exampleEqualRange.getLowerBound(), .000000001d);
    }
    
     /*
    * getUpperBound() tests
    */
    
    //testing method getUpperBound() for case where upper bound != lower bound
    @Test
    public void upperBoundRange() {
    	assertEquals("Upper bound of -1 and 1 ",
    	1, exampleRange.getUpperBound(), .000000001d);
    }
    
    //testing method getUpperBound() for case where upper bound == lower bound
    @Test
    public void upperBoundEqualRange() {
    	assertEquals("Upper bound of 1 and 1 ",
    	1, exampleEqualRange.getUpperBound(), .000000001d);
    }
    
     /*
    * getLength() tests
    */
    
    //testing method getLength() for range with length greater than 0
    @Test
    public void lengthRange() {
    	assertEquals("Length of range -1 and 1 ",
    	2, exampleRange.getLength(), .000000001d);
    }
    
    //testing method getLength() for range with length 0
    @Test
    public void lengthEqualRange() {
    	assertEquals("Length of range 1 and 1 ",
    	0, exampleEqualRange.getLength(), .000000001d);
    }
    
     /*
    * contains() tests
    */
    
    //testing method contains() for valid value between boundary
    @Test
    public void rangeContainsValueInRange() {
    	assertTrue("Range of -1 to 1 contains 0 ",
    	exampleRange.contains(0));
    }
    
    //testing method contains() for valid value on lower boundary
    @Test
    public void rangeContainsLowerBound() {
    	assertTrue("The range of -1 to 1 contains -1 ",
    	exampleRange.contains(-1));
    }
    
    //testing method contains() for valid value on upper boundary
    @Test
    public void rangeContainsUpperBound() {
    	assertTrue("The range of -1 to 1 contains 1 ",
    	exampleRange.contains(1));
    }
    
    //testing method contains() for invalid value outside lower boundary
    @Test
    public void rangeContainsPastLowerBound() {
    	assertFalse("The range of -1 to 1 does not contain -1.5 ",
    	exampleRange.contains(-1.5));
    }
    
    //Test: testing method contains() for invalid value outside upper boundary
    @Test
    public void rangeContainsPastUpperBound() {
    	assertFalse("The range of -1 to 1 does not contain 1.5 ",
    	exampleRange.contains(1.5));
    }
    
    //Test: testing method contains() for invalid value outside upper boundary
    @Test
    public void rangeContainsWithSmallerRange() {
    	assertTrue("The range of -1 to 3 contain 1.5 ",
    	addedRange.contains(1.5));
    }
    
   /* Test: rangeContainsEqualBound
     * Description: Testing method contains() for range lower = upper
     * returns TRUE
     * NEW TEST
     */
    
    @Test
    public void rangeContainsEqualBound()
    {
    	assertTrue("The range of 1 to 1 contains 1", 
    	exampleEqualRange.contains(1.0));
    }
    
    //testing the constrain() method with a value inside the range
    @Test
    public void testConstrainInsideRange() {
    	double value = exampleRange.constrain(-0.5);
    	assertEquals("Testing for a value outside of the range", -0.5, value, .000000001d);
    }
    
     /*
    * intersects() tests
    */
    
   /* Test: notIntersectLowerNotIntersectUpperLessThanLower
     * Description: Sends a lower bound that is greater than the upper bound 
     * 	and neither bound is intersecting 
     * TEST UPDATED FROM PREVIOUS
     */

    @Test  
    public void notIntersectLowerNotIntersectUpperLessThanLower() {
    	assertFalse("Given range shouldn't intersect with initialized range, but does.",
    	testRange.intersects(17, 7.0));
    }
    
    /* Test: notIntersectLowerIntersectUpperLessThanLower
     * Description: Sends a lower bound that is greater than the upper bound 
     * 	The "upper bound" is intersecting
     * TEST UPDATED FROM PREVIOUS
     */
    @Test
    public void notIntersectLowerIntersectUpperLessThanLower(){
    	assertFalse("Given range shouldn't intersect with initialized range, but does.",
    	testRange.intersects(7.0, -3.0));
    }
    
    /* Test: intersectLowerNotIntersectUpperLessThanLower
     * Description: Sends a lower bound that is greater than the upper bound 
     * 	The "lower bound" is intersecting
     * NEW TEST!!!
     */
    @Test
    public void intersectLowerNotIntersectUpperLessThanLower(){
    	assertFalse("Given range shouldn't intersect with initialized range, but does.",
    	testRange.intersects(3.0, -6.0));
    }
    
    
    /* Note: Equivalence Test
     * Test: intersectWithinRange
     * Description: When given bound that is within the mock range the test passes
     * Expected Return: True
     */
    @Test
    public void intersectWithinRange()  {
    	assertTrue("Given range should intersect with initialized range, but does not.",
    			testRange.intersects(-4.99, 4.99));
    }
    
    /* Note: Boundary Test
     * Test: intersectIsRange
     * Description: Gives an intersect range equal to the mock range
     * Expected Return: True
     */
    @Test
    public void intersectIsRange()  {
    	assertTrue("Given range should intersect with initialized range, but does not.",
    			testRange.intersects(-5, 5));
    }
    
    @Test
    public void intersectSaRange()  {
    	assertTrue("Given range should intersect with initialized range, but does not.",
    			testRange.intersects(-5, 5));
    }
        
    
    /* Note: Equivalence Test
     * Test: intersectOverlappingLowerBound
     * Description: Lower bound given is within the range but the upper bound is not
     * Expected Return: True
     */
    @Test
    public void intersectOverlappingLowerBound() {
        assertTrue("Given range should intersect with initialized range, but does not.",
        		testRange.intersects(4.38, 7.38));
    }
    
    
    /* Note: Equivalence Test
     * Test: intersectOverlappingUpperBound
     * Description: Upper bound given is within the range but the lower bound is not
     * Expected Return: True
     */
    @Test
    public void intersectOverlappingUpperBound() {
    	assertTrue("Given range should intersect with initialized range, but does not.",
    			testRange.intersects(-10, -4.75));
    }  
    
    /* Note: Equivalence Test
     * Test: intersectNoOverlappingBoundLHS
     * Description: Neither the upper or lower bound should overlap with the 
     * given range on the left hand side of range 
     * Expected Return: False
     */
    @Test
    public void NoOverlappingBoundLHS() {
        assertFalse("Given range should not intersect with initialized range, but does.",
        testRange.intersects(-10, -5.01));
    }  
    
    /* Note: Equivalence Test
     * Test: intersectNoOverlappingBoundRHS
     * Description: Neither the upper or lower bound should overlap with the 
     * given range on the right hand side range
     * Expected Return: False
     */
    @Test
    public void NoOverlappingBoundRHS() {
        assertFalse("Given range should not intersect with initialized range, but does.",
        testRange.intersects(5.01, 10));
    }  
    
    //new tests for other methods to increase the mutation coverage 
    
    //intersects() test for when the upper bound == lower bound and b0 == lower bound
    @Test
    public void intersectEqualRange()  {
    	assertTrue("Given range should intersect with initialized equal range.",
    			exampleEqualRange.intersects(1, 5));
    }
    
    //intersects() test for when b0 == b1 == lower
    @Test
    public void intersectEqualValues() {
    	assertFalse("Given range shouldn't intersect with initialized range, but does.",
    			testRange.intersects(-5, -5));
    }  
    
    //intersects() test for with a close range
    @Test
    public void intersectSmallerRange() {
    	assertTrue("Given range should intersect with initialized range, but does not.",
    			exampleRange.intersects(-1, 5));
    }  
    
    //test for when the lower bound is greater than the upper bound
    //expected to throw IllegalArgumentException
    @Test (expected = IllegalArgumentException.class)
    public void testRangeInvalidInput() {
    	Range invalidRange = new Range(1, -1);	
    }
    
    //testing the getCentralValue() method with valid input
    @Test
    public void testCentralValueValidInput() {
    	double value = exampleRange.getCentralValue();
    	assertEquals("Testing with valid input.", 0.0, value, .000000001d);
    }
    
    //testing the contains() method with the lower bound
    @Test
    public void testContainsWithSmallRangeLowerBound() {
    	Range smallRange = new Range(0.5, 1);
    	boolean val = smallRange.contains(0.5);
    	assertTrue("0.5 is expected to be contained in the range 0.5 to 1 but was not", val);
    }
    
    //testing the constrain() method with a value outside of the range on the lower bound
    @Test
    public void testConstrainOutsideRangeLowerBound() {
    	double value = exampleRange.constrain(-2);
    	assertEquals("Testing for a value outside of the range", -1.0, value, .000000001d);
    }
    
    //testing the constrain() method with a value outside of the range on the upper bound
    @Test
    public void testConstrainOutsideRangeUpperBound() {
    	double value = exampleRange.constrain(2);
    	assertEquals("Testing for a value outside of the range", 1.0, value, .000000001d);
    }
    
    
    //testing the combine() method with ranges that are non-null and overlapping on lower bound
    @Test
    public void testCombineOverlappingNoNullsLowerRange() {
    	Range combinedRange = Range.combine(testRange, exampleRange);
    	double lower = combinedRange.getLowerBound();
    	assertEquals("testing the lower bound with no nulls", -5.0, lower, .000000001d);
    }
    
    //testing the combine() method with ranges that are non-null and overlapping on upper bound
    @Test
    public void testCombineOverlappingNoNullsUpperRange() {
    	Range combinedRange = Range.combine(testRange, exampleRange);
    	double upper = combinedRange.getUpperBound();
    	assertEquals("testing the upper bound with no nulls", 5.0, upper, .000000001d);
    }
    
    //testing the combine() method with ranges first range null for lower bound
    @Test
    public void testCombineNullFirstRangeLowerBound() {
    	Range combinedRange = Range.combine(null, exampleRange);
    	double lower = combinedRange.getLowerBound();
    	assertEquals("testing the lower bound when the first range is null", -1.0, lower, .000000001d);
    }
    
  //testing the combine() method with ranges first range null for upper bound
    @Test
    public void testCombineNullFirstRangeUpperBound() {
    	Range combinedRange = Range.combine(null, exampleRange);
    	double upper = combinedRange.getUpperBound();
    	assertEquals("testing the upper bound when the first range is null", 1.0, upper, .000000001d);
    }
    
  //testing the combine() method with ranges second range null for lower bound
    @Test
    public void testCombineNullSecondRangeLowerBound() {
    	Range combinedRange = Range.combine(testRange, null);
    	double lower = combinedRange.getLowerBound();
    	assertEquals("testing the lower bound when the second range is null", -5.0, lower, .000000001d);
    }
    
  //testing the combine() method with ranges second range null for upper bound
    @Test
    public void testCombineNullSecondRangeUpperBound() {
    	Range combinedRange = Range.combine(testRange, null);
    	double upper = combinedRange.getUpperBound();
    	assertEquals("testing the upper bound when the second range is null", 5.0, upper, .000000001d);
    }
    
}

