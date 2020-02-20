/*
 * Purpose: Data Structure and Algorithms Lab 4 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 10/1/19
 * Submitted:  10/1/19
 * Comment: test suite and sample run attached
 * @author: Nick Bovee
 * @version: 2019.10.01
 */
public class DNode extends Node
{
    private DNode back;

    public DNode(Object newItem)
    {
        super(newItem);
        this.setNext(this);
        back = this;
    } // end constructor

    public DNode(Object newItem, DNode nextNode, DNode backNode)
    {
        super(newItem, (Node) nextNode);
        back = backNode;
    } // end constructor

    public DNode getNext()
    {
        return (DNode) super.getNext();
    }

    public void setBack(DNode backNode)
    {
        back = backNode;
    } // end setNext

    public DNode getBack()
    {
        return back;
    } // end getNext
} // end class Node


