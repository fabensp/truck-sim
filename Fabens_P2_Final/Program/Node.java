
/**
 * Data holder for a single node
 *
 * @author Peter Fabens
 * @version 4/5/2022
 */
public class Node<E extends Comparable<E>>
{
    private E val = null;
    private Node<E> next = null;
    
    /**
     * Constructor that stores a value in the node.
     * 
     * @param value value to store
     */
    public Node (E val, Node<E> next){
        this.val = val;
        this.next = next;
    }
    
    /**
     * Value getter.
     * 
     * @returns stored value
     */
    public E val() {
        return val;
    }
    
    /**
     * Value setter.
     * 
     * @param val value to store.
     */
    public void set_val(E val) {
        this.val = val;
    }
    
    /**
     * Next reference getter.
     * 
     * @returns stored reference
     */
    public Node<E> next() {
        return next;
    }
    
    /**
     * Next reference setter.
     * 
     * @param next reference to store.
     */
    public void set_next(Node<E> next) {
        this.next = next;
    }
}