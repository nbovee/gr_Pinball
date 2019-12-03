/*
 * Purpose: Data Structure and Algorithms Lab 02 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 09/23/19
 * Submitted:  09/17/19
 * Comment: test suite and sample run attached
 * @author: Nick Bovee
 * @version: 2019.09.23
 */


import java.util.*;

public class ListRAB<E> extends ListAB<E> implements ListInterface<E>
{
//	private int assignments = 0;

    public ListRAB()
    {
        super();
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException //revised add
    {
        if (index >= 0 && index <= numItems)
        {
            if (numItems==items.length) //fixes implementation errors //fixes programming style
            {
                int newSize =(int)(items.length * 3 / 2);
                Object[] newArray = (E[]) new Object[newSize];

                for(int i = 0, j = 0; j < items.length; i++, j++)
                {
                    if(i == index)
                    {
                        j--;
                    }
                    else
                    {
                        newArray[i] = items[j];

                    }
                }
                newArray[index] = item;
                items = newArray;
            }
            else {
                // make room for new element by shifting all items at
                // positions >= index toward the end of the
                // list (no shift if index == numItems+1)
                for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
                {
                    items[pos+1] = items[pos];
                } // end for
                // insert new item
                items[index] = item;
            }
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<numItems; i++)
        {
            builder.append(items[i] + " ");
        }
        return builder.toString();
    }

    public void reverse()
    {
        //below is the most efficient reverse method tested. See conclusions.
        reverseMemDirect();
    }

//private void reverseIPDirect()
//{
//   for(int i = 0; i < numItems/2; i++)
//  {
//     Object temp = items[i];
//    items[i] = items[numItems-i-1];
//   items[numItems-i-1] = temp;
// // assignments += 3;//3 values
//}
//}

    private void reverseMemDirect()
    {
        Object[] newItems = (E[]) new Object[numItems];
        for(int i = 0; i<numItems; i++)
        {
            newItems[i] = items[numItems-i-1];
            //assignments++;//1 values
        }
        items = newItems;
    }

    private void resize()
    {
        int newSize =(int)(items.length * 3 / 2);
        Object[] newArray = new Object[newSize];
        for(int i = 0; i < items.length; i++)
        {
            newArray[i] = items[i];
        }
        items = newArray;

    }
}

