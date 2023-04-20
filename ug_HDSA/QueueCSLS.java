// Nick Bovee

import java.util.*;
import java.io.*;

public class QueueCSLS<E> implements QueueInterface<E>
{
    private Node<E> tail;  // if non-empty reference to node containing last item in list; null otherwise

    public QueueCSLS()
    {

    }

    public boolean isEmpty()
    {
        return tail == null;
    }

    public void enqueue(E item) throws QueueException// appends the passed item to the end of the collection
    {
        Node<E> app;
        if(tail== null)
        {
            app = new Node(item);
            app.setNext(app);
        }
        else
        {
            app = new Node(item,tail.getNext());
            tail.setNext(app);
        }
        tail = app;
    }

    public E dequeue() throws QueueException
    {
        if(tail != null)
        {
            Node<E> temp = tail.getNext();
            if(temp == tail)
            {
                tail = null;
            }
            else
            {

                tail.setNext(temp.getNext());
            }

            return temp.getItem();
        }
        else
        {
            throw new QueueException("Queue is empty");
        }
    }

    public void dequeueAll()
    {
        tail = null;
    }

    public E peek() throws QueueException
    {
        if(tail != null)
        {
            return tail.getItem();
        }
        else
        {
            throw new QueueException("Queue is empty");
        }
    }

    public String toString() //collects and returns a String representation of the collection in order from first to last
    {
        StringBuilder sb = new StringBuilder();
        Node<E> curr = tail;
        if(tail != null)
        {
            do {
                curr = curr.getNext();
                sb.append(curr.getItem() + " ");

            } while(curr!=tail);
        }
        return sb.toString();
    }

    public void reverse() // reverses the content of the collection without the use of any
    // additional dynamically allocated memory (no new)
    {
        if(tail != null)
        {
            Node<E> behind = tail;
            Node<E> curr = behind.getNext();
            Node<E> temp;
            do
            {
                temp = curr.getNext();
                curr.setNext(behind);
                behind = curr;
                curr = temp;

            } while(curr != tail);
            temp = curr.getNext();
            curr.setNext(behind);
            tail = temp;
        }
    }
}
