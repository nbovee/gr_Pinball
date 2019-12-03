/*
 * Purpose: Data Structure and Algorithms Project: ListAB Class
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */


// ********************************************************
// Array-based implementation of the ADT list.
// *********************************************************
public class ListAB<E> implements ListInterface<E>
{

    private static final int MAX_LIST = 3;
    protected Object[] items;  // an array of list items
    protected int numItems;  // number of items in list

    public ListAB()
    {
        items = (E[]) new Object[MAX_LIST];
        numItems = 0;
    }  // end default constructor
    
	/**
     * Returns if the List is empty.
     * @return boolean The state of the list.
     */
    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

	/**
     * Returns the current size of the list.
     * @return s The size of the list.
     */
    public int size()
    {
        return numItems;
    }  // end size

	/**
     * Clears the List.
     */
    public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (E[]) new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll

	/**
     * Adds an item to the list.
     * @param index The index to add the new item too
     * @param item The item to add to this location
     */
    public void add(int index, E item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems==items.length) //fixes implementation errors //fixes programming style
        {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

	/**
     * Returns an item from the list.
     * @param index the index to retrieve an item from.
     * @return item the item in the given index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return (E) items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get

	/**
     * Removes an item from the list.
     * @param index the index to remove.
     */
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
            numItems--;
            items[numItems] = null; //fixes memory leak
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove
}

