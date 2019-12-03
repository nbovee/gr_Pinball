/*
 * Purpose: Data Structure and Algorithms Project: Airport Exception
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */
public class AirportException extends RuntimeException {
	/**
     * Creates a new AirportException with a description of what happened.
     * @param s The description of what happened.
     */
    public AirportException(String s) {
        super(s);
    } // end constructor
}
