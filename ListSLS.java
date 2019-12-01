/*
 * Purpose: Data Structure and Algorithms Lab 05 Problem 4
 * Status: Complete and thoroughly tested
 * Last update: 10/08/19
 * Submitted:  10/08/19
 * Comment: not fully tested
 * @author: Nick Bovee
 * @version: 2019.10.08
 */


// Please note that this code is slightly different from the textbook code
//to reflect the fact that the Node class is implemented using data encapsulation


// ****************************************************
// Reference-based implementation of ADT list.
// ****************************************************
public class ListSLS<E> implements ListInterface<E>
{
    // reference to linked list of items
    private Node<E> head;
    private int numItems; // number of items in list

    public ListSLS()
    {
        numItems = 0;
        head = null;
    }  // end default constructor

    public boolean isEmpty()
    {
        return numItems == 0;
    }  // end isEmpty

    public int size()
    {
        return numItems;
    }  // end size

    private Node<E> find(int index)
    {
        // --------------------------------------------------
        // Locates a specified node in a linked list.
        // Precondition: index is the number of the desired
        // node. Assumes that 0 <= index <= numItems
        // Postcondition: Returns a reference to the desired
        // node.
        // --------------------------------------------------
        Node<E> curr = head;
        for (int skip = 0; skip < index; skip++)
        {
            curr = curr.getNext();
        } // end for
        return curr;
    } // end find

    @SuppressWarnings("unchecked")
    public E get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // get reference to node, then data in node
            Node<E> curr = find(index);
            E dataItem = curr.getItem();
            return dataItem;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on get");
        } // end if
    } // end get

    public E find(E toFind)
    {
	E result = null;
	//TO COMPLETEL
	return result;
    }
  
    public void add(E item)
    {
	add(numItems,item);
    }

    public void add(int index, E item)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems+1)
        {
            if (index == 0)
            {
                // insert the new node containing item at
                // beginning of list
                Node<E> newNode = new Node<E>(item, head);
                head = newNode;
            }
            else
            {
                Node<E> prev = find(index-1);
                // insert the new node containing item after
                // the node that prev references
                Node<E> newNode = new Node<E>(item, prev.getNext());
                prev.setNext(newNode);
            } // end if
            numItems++;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on add");
        } // end if
    }  // end add

    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            if (index == 0)
            {
                // delete the first node from the list
                head = head.getNext();
            }
            else
            {
                Node<E> prev = find(index-1);
                // delete the node after the node that prev
                // references, save reference to node
                Node<E> curr = prev.getNext();
                prev.setNext(curr.getNext());
            } // end if
            numItems--;
        } // end if
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on remove");
        } // end if
    }   // end remove

    public void removeAll()
    {
        // setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection
        head = null;
        numItems = 0;
    } // end removeAll

} // end ListSLS


