// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface<E>
{
    boolean isEmpty();
    int size();
    void add(int index, E item)
    throws ListIndexOutOfBoundsException;
    E get(int index)
    throws ListIndexOutOfBoundsException;
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
}  // end ListInterface
