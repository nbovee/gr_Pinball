/*
 * Purpose: Data Structure and Algorithms Lab 06 Problem 0
 * Status: Complete and thoroughly tested
 * Last update: 10/09/19
 * Submitted:  10/09/19
 * Comment: test suite and sample run attached
 * @author: Nick Bovee
 * @version: 2019.10.09
 */
public class QueueCRAB<E> implements QueueInterface<E>
{

    protected int numItems;
    protected int front;
    protected int back;

    protected E[] q;

    public QueueCRAB()
    {
        numItems = 0;
        front = 0;
        back = 0;

        q = (E[]) new Object[3];
    }

    public boolean isEmpty()
    {
        return numItems == 0;
    }

    public void enqueue(E item) throws QueueException
    {
        ensureCap();
        q[back] = item;
        back = (back + 1) % q.length;
        numItems++;


    }

    protected void ensureCap()
    {
        if(front == back && numItems > 0)
        {
            E[] w = (E[]) new Object[q.length*2];
            int curr = front;
            for(int i = 0; i < numItems; i++)
            {
                w[i] = q[curr];
                curr = (curr + 1) % q.length;
            }
            q = w;
            front = 0;
            back = numItems;
        }
    }

    public E dequeue() throws QueueException
    {
        if(numItems != 0)
        {
            E temp = q[front];
            q[front] = null;
            front = (front + 1) % q.length;
            numItems--;
            return temp;
        }
        else
        {
            throw new QueueException("Queue is empty");

        }
    }

    public void dequeueAll()
    {
        numItems = 0;
        front = 0;
        back = 0;

        q = (E[]) new Object[10];
    }

    public E peek() throws QueueException
    {
        if(numItems != 0)
        {
            return q[front];
        }
        else
        {
            throw new QueueException("Queue is empty");
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int curr = front;
        for(int i = 0; i< numItems; i++)
        {
            sb.append(q[curr] + ", ");
            curr = (curr + 1) % numItems;
        }
        if(numItems != 0)
        {
            int trim = sb.length();
            sb.delete(trim-2, trim);
        }


        return sb.toString();
    }

}
