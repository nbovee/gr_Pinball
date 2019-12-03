/*
 * Purpose: Data Structure and Algorithms Project: List Interface
 * Status: Complete and thoroughly tested
 * Last update: 12/03/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Nicholas Bovee
 * @version: 2019.12.03
 */
public interface ListInterface<E>
{
	/**
     * Returns if the List is empty.
     * @return boolean The state of the list.
     */
    boolean isEmpty();
	/**
     * Returns the current size of the list.
     * @return s The size of the list.
     */
    int size();
    
	/**
     * Adds an item from the list.
     * @param index The index to add the new item too
     * @param item The item to add to this location
     */
    void add(int index, E item)
    throws ListIndexOutOfBoundsException;
    
	/**
     * Returns an item from the list.
     * @param index the index to retrieve an item from.
     * @return item the item in the given index.
     */
    E get(int index)
    throws ListIndexOutOfBoundsException;
    
	/**
     * Removes an item from the list.
     * @param index the index to remove.
     */
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    
	/**
     * Clears the List.
     */
    void removeAll();
}  // end ListInterface
