
/**
 * Generic LinkedList
 *
 * @author Peter Fabens
 * @version 4/6/2022
 */
public class LinkedList<E extends Comparable<E>>
{
    private Node<E> head;

    /**
     * Default Constructor
     */
    public LinkedList()
    {
        head = null;
    }

    public void add (E val)
    {
        Node<E> new_node = new Node<E>(val, null);
        if (head == null)
        {
            head = new_node;
            return;
        }
        Node<E> ref_node = head;
        while (ref_node.next() != null)
        {
            ref_node = ref_node.next();
        }
        ref_node.set_next(new_node);
    }
    
    public E get (int index)
    {
        Node<E> ref_node = head;
        for (int cnt = 0; cnt < index; cnt++)
        {
            if (ref_node == null) throw new IndexOutOfBoundsException("Index " + cnt + " is too large for size " + cnt);
            ref_node = ref_node.next();
        }
        return ref_node.val();
    }
    
    public void set (int index, E val)
    {
        Node<E> ref_node = head;
        for (int cnt = 0; cnt < index; cnt++)
        {
            if (ref_node == null) throw new IndexOutOfBoundsException("Index " + cnt + " is too large for size " + cnt);
            ref_node = ref_node.next();
        }
        ref_node.set_val(val);
    }
    
    public E remove (int index)
    {
        Node<E> ref_node = head;
        E out = ref_node.val();
        if (index == 0 && size() == 1){
            out = head.val();
            head = null;
        } else if (index == 0)
        {
            out = head.val();
            head = head.next();
        }
        for (int cnt = 0; cnt < index - 1; cnt++)
        {
            if (ref_node == null) throw new IndexOutOfBoundsException("Index is not in list.");
            ref_node = ref_node.next();
        }
        if (ref_node.next() != null) ref_node.set_next(ref_node.next().next());
        return out;
    }
    
    /**
     * Returns number of items in the data structure
     */
    public int size()
    {
        Node<E> ref_node = head;
        int num = 0;
        while (ref_node != null)
        {
            num++;
            ref_node = ref_node.next();
        }
        return num;
    }
}
