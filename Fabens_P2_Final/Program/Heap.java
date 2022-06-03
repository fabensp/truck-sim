/**
 * Heap, converted from lab assignment, which in turn used clss notes
 *
 * @author Peter Fabens
 * @version 4/14/2022
 */
public class Heap<E extends Comparable<E>>
{
    LinkedList<E> list;
    
    /**
     * Default Constructor
     */
    public Heap()
    {
        list = new LinkedList<E>();        
    }
    
    /**
     * Fully reorders the heap, since values change as trucks move
     */
    public void rebuild()
    {
        Heap<E> old_heap = dupe(); // clone heap
        list = new LinkedList<E>(); // wipe heap
        while(!old_heap.empty())
        {
            add(old_heap.pull()); // put everything back in and let it percolate
        }
    }
    
    public boolean empty(){return (list.size() == 0);}
    
    /**
     * Read the top item off the heap and return it, without removing it from heap.
     */
    public E peek()
    {
        return list.get(0);
    }
    
    /**
     * Take the top item off the heap and return it
     */
    public E pull()
    {
        E top = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        if (!empty()) percolate_down(0);
        return top;
    }
    
    /**
     * Add an item to the heap
     * 
     * @param item item to add
     * @returns whether it worked
     */
    public boolean add(E item)
    {
        try{
            list.add(item);
            percolate_up(list.size() - 1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Return a LinkedList representation of the heap data, sorted
     */
    public LinkedList<E> sort(){
        Heap<E> unsorted = dupe();
        LinkedList<E> sorted = new LinkedList<E>();
        while (!unsorted.empty())
        {
            System.out.println(sorted.size());
            sorted.add(unsorted.pull());
        }
        return sorted;
    }
    
    /**
     * Prof. Pfaffmann's code for percolating a node up the heap.
     * 
     * @param nodeIndex index of node to percolate
     */
    public void percolate_up(int nodeIndex)
    {
            while (nodeIndex > 0) {
    
            int parentIndex = (nodeIndex - 1) / 2;
            
            if (list.get(nodeIndex).compareTo(list.get(parentIndex)) < 0) {
            
                return;
            } else {
                
                E temp;
                
                temp = list.get(nodeIndex);
                list.set(nodeIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
                
                nodeIndex = parentIndex;
            }
        }
    }
    
    /**
     * Only slightly modified from class notes, so credit goes to Prof. Pfaffmann
     * 
     * @param nodeIndex index of node to percolate down through heap
     */
    public void percolate_down(int nodeIndex)
    {
        int childIndex = 2 * nodeIndex + 1;
        E value = list.get(nodeIndex);
        
        while (childIndex < list.size()) {
            
            // Find the max among the node and all the node's children
            E maxValue = value;
            int maxIndex = -1;
            
            for (int i = 0; i < 2 && i + childIndex < list.size(); i++) {
                if (list.get(i + childIndex).compareTo(maxValue) > 0) {
                
                  maxValue = list.get(i + childIndex);
                  maxIndex = i + childIndex;
                }
            }
            
            if (maxValue == value) {
                return;
            } else {
                
                E temp;
                
                temp = list.get(nodeIndex);
                list.set(nodeIndex, list.get(maxIndex));
                list.set(maxIndex, temp);
                
                nodeIndex  = maxIndex;
                childIndex = 2 * nodeIndex + 1;
            }
        }
    }
    
    /**
     * Does a deep copy of the Heap, which is inefficient but sometimes useful
     */
    public Heap<E> dupe() {
        Heap<E> copy = new Heap<E>();
        for (int i = 0; i < list.size(); i++)
        {
            copy.add(list.get(i));
        }
        return copy;
    }
    
    /**
     * Print heap in space-separated values
     */
    public String toString()
    {
        String out = "";
        for (int cnt = 0; cnt < list.size(); cnt++)
        {
            out += " " + list.get(cnt);
        }
        return out.trim();
    }
}