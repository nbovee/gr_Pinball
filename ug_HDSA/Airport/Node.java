/*
 * Purpose: Data Structure and Algorithms Project: Node class, as part of Lists.
 * Status: Complete and thoroughly tested
 * Last update: 11/24/19
 * Submitted:  12/03/19
 * Comment: test suite and sample run attached
 * @author: Devyn Melendez
 * @version: 2019.11.24
 */

public class Node<T>
{
    private T item;
    private Node<T> next;

    /**
     * Creates a new Node with an item, and no Node ahead.
     * @param newItem The Node's Item.
     */
    public Node(T newItem)
    {
        item = newItem;
        next = null;
    } // end constructor

    /**
     * Creates a new Node with an item and another Node ahead of this Node.
     * @param newItem The Node's Item.
     * @param nextNode The Node after this Node.
     */
    public Node(T newItem, Node<T> nextNode)
    {
        item = newItem;
        next = nextNode;
    } // end constructor

    /**
     * Sets a new Item for this Node.
     * @param newItem The new item.
     */
    public void setItem(T newItem)
    {
        item = newItem;
    } // end setItem

    /**
     * Returns this Node's Item.
     * @return The item.
     */
    public T getItem()
    {
        return item;
    } // end getItem

    /**
     * Sets a Node to be the one ahead of this Node.
     * @param nextNode The Node ahead of this Node.
     */
    public void setNext(Node<T> nextNode)
    {
        next = nextNode;
    } // end setNext

    /**
     * Returns the Node ahead of this Node.
     * @return The Node ahead of this Node.
     */
    public Node<T> getNext()
    {
        return next;
    } // end getNext
} // end class Node
