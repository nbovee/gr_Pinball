/*
 * Purpose: Data Structure and Algorithms Lab 5 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 10/2/19
 * Submitted:  10/2/19
 * Comment: tested but I forgot to save to file.
 * @author: Nick Bovee
 * @version: 2019.10.02
 */

import java.lang.StringBuilder;

public class StackSLS<T> implements StackInterface
{

    Node head;

    public StackSLS()
    {
        head = null;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public void popAll()
    {
        head = null;
    }

    public void push(Object newItem) throws StackException
    {
        Node temp = new Node(newItem, head);
        head = temp;
    }

    @SuppressWarnings("unchecked")
    public T pop() throws StackException
    {
        if(head != null)
        {
            Object temp = head.getItem();
            head = head.getNext();
            return (T) temp;
        }
        else
            throw new StackException("Stack is empty.");
    }

    @SuppressWarnings("unchecked")
    public T peek() throws StackException
    {
        if (head != null)
            return (T) head.getItem();

        else
            throw new StackException("Stack is empty.");
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while(curr != null)
        {
            sb.append(curr.getItem() +", ");
            curr = curr.getNext();
        }
        int trim = sb.length();
        if(trim > 0)
            sb.delete(trim-2,trim);
        return sb.toString();

    }

}

