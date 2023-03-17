/**
 * 
 */
package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;

import org.junit.Before;
import org.junit.Test;

/**
 * @author maitr
 *
 */
public class NewDataUtilTests {

	private Values2D values;
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


	
	@Test
	public void EqualArraysTest() {
		double[][] array1 = {{0,1,2}, {2,2,3}};
		assertTrue("The arrays are equal, but the function returns false", DataUtilities.equal(array1, array1));
	}
	
	@Test
	public void ArrayAisNullTest() {
		double[][] array1 = null;
		double[][] array2 = {{0,1,2}, {2,2,3}};
		assertFalse("The arrays are not equal, but the function returns true", DataUtilities.equal(array1, array2));
	}
	
	@Test
	public void ArrayBisNullTest() {
		double[][] array2 = null;
		double[][] array1 = {{0,1,2}, {2,2,3}};
		assertFalse("The arrays are not equal, but the function returns true", DataUtilities.equal(array1, array2));
	}
	
	@Test
	public void BothArrayNull() {
		double[][] array1 = null;
		assertTrue("The arrays are equal, but the function returns false", DataUtilities.equal(array1, array1));
	}
	
	@Test
	public void ArrayLengthsNotEqual() {
		double[][] array1 = {{0,1,2}};
		double[][] array2 = {{0,1,2}, {2,2,3}};
		assertFalse("The arrays are not equal, but the function returns true", DataUtilities.equal(array1, array2));
	}
	
	@Test
	public void ArrayLengthisEqualArrayNotEqual() {
		double[][] array1 = {{0,1,2}, {0,3,5}};
		double[][] array2 = {{0,1,2}, {2,2,3}};
		assertFalse("The arrays are not equal, but the function returns true", DataUtilities.equal(array1, array2));
	}
	
	//CLONE TESTS
	
	@Test (expected = IllegalArgumentException.class)
	public void cloneNull() {
		double[][] example = null;
		DataUtilities.clone(example);
	}
	
	@Test
	public void cloneCreated() {
		double[][] expected = {{0,1.6,2.7}, {0,3.9,5.5}};
		double[][] actual = DataUtilities.clone(expected);
		for(int i = 0; i < 2; i++) {
			assertArrayEquals(expected[i], actual[i], 0.0000d);
		}
	}
	
	@Test
	public void firstIndexNull() {
		double[][] expected = {{}, {0,3.9,5.5}};
		double[][] actual = DataUtilities.clone(expected);
		
		for(int i = 0; i < 2; i++) {
			assertArrayEquals(expected[i], actual[i], 0.0000d);
		}
	}
	
	// IMPROVING OLDER TESTS
	@Test (expected = IllegalArgumentException.class) 
	public void columnNull() {
		Values2D test = null;
		DataUtilities.calculateColumnTotal(test, 0);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void rowNull() {
		Values2D test = null;
		DataUtilities.calculateRowTotal(test, 0);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void numberArrayNull() {
		double[] test = null;
		DataUtilities.createNumberArray(test);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void numberArrayNull2D() {
		double[][] test = null;
		DataUtilities.createNumberArray2D(test);
	}
	
}
