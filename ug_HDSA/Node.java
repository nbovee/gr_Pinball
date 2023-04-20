//please note that this code is different from the textbook code, because the data is encapsulated!

public class Node<E>
{
    private E item;
    private Node next;

    public Node(E newItem)
    {
        item = newItem;
        next = null;
    } // end constructor

    public Node(E newItem, Node nextNode)
    {
        item = newItem;
        next = nextNode;
    } // end constructor

    public void setItem(E newItem)
    {
        item = newItem;
    } // end setItem

    public E getItem()
    {
        return item;
    } // end getItem

    public void setNext(Node nextNode)
    {
        next = nextNode;
    } // end setNext

    public Node getNext()
    {
        return next;
    } // end getNext
} // end class Node


