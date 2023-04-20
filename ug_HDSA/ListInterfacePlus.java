// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterfacePlus
{
    boolean isEmpty();
    int size();
    void add(int index, Object item)
    throws ListIndexOutOfBoundsException;
    Object get(int index)
    throws ListIndexOutOfBoundsException;
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
    java.util.ListIterator listIterator();
}  // end ListInterfacePlus


