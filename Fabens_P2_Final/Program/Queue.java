
/**
 * Queue data structure
 *
 * @author Peter Fabens
 * @version 4/5/2022
 */
public class Queue<E extends Comparable<E>>
{
    Node<E> top;
    
    /**
     * Construct empty queue
     */
    public Queue () {
        top = null;
    }
    
    /**
     * Add item to the end of the queue.
     * 
     * @param val item to add to queue
     */
    public void join (E val) {
        Node<E> new_node = new Node<E>(val, top);
        top = new_node;
    }
    
    /**
     * Remove the item at the front of the queue.
     * 
     * @returns item removed from front of queue.
     */
    public E next () {
        Node<E> ref_node = top;
        E out;
        
        if (ref_node == null) throw new IndexOutOfBoundsException ("No items to pull");
        else if (ref_node.next() == null)
        {
            out = top.val();
            top = null;
            return out;
        }
        
        while (ref_node.next().next() != null)
        {
            ref_node = ref_node.next();
        }
        out = ref_node.next().val();
        ref_node.set_next(null);
        return out;
    }
    
    
    /**
     * Returns number of items in the data structure
     */
    public int size()
    {
        Node<E> ref_node = top;
        int num = 0;
        while (ref_node != null)
        {
            num++;
            ref_node = ref_node.next();
        }
        return num;
    }
}
