/*
 * Purpose: Data Structure and Algorithms Lab 04 Problem 1
 * Status: Complete and Tested
 * Last update: 10/11/19
 * Submitted:  -
 * Comment: test suite and sample run attached
 * @author: Nick Bovee
 * @version: 2019.10.11
 */

import java.util.*;

public class ListCDLS implements ListInterfacePlus
{
    // reference to linked list of items
    private DNode head;
    private int numItems;

    public ListCDLS()
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

    private DNode find(int index)
    {
        index = (index + numItems) % numItems; //allows index to be asked to find -1 and n, and return n-1 and 0 respectively.
        DNode curr = head;
        if(index <= numItems/2)
        {
            for (int skip = 0; skip < index; skip++)
            {
                curr = curr.getNext();
            } // end for

        }
        else
        {
            int negIndex = index -numItems;
            for(int skip = 0; skip > negIndex; skip--)
            {
                curr = curr.getBack();
            }
        }
        return curr;
    } // end find

    public Object get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < this.size())
        {
            // get reference to node, then data in node
            DNode curr = find(index);
            Object dataItem = curr.getItem();
            return dataItem;
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on get");
        } // end if
    } // end get

    public void add(int index, Object item)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index <= numItems)
        {
            if (head == null)
            {
                head = new DNode(item);
            }
            else
            {
                // insert the new node containing item at beginning
                DNode next = find(index);
                DNode back =  head.getBack();
                DNode newDNode = new DNode(item, next, back);

                next.setBack(newDNode);
                back.setNext(newDNode);
                if(index == 0)
                {
                    head = newDNode;
                }
            }
            numItems++;
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on add");
        } // end if
    }  // end add

    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            DNode rem = find(index);
            DNode next = rem.getNext();
            DNode back = rem.getBack();
            next.setBack(back);
            back.setNext(next);
            if(index == 0)
            {
                head = next;
            }
            numItems--;
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on remove");
        } // end if
    }  // end remove

    public void removeAll()
    {
        // setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection
        head = null;
        numItems = 0;
    } // end removeAll
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        DNode curr = head;
        for(int i = 0; i <numItems; i++)
        {
            sb.append(curr.getItem() +" ");
            curr = curr.getNext();
        }
        return sb.toString();
    }

    public ListIterator listIterator()
    {
        return new CDLSIterator();
    }

    //being inner class for ListIterator
	@SuppressWarnings("unchecked")
    private class CDLSIterator implements ListIterator<Object>
    {
        DNode cursor;
        DNode last; //instead of flagging for last methods, etc, can track the node used itself and probe and/or compare to cursor to figure out direction of travel
        int index;

        public CDLSIterator()
        {
            cursor = ListCDLS.this.head;
            last = null;
            index = 0;
        }

        public boolean hasNext()
        {
            return index != numItems;
        }

        public boolean hasPrevious()
        {
            return index != 0;
        }

        public Object next()
        {
            if(index  != ListCDLS.this.numItems)
            {
                Object temp = cursor.getItem();
                last = cursor;
                cursor = cursor.getNext();
                index++;
                return temp;
            }
            else
            {
                throw new NoSuchElementException("reached end of list");
            }
        }

        public Object previous()
        {
            if(index != 0)
            {
                cursor = cursor.getBack();
                Object result = cursor.getItem();
                last = cursor;
                index--;
                return result;
            }
            else
            {
                throw new NoSuchElementException("reached beginning of list");
            }

        }

        public int nextIndex()
        {
            return index;
        }
	
        public int previousIndex()
        {
            return index - 1;
        }

        public void add(Object toAdd) //adjusts lastCall
        {
	    DNode temp;
	    if(cursor == null)
	    {
		    temp = new DNode(toAdd);
		    ListCDLS.this.head = temp;
		    cursor = temp;
	    }
	    else
	    {
            DNode back = cursor.getBack();
            temp = new DNode(toAdd, cursor, back);
            cursor.setBack(temp);
            back.setNext(temp);
	    }
            numItems++;
            index++;
            last = null; //not used here, but must reset to avoid inconsistent states
        }

        public void remove()
        {
            if(last != null)
            {
                DNode next = last.getNext();
                DNode back = last.getBack();

                next.setBack(back);
                back.setNext(next);

                if(cursor == last)
                {
                    cursor = next;
                }
                else
                {
                    index--;
                }
                last = null;
                numItems--;
            }
            else
            {
                throw new IllegalStateException();
            }
        }

        public void set(Object newObject)
        {
            if(last != null)
            {
                last.setItem(newObject);
            }
            else
            {
                throw new IllegalStateException("ListIterator is not in correct state");
            }

        }
    }
} // end ListReferenceBased
