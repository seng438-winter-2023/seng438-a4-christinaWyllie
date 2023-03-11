/*
Class: SENG 438

File: DataUtilitestest.java
Purpose: Test the calculateColumnTotal, calculateRowTotal, createNumberArray, createNumberArray2D, and getCumulativePercentage tests method in the class DataUtilities. 

# of Tests: 37
Failures: 7

Author: Christina Wyllie, Sobia Khan, Maitry Rohit
Date: March 3, 2023

*/

package org.jfree.data;

import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import java.security.*;

public class DataUtilitiesTest {
	
	private Values2D values;
	private Number[] myNumberArray;
	private Number[][] myNumberArray2D;
    private KeyedValues myKeyedValues;

	
	/*
	 * SETUP
	 */
	@Before
	public void setUp() throws Exception{
		//create a mockery object for Values2D
		Mockery mockingContext = new Mockery();
	    values = mockingContext.mock(Values2D.class);
	    //create a Values2D object with 3 rows and 3 columns
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            //three rows
	            will(returnValue(3));
	            one(values).getColumnCount();
	            //three columns
	            will(returnValue(3));
	            
	            //populate 0th row
	            one(values).getValue(0, 0);
	            //insert 2.0 into the 0th row and 0th column
	            will(returnValue(2.0));
	            one(values).getValue(0, 1);
	            //insert 2.5 into the 0th row and 1st column
	            will(returnValue(2.5));
	            one(values).getValue(0, 2);
	            //insert 3.8 into the 0th row and 2nd column
	            will(returnValue(3.8));
	            
	            //populate 1st row
	            one(values).getValue(1, 0);
	            //insert 5.0 into the 1st row and 0th column
	            will(returnValue(5.0));
	            one(values).getValue(1, 1);
	            //insert 1.0 into the 1st row and 1st column
	            will(returnValue(1.0));
	            one(values).getValue(1, 2);
	            //insert 3.5 into the 1st row and 2nd column
	            will(returnValue(3.5));
	            
	            //populate 2nd row
	            one(values).getValue(2, 0);
	            //insert 2.0 into the 0th row and 0th column
	            will(returnValue(2.0));
	            one(values).getValue(2, 1);
	            //insert 10.0 into the 2nd row and 1st column
	            will(returnValue(10.0));
	            one(values).getValue(2, 2);
	            //insert 9.8 into the 2nd row and 2nd column
	            will(returnValue(9.8));
	        }
	    });
	    
	    double[] myDoubleArray = {-12356015.0, 1, 2.01, 50000000};
		myNumberArray = DataUtilities.createNumberArray(myDoubleArray);
		
		double[][] my2DDoubleArray = {{-12356015.0}, {1, 2.01}, {50000000}};
		myNumberArray2D = DataUtilities.createNumberArray2D(my2DDoubleArray);

        // creates mock object for keyedValues
	    myKeyedValues = mockingContext.mock(KeyedValues.class);
	    mockingContext.checking(new Expectations() {
	        {
	        	for(int i = 0; i < 5; i++) { //KeyedValues object is created with keys {0,1,2,3,4} and values {0,1,2,3,4}
	        		allowing(myKeyedValues).getKey(i);
	        		will(returnValue(i));
	        		allowing(myKeyedValues).getValue(i);
	        		will(returnValue(i));
	        	}
	        	allowing(myKeyedValues).getItemCount();
	        	will(returnValue(5)); //Confirms that the KeyedValues object has the correct size
	        }
	    });
	}
	
	
	/*
	 * CALCULATECOLUMNTOTAL TESTS
	 */
	@Test
	public void calculateColumnWithLowerBound() {
		//calculating the total in column 0, the lower bound 
		double result = DataUtilities.calculateColumnTotal(values, 0);
		// verify
	    assertEquals("Calculated column total with lower bound", 9.0, result, .000000001d);
	}
	
	@Test
	public void calculateColumnWithAboveLowerBound() {
		//calculating the total in column 1, below upper bound and above upper bound
		double result = DataUtilities.calculateColumnTotal(values, 1);
		// verify
	    assertEquals("Calculated column total for column 1", 13.5, result, .000000001d);
	}
	
	@Test
	public void calculateColumnWithUpperBound() {
		//calculating the total in column 2, the upper bound
		double result = DataUtilities.calculateColumnTotal(values, 2);
		// verify
	    assertEquals("Calculated column total with upper bound", 17.1, result, .000000001d);
	}
	
	@Test
	public void calculateColumnWithNegativeValues() {
		//creating a mockery object specific for this test
		//using negative values to calculate the column total
		Values2D val;
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with 3 rows and 3 columns
	    mockingContext2.checking(new Expectations() {
	        {
	            one(val).getRowCount();
	            //three rows
	            will(returnValue(3));
	            one(val).getColumnCount();
	            //one column
	            will(returnValue(1));
	            
	            //populate 0th row
	            one(val).getValue(0, 0);
	            //insert -2.0 into the 0th row and 0th column
	            will(returnValue(-2.0));
	            one(val).getValue(1, 0);
	            //insert 2.5 into the 1st row and 0th column
	            will(returnValue(2.5));
	            one(val).getValue(2, 0);
	            //insert -3.8 into the 2nd row and 0th column
	            will(returnValue(-3.8));
	        }
	    });
	         
	    double result = DataUtilities.calculateColumnTotal(val, 0);
		// verify
	    assertEquals("Calculated column total with negative values", -3.3, result, .000000001d);
	}
	
	
	//NEW TEST FOR WHEN THE DATA INSIDE THE MOCK IS NULL: Column
	@Test
	public void calculateColumnWithNullValues() {
		//creating a mockery object specific for this test
		//using negative values to calculate the column total
		Values2D val;
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with 3 rows and 3 columns
	    mockingContext2.checking(new Expectations() {
	        {
	            one(val).getRowCount();
	            //three rows
	            will(returnValue(3));
	            one(val).getColumnCount();
	            //one column
	            will(returnValue(1));
	            
	          //populate 0th row
	            one(val).getValue(0, 0);
	            //insert -2.0 into the 0th row and 0th column
	            will(returnValue(null));
	            one(val).getValue(1, 0);
	            //insert 2.5 into the 1st row and 0th column
	            will(returnValue(null));
	            one(val).getValue(2, 0);
	            //insert -3.8 into the 2nd row and 0th column
	            will(returnValue(0.4));
	        }
	    });
	         
	    double result = DataUtilities.calculateColumnTotal(val, 0);
		// verify
	    assertEquals("Calculated column total with null values", 0.4, result, .000000001d);
	}
	

	
	@Test
	public void calculateColumnWithNullData(){
		Values2D val = null;
		try {
			//calculating the total with invalid input, null data
			double result = DataUtilities.calculateColumnTotal(val, 1);
		}
		catch(InvalidParameterException e) {
			//if InvalidParameterException is caught, then the correct exception was thrown
			assertTrue(true);
		}
		catch(Exception e) {
			//else, should fail
			fail("InvalidParameterException was not caught");
		}
	}
	
	
	//test the second for loop in the method
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateColumnWithNegativeRowCountWithNull() {
		//creating a mockery object specific for this test
		//using negative values to calculate the column total
		Values2D val;
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with -1 rows and 1 columns
	    mockingContext2.checking(new Expectations() {
	        {
	       
	            one(val).getRowCount();
	            //-1 column
	            will(returnValue(-1));
	            
	            one(val).getColumnCount();
	            //-1 column
	            will(returnValue(-1));
	            
	          //populate 0th row
	            one(val).getValue(0, -1);
	            //insert null into the 0th row and -1 column
	            will(returnValue(null));
	        }
	    });
	         
	  //get the value in the -1 spot for the column given the values inputed
	    double result = DataUtilities.calculateColumnTotal(val, -1);
		// verify
	    assertEquals("Calculated column total with negative row count", 0.0, result, .000000001d);
	}
	
	//test the second for loop in the method
		@Test(expected = IndexOutOfBoundsException.class)
		public void calculateColumnWithNegativeRowCountWithNonNull() {
			//creating a mockery object specific for this test
			//using negative values to calculate the column total
			Values2D val;
			Mockery mockingContext2 = new Mockery();
		    val = mockingContext2.mock(Values2D.class);
		    //create a Values2D object with -1 rows and 1 columns
		    mockingContext2.checking(new Expectations() {
		        {
		       
		            one(val).getRowCount();
		            //-1 column
		            will(returnValue(-1));
		            
		            one(val).getColumnCount();
		            //-1 column
		            will(returnValue(-1));
		            
		          //populate 0th row
		            one(val).getValue(0, -1);
		            //insert null into the 0th row and -1 column
		            will(returnValue(0.4));
		        }
		    });
		         
		    //get the value in the -1 spot for the column given the values inputed
		    double result = DataUtilities.calculateColumnTotal(val, -1);
			// verify
		    assertEquals("Calculated column total with negative row count", 0.4, result, .000000001d);
		}
	
	
	 @Test(expected = IndexOutOfBoundsException.class)
	    //method to test if the correct error was thrown when out of bounds
	 public void calculateColumnTotalAboverUpperBound() {
		Mockery mockingContext2 = new Mockery();
		Values2D values = mockingContext2.mock(Values2D.class);
		mockingContext2.checking(new Expectations() {
		    {
			one(values).getRowCount();
			will(returnValue(2));
			one(values).getValue(0, 4);
			will(returnValue(null));
			one(values).getValue(1, 4);
			will(returnValue(null));
		    }
		});

		double result = DataUtilities.calculateColumnTotal(values, 4);
		assertEquals("Calculated column total with above upper bound", 0.0, result, .000000001d);
	 }
	 
	 
	
	/*
	 * CALCULATEROWTOTAL() TESTS
	 */
	@Test
	public void calculateRowWithLowerBound() {
		//calculating the total in row 0, the lower bound 
		double result = DataUtilities.calculateRowTotal(values, 0);
		// verify
	    assertEquals("Calculated row total with lower bound", 8.3, result, .000000001d);
	}
	
	@Test
	public void calculateRowWithAboveLowerBound() {
		//calculating the total in row 1. below upper bound and above lower bound
		double result = DataUtilities.calculateRowTotal(values, 1);
		// verify
	    assertEquals("Calculated row total for row 1", 9.5, result, .000000001d);
	}
	
	@Test
	public void calculateRowWithUpperBound() {
		//calculating the total in row 2. upper bound
		double result = DataUtilities.calculateRowTotal(values, 2);
		// verify
	    assertEquals("Calculated row total with upper bound", 21.8, result, .000000001d);
	}
	
	@Test 
	public void calculateRowWithNullData(){
		Values2D val = null;
		try {
			//calculating the total with invalid input, null data
			double result = DataUtilities.calculateRowTotal(val, 1);
		}
		catch(InvalidParameterException e) {
			//if InvalidParameterException is caught, then the correct exception was thrown
			assertTrue(true);
		}
		catch(Exception e) {
			//else, the test fails
			fail("InvalidParameterException was not caught");
		}
		
	}
	
	@Test
	public void calculateRowWithNegativeValues() {
		Values2D val;
		//creating a mockery object specific for this test
		//using negative values to calculate the row total
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with 3 rows and 3 columns
	    mockingContext2.checking(new Expectations() {
	        {
	            one(val).getRowCount();
	            //one row
	            will(returnValue(1));
	            one(val).getColumnCount();
	            //three columns
	            will(returnValue(3));
	            
	            //populate 0th row
	            one(val).getValue(0, 0);
	            //insert -2.0 into the 0th row and 0th column
	            will(returnValue(-2.0));
	            one(val).getValue(0, 1);
	            //insert 2.5 into the 0th row and 1st column
	            will(returnValue(2.5));
	            one(val).getValue(0, 2);
	            //insert -3.8 into the 0th row and 2nd column
	            will(returnValue(-3.8));
	        }
	    });
	         
	    double result = DataUtilities.calculateRowTotal(val, 0);
		// verify
	    assertEquals("Calculated row total with negative values", -3.3, result, .000000001d);
	}
	
	//NEW TEST FOR WHEN THE DATA INSIDE THE MOCK IS NULL: ROW
	@Test
	public void calculateRowWithNullValues() {
		//creating a mockery object specific for this test
		//using negative values to calculate the column total
		Values2D val;
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with 1 rows and 3 columns
	    mockingContext2.checking(new Expectations() {
	        {
	            one(val).getRowCount();
	            //three rows
	            will(returnValue(1));
	            one(val).getColumnCount();
	            //one column
	            will(returnValue(3));
	            
	          //populate 0th row
	            one(val).getValue(0, 0);
	            //insert null into the 0th row and 0th column
	            will(returnValue(null));
	            one(val).getValue(0, 1);
	            //insert null into the 0th row and 1st column
	            will(returnValue(null));
	            one(val).getValue(0, 2);
	            //insert 0.4 into the 0th row and 2nd column
	            will(returnValue(0.4));
	        }
	    });
	         
	    double result = DataUtilities.calculateRowTotal(val, 0);
		// verify
	    assertEquals("Calculated row total with null values", 0.4, result, .000000001d);
	}
	
	//test the second for loop in the method
	@Test(expected = IndexOutOfBoundsException.class)
		public void calculateRowWithNegativeColumnCountWithNull() {
			//creating a mockery object specific for this test
			//using negative values to calculate the column total
			Values2D val;
			Mockery mockingContext2 = new Mockery();
		    val = mockingContext2.mock(Values2D.class);
		    //create a Values2D object with -1 rows and 1 columns
		    mockingContext2.checking(new Expectations() {
		        {
		            
		            one(val).getColumnCount();
		            //-1 column
		            will(returnValue(-1));
		            
		            one(val).getRowCount();
		            //-1 column
		            will(returnValue(-1));
		            
		          //populate 0th row
		            one(val).getValue(-1, 0);
		            //insert null into the 0th row and -1 column
		            will(returnValue(null));
		        }
		    });
		    
		    
		  //get the value in the -1 spot for the row given the values inputed
		    double result = DataUtilities.calculateRowTotal(val, -1);
			// verify
		    assertEquals("Calculated row total with negative column count", 0.0, result, .000000001d);
		}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowWithNegativeColumnCountWithNonNull() {
		//creating a mockery object specific for this test
		//using negative values to calculate the column total
		Values2D val;
		Mockery mockingContext2 = new Mockery();
	    val = mockingContext2.mock(Values2D.class);
	    //create a Values2D object with -1 rows and 1 columns
	    mockingContext2.checking(new Expectations() {
	        {
	            
	            one(val).getColumnCount();
	            //-1 column
	            will(returnValue(-1));
	            
	            one(val).getRowCount();
	            //-1 column
	            will(returnValue(-1));
	            
	          //populate 0th row
	            one(val).getValue(-1, 0);
	            //insert null into the 0th row and -1 column
	            will(returnValue(0.4));
	        }
	    });
	         
	  //get the value in the -1 spot for the row given the values inputed
	    double result = DataUtilities.calculateRowTotal(val, -1);
		// verify
	    assertEquals("Calculated row total with negative column count", 0.4, result, .000000001d);
	}
	
	//test the second for loop in the method
	@Test
		public void calculateRowWithNoValuesProvided() {
			//creating a mockery object specific for this test
			//using negative values to calculate the column total
			Values2D val;
			Mockery mockingContext2 = new Mockery();
			val = mockingContext2.mock(Values2D.class);
			//create a Values2D object with -1 rows and 1 columns
			mockingContext2.checking(new Expectations() {
				{
					one(val).getColumnCount();
			        //three rows
			        will(returnValue(0));
			        
			     }
			});
			         
			double result = DataUtilities.calculateRowTotal(val, 0);
			// verify
			assertEquals("Calculated row total with no values given", 0.0, result, .000000001d);
		}
	
	 @Test(expected = IndexOutOfBoundsException.class)
	    //method to test if the correct error was thrown when out of bounds
	 public void calculateRowTotalAboverUpperBound() {
		Mockery mockingContext2 = new Mockery();
		Values2D values = mockingContext2.mock(Values2D.class);
		mockingContext2.checking(new Expectations() {
		    {
			one(values).getColumnCount();
			will(returnValue(2));
			one(values).getValue(3, 0);
			will(returnValue(null));
			one(values).getValue(3, 1);
			will(returnValue(null));
		    }
		});
		double result = DataUtilities.calculateRowTotal(values, 3);
		assertEquals("Calculated row total with above upper bound", 0.0, result, .000000001d);
	 }
	 
	 
	 
	 //CreateNumberArray Tests 

	    /* Equivalence Class Test: Test that valid double[] input creates expected
		 * Double[] output.
		 */
		
		@Test
		public void createNumberArrayValidInput() 
		{
			Double[] expectedDoubleArray = {-12356015.0, 1.0, 2.01, 50000000.0};
			assertArrayEquals(expectedDoubleArray, myNumberArray);
		}
		
		/* Equivalence Class Test: Test that valid double[] input creates expected
		 * length Double[] output.
		 */
		
		@Test
		public void createNumberArrayValidInputLength() 
		{
			Double[] expectedDoubleArray = {-12356015.0, 1.0, 2.01, 50000000.0};
			assertEquals(expectedDoubleArray.length, myNumberArray.length);
		}
		
		/* Boundary Value Test: When given max value that can be held in a double
		 * expected Number array is created. Ensure that variables can hold largest 
		 * positive number without variables overflowing. 
		 */
		
		@Test
		public void createNumberArrayLargeInput() 
		{
			Double[] expectedDoubleArray = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
			double[] inputArray = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
			myNumberArray = DataUtilities.createNumberArray(inputArray);
			assertEquals(expectedDoubleArray[0], myNumberArray[0]);
		}
		
		/* Boundary Value Test: When given the largest negative number that can be held in a double
		 * expected number array is created. Ensure that variables can hold largest negative double number
		 * without overflowing. 
		 */
		
		@Test
		public void createNumberArraySmallInput() 
		{
			Double[] expectedDoubleArray = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
			double[] inputArray = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
			myNumberArray = DataUtilities.createNumberArray(inputArray);
			assertEquals(expectedDoubleArray[0], myNumberArray[0]);
		}
		
		/* Equivalence Class Test: Pass in empty double array as input and test that 
		 * empty Double array created. Empty double[] is valid input according to documentation
		 * and should create expected Double[].
		 */
		
		@Test
		public void createNumberArrayEmptyInput()
		{
			double[] emptyArray = {};
			Double[] expectedDoubleArray = {};
			myNumberArray = DataUtilities.createNumberArray(emptyArray);
			assertArrayEquals(expectedDoubleArray, myNumberArray);
		}
		
		/* Boundary Value Test: Test that when null passed in as argument, 
		 * InvalidParameterException is thrown
		 * Catch InvalidParameterExeption if thrown and assert that test has passed
		 * Catch all other exceptions and assert that test failed. 
		 * 
		 * Cannot pass in char[], int[], Double[] etc. Gives compilation errors
		 */
		
		@Test //(expected = InvalidParameterException.class)
		public void createNumberArrayThrowsInvalidParameterException() //throws InvalidParameterException
		{	
			// TA: Shohug 
			// Confirmed that try catch is okay for checking whether or not invalid parameter exception is thrown
			
			try 
			{
				double[] invalidArray = null;
				myNumberArray = DataUtilities.createNumberArray(invalidArray); // throws illegal argument exception when invalidArray = null
			}
			
			catch (InvalidParameterException e)
			{
				assertTrue("InvalidParameterException correctly thrown for invalid input null", true);
			}
			
			catch (Exception e)
			{
				fail();
			}

		}

	    //CreateNumberArray2D Tests
		
		/* Equivalence Class Test: Test that passing in a valid 2D double array creates 
		 * the expected Double Array
		 */
		
		@Test
		public void createNumberArray2DValidInput() 
		{
			Double[][] expected2DDoubleArray = {{-12356015.0}, {1.0, 2.01}, {50000000.0}};
			assertArrayEquals(expected2DDoubleArray, myNumberArray2D);
		}
		
		/* Equivalence Class Test: Test that Double[] array created has the correct 
		 * number of rows
		 */
		
		@Test
		public void createNumberArray2DCorrectRowsLength() 
		{
			Double[][] expected2DDoubleArray = {{-12356015.0}, {1.0, 2.01}, {50000000.0}};
			assertEquals(expected2DDoubleArray.length, myNumberArray2D.length);
		}
		
		/* Equivalence Class Test: Test that array created has the expected 
		 * number of columns for each row
		 */
		
		@Test
		public void createNumberArray2DCorrectColumnsLength() 
		{
			Double[][] expected2DDoubleArray = {{-12356015.0}, {1.0, 2.01}, {50000000.0}};
			for(int i = 0; i < expected2DDoubleArray.length; i++)
			{
				assertEquals(expected2DDoubleArray[i].length, myNumberArray2D[i].length);
			}	
		}
		
		/* Equivalence Class Test: Pass in empty double[][] as input and test that 
		 * empty Double array created. Empty double[][] is valid input and should 
		 * create valid Number array.
		 */
		
		@Test
		public void createNumberArray2DEmptyInput()
		{
			double[][] empty2DArray = {{}, {}, {}};
			Double[][] expected2DDoubleArray = {{}, {}, {}};
			myNumberArray2D = DataUtilities.createNumberArray2D(empty2DArray);
			assertArrayEquals(expected2DDoubleArray, myNumberArray2D);
		}

		/* Boundary Value Test: When given max value that can be held in a double
		 * correct Number array is created, ensure that variables inside createNumberArray can hold 
		 * correct amount of precision. 
		 */
		
		@Test
		public void createNumberArray2DLargeInput() 
		{
			Double[][] expected2DDoubleArray = {{Double.MAX_VALUE, Double.MAX_VALUE}, {Double.MAX_VALUE}, {Double.MAX_VALUE}};
			double[][] inputArray = {{Double.MAX_VALUE, Double.MAX_VALUE}, {Double.MAX_VALUE}, {Double.MAX_VALUE}};
			myNumberArray2D = DataUtilities.createNumberArray2D(inputArray);
			assertEquals(expected2DDoubleArray[0][0], myNumberArray2D[0][0]);
		}
		
		/* Boundary Value Test: When given the largest negative number that can be held in a double
		 * correct number array is created, ensure that variables inside createNumberArray can hold 
		 * correct amount of precision. 
		 */
		
		@Test
		public void createNumberArray2DSmallInput() 
		{
			Double[][] expected2DDoubleArray = {{Double.MIN_VALUE, Double.MIN_VALUE}, {Double.MIN_VALUE}, {Double.MIN_VALUE}};
			double[][] inputArray = {{Double.MIN_VALUE, Double.MIN_VALUE}, {Double.MIN_VALUE}, {Double.MIN_VALUE}};
			myNumberArray2D = DataUtilities.createNumberArray2D(inputArray);
			assertEquals(expected2DDoubleArray[0][0], myNumberArray2D[0][0]);
		}
		
		/* Boundary Value Test: Test that when null passed in as argument, 
		 * InvalidParameterException is thrown
		 * Catch InvalidParameterExeption if thrown and assert that test has passed
		 * Catch all other exceptions and assert that test failed. 
		 * 
		 * Cannot pass in char[][], int[][], Double[][], etc. Gives compilation errors
		 */
		
		@Test //(expected = InvalidParameterException.class)
		public void createNumberArray2DThrowsInvalidParameterException() //throws InvalidParameterException
		{
			// TA: Shohug 
			// Confirmed that try catch is okay for checking whether or not invalid parameter exception is thrown
					
			try
			{
				// Invalid data according to documentation is null, should throw invalid parameter exception
				double[][] invalidArray = null;
				myNumberArray2D = DataUtilities.createNumberArray2D(invalidArray);
			}
			
			catch (InvalidParameterException e)
			{
				// If invalid parameter exception is thrown, assert that test passes 
				assertTrue(true);
			}
			
			catch (Exception e)
			{
				// If any other exception is thrown test fails 
				fail();
			}
		}
		
		
		/*
		 * GETCUMULATIVEPERCENTAGE TESTS
		 */
		
		/* Test: validCumulativePercentage
	     * Description: Checks if function returns an array of cumulative percentages that are expected
	     * Expected Return: Valid array that is the average percentage of each value in the keyed values array
	     */
		@Test
		public void validCumulativePercentage() {
			KeyedValues actual = DataUtilities.getCumulativePercentages(myKeyedValues);
			double expected[] = {0.0, 0.1, 0.3, 0.6, 1.0};
			for(int i = 0; i < 5; i++) {
				assertEquals(expected[i], actual.getValue(i));
			}
		}
		
		/* Test: invalidCumulativePercentageNull
	     * Description: Sets a KeyedValue object to null and passes it to the function
	     * Expected Return: IllegalArgumentException thrown
	     */
		@Test (expected = IllegalArgumentException.class)
			public void invalidCumulativePercentageNull() throws IllegalArgumentException {
				DataUtilities.getCumulativePercentages(null);
			}
		
		
		/* Test: invalidCumulativePercentage0
	     * Description: Sets a KeyedValue object to all 0's and passes it to the function
	     * Expected Return: Every value is calculated as NaN (not a number)
	     */
		@Test
		public void invalidCumulativePercentage0() throws InvalidParameterException {
			Mockery mockingContext = new Mockery();
		    KeyedValues values1 = mockingContext.mock(KeyedValues.class);
		    mockingContext.checking(new Expectations() {
		        {
		        	for(int i = 0; i < 3; i++) {
		        		allowing(values1).getKey(i); //KeyedValues object is created with keys {0,1,2} and values {0,0,0}
		        		will(returnValue(i));
		        		allowing(values1).getValue(i);
		        		will(returnValue(0));
		        	}
		        	allowing(values1).getItemCount();
		        	will(returnValue(3));
		        }
		    });
		    
		    KeyedValues actual = DataUtilities.getCumulativePercentages(values1);
		    for(int i = 0; i < 3; i++) {
				assertEquals(Double.NaN, actual.getValue(i));
			}
		}
		
		/* Test: singleNullValueTest
	     * Description: Sets a KeyedValue object to valid numbers with only one 
	     * 	value being null and the item count as -1
	     * Expected Return: Every value is calculated with the exception of the null value
	     * NEW TEST
	     */
		@Test
		public void incorrectItemCountTest() {
			Mockery mockingContext = new Mockery();
		    KeyedValues values2 = mockingContext.mock(KeyedValues.class);
		    mockingContext.checking(new Expectations() {
		    	{
		    		for(int i = 0; i < 3; i++) {
		    			 
		    			allowing(values2).getKey(i); //KeyedValues object is created with keys {0,1,2} and values {0,0,0}
		    			will(returnValue(i));
		    			allowing(values2).getValue(i);
		    			if(i == 0) {
		        			will(returnValue(1));
		        		}
		        		if(i == 1) {
		        			will(returnValue(2));
		        		}
		        		
		        		if(i == 2) {
		        			will(returnValue(2));
		        		}
		        	
		        	}
		        	allowing(values2).getItemCount();
		        	will(returnValue(-1));
		        }
		    });
		    
			KeyedValues actual = DataUtilities.getCumulativePercentages(values2);
		    double expected[] = {0.2, 0.4, 0.4};
			for(int i = 0; i < 3; i++) {
				assertEquals(expected[i], actual.getValue(i));
			}
		}
		
		
		/* Test: singleNullValueTest 
	     * Description: Sets a KeyedValue object to valid numbers with only one value being null
	     * Expected Return: Every value is calculated with the exception of the null value
	     * NEW TEST
	     */
		@Test
		public void singleNullValueTest() {
			Mockery mockingContext = new Mockery();
		    KeyedValues values2 = mockingContext.mock(KeyedValues.class);
		    mockingContext.checking(new Expectations() {
		    	{
		    		for(int i = 0; i < 3; i++) {
		 
		    			allowing(values2).getKey(i); //KeyedValues object is created with keys {0,1,2} and values {0,0,0}
		    			will(returnValue(i));
		    			allowing(values2).getValue(i);
		    			if(i == 0) {
		        			will(returnValue(1));
		        		}
		        		if(i == 1) {
		        			will(returnValue(null));
		        		}
		        		
		        		if(i == 2) {
		        			will(returnValue(4));
		        		}
		        	
		        	}
		        	allowing(values2).getItemCount();
		        	will(returnValue(3));
		        }
		    });
		    
			KeyedValues actual = DataUtilities.getCumulativePercentages(values2);
		    double expected[] = {0.2, 0.0, 0.8};
			for(int i = 0; i < 3; i++) {
				assertEquals(expected[i], actual.getValue(i));
			}
		}
}
