
/**
 * Stack data structure
 *
 * @author Peter Fabens
 * @version 4/5/2022
 */
public class Stack<E extends Comparable<E>>
{
    Node<E> top;
    
    /**
     * Construct empty stack
     */
    public Stack () {
        top = null;
    }
    
    /**
     * Returns whether there are items in stack
     */
    public boolean empty()
    {
        return top == null;
    }
    
    /**
     * Add item to the top of the stack.
     * 
     * @param val item to put on stack
     */
    public void push (E val) {
        Node<E> new_node = new Node<E>(val, top);
        top = new_node;
    }
    
    /**
     * Remove the item at the top of the stack.
     * 
     * @returns item removed from top of stack.
     */
    public E pull () {
        if (top == null) throw new IndexOutOfBoundsException ("No items to pull");
        Node<E> ref_node = top;
        top = top.next();
        return ref_node.val();
    }
    
    /**
     * Returns the the item at the top of the stack without removing it.
     */
    public E peek ()
    {
        return top.val();
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
