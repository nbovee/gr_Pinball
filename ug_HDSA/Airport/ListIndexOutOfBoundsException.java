/*
 * Purpose: Data Structure and Algorithms Project:  ListIndexOutOfBoundsException
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */
public class ListIndexOutOfBoundsException extends IndexOutOfBoundsException
{    
	/**
     * Creates a new ListIndexOutOfBoundsException with a description of what happened.
     * @param s The description of what happened.
     */
    public ListIndexOutOfBoundsException(String s)
    {
        super(s);
    } // end constructor
} // end ListIndexOutOfBoundsException
