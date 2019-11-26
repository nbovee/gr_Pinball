/*
 * Purpose: Data Structure and Algorithms Project: Interface for Queues.
 * Status: Complete and thoroughly tested
 * Last update: 11/24/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.11.24
 */

public interface QueueInterface<T> {

    /**
     * Returns whether the Queue is empty or not.
     * @return True if the queue is empty; False otherwise.
     */
    public boolean isEmpty();
    // Determines whether a queue is empty.
    // Precondition: None.
    // Postcondition: Returns true if the queue is empty;
    // otherwise returns false.

    /**
     * Enqueues an item to the back of the Queue.
     * @param newItem The item to be enqueued.
     * @throws QueueException when the item cannot be enqueued for some reason.
     */
    public void enqueue(T newItem) throws QueueException;
    // Adds an item at the back of a queue.
    // Precondition: newItem is the item to be inserted.
    // Postcondition: If the operation was successful, newItem
    // is at the back of the queue. Some implementations
    // may throw QueueException if newItem cannot be added
    // to the queue.

    /**
     * Dequeues the item from the front of the queue.
     * @throws QueueException when attempting to dequeue from an empty queue.
     * @return The dequeued item.
     */
    public T dequeue() throws QueueException;
    // Retrieves and removes the front of a queue.
    // Precondition: None.
    // Postcondition: If the queue is not empty, the item that
    // was added to the queue earliest is removed. If the queue is
    // empty, the operation is impossible and QueueException is thrown.

    /**
     * Dequeues all items from the queue.
     */
    public void dequeueAll();
    // Removes all items of a queue.
    // Precondition: None.
    // Postcondition: The queue is empty.

    /**
     * Returns the item at the front of the queue.
     * @throws QueueException when attempting to peek at an empty queue.
     * @return The item at the front.
     */
    public T peek() throws QueueException;
    // Retrieves the item at the front of a queue.
    // Precondition: None.
    // Postcondition: If the queue is not empty, the item
    // that was added to the queue earliest is returned.
    // If the queue is empty, the operation is impossible
    // and QueueException is thrown.

    /**
     * Returns a String representation of the Queue.
     * @return The String representation.
     */
    public String toString();
}  // end QueueInterface

