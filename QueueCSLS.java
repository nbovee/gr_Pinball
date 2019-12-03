/*
 * Purpose: Data Structure and Algorithms Project: CSLS-based Queue built on the QueueInterface.
 * Status: Complete and thoroughly tested
 * Last update: 11/24/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.11.24
 */

public class QueueCSLS<T> implements QueueInterface<T> {

    private Node<T> tail;

    /**
     * Returns whether the Queue is empty or not.
     * @return True if the queue is empty; False otherwise.
     */
    public boolean isEmpty()
    {
        return tail == null;
    }

    /**
     * Enqueues an Item to the back of the Queue and updates the tail.
     * @param item The item to be enqueued.
     */
    public void enqueue(T item) // appends the passed item to the end of the collection
    {
        if(tail == null)
        {
            tail = new Node<T>(item);
            tail.setNext(tail);
        }
        else
        {
            tail.setNext(new Node<T>(item, tail.getNext()));
            tail = tail.getNext();
        }
    }

    /**
     * Dequeues the item at the front of the Queue.
     * @throws QueueException when attempting to dequeue from an empty queue.
     * @return The dequeued item.
     */
    public T dequeue() throws QueueException
    {
        T dequeued = null;

        if(tail == null)
        {
            throw new QueueException("Queue exception at dequeue");
        }
        else if(tail.getNext() == tail)
        {
            dequeued = tail.getItem();
            tail = null;
        }
        else
        {
            Node<T> front = tail.getNext();
            dequeued = front.getItem();
            tail.setNext(front.getNext());
        }

        return dequeued;
    }

    /**
     * Dequeues all items in the queue.
     */
    public void dequeueAll()
    {
        tail = null;
    }

    /**
     * Returns whatever item is at the front of the Queue.
     * @throws QueueException when attempting to peek at an empty queue.
     * @return The item at the front.
     */
    public T peek() throws QueueException
    {
        if(tail == null)
        {
            throw new QueueException("Queue exception at peek");
        }

        return tail.getNext().getItem();
    }

    /**
     * Returns a String listing of the items in the Queue (in order).
     * @return The listing.
     */
    public String toString() //collects and returns a String representation of the collection in order from first to last
    {
        Node curr = tail;
        StringBuilder str = new StringBuilder();

        if(curr != null)
        {
            curr = curr.getNext();
            str.append(curr.getItem());
            while(curr != tail)
            {
                curr = curr.getNext();
                str.append("\n");
                str.append(curr.getItem());
            }
        }
        return str.toString();
    }
}
