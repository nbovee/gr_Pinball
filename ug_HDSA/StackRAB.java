/*
 * Purpose: Data Structure and Algorithms Lab 5 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 10/2/19
 * Submitted:  10/2/19
 * Comment: tested but I forgot to attach.
 * @author: Nick Bovee
 * @version: 2019.10.02
 */
import java.lang.StringBuilder;

public class StackRAB<T> implements StackInterface
{

    private T[] arrayVar;  //declaration
    private int head;


    @SuppressWarnings("unchecked")
    private StackRAB(int length)
    {
        arrayVar = (T[]) new Object[length];
        head = 0;
    }

    public StackRAB()
    {
        this(10);
    }

    public boolean isEmpty()
    {
        return head == 0;
    }


    @SuppressWarnings("unchecked")
    public void popAll()
    {
        arrayVar = (T[]) new Object[10];
        head = 0;
    }

    @SuppressWarnings("unchecked")
    public void push(Object newItem) throws StackException
    {
        if(head == arrayVar.length)
        {
            T[] tempArr = (T[]) new Object[2*arrayVar.length];
            for(int i = 0; i<arrayVar.length; i++)
            {
                tempArr[i] = arrayVar[i];
            }
            arrayVar = tempArr;
        }
        arrayVar[head] = (T) newItem;
        head++;
    }

    public T pop() throws StackException
    {
        if(head != 0)
        {
            T temp = arrayVar[head-1];
            arrayVar[--head] = null;
            return temp;
        }
        else
            throw new StackException("Stack is empty.");
    }

    public T peek() throws StackException
    {
        if(head!=0)
            return arrayVar[head-1];
        else
            throw new StackException("Stack is empty.");
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = head-1; i >=0; i--)
        {
            Object t = arrayVar[i];
            sb.append(t + ", ");

        }
        int temp = sb.length();
        if(temp>0)
            sb.delete(temp-2,temp);
        return sb.toString();
    }

}
