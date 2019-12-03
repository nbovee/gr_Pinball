/*
 * Purpose: Data Structure and Algorithms Project: Exception for Queues to throw.
 * Status: Complete and thoroughly tested
 * Last update: 11/24/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.11.24
 */

public class QueueException extends RuntimeException {

    /**
     * Creates a new QueueException with a description of what happened.
     * @param s The description of what happened.
     */
    public QueueException(String s) {
        super(s);
    }  // end constructor
}  // end QueueException
