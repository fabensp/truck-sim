
/**
 * A shipment of goods.
 *
 * @author Peter Fabens
 * @version 4/6/2022
 */
public class Shipment implements Comparable<Shipment>
{
    private String contents = "ACME™ Brand Anvils - Lift With Your Legs!";
    private static int SHIPMENT_COUNT = 0; // total number of shipments
    private int size; // shipment size
    private int id; // shipment id
    
    /**
     * Constructor for objects of class Shipment
     */
    public Shipment(int size)
    {
        this.size = size; // generates a random num between 0-3, rounds down, then adds one so that possible values are 1, 2, and 3
        id = SHIPMENT_COUNT; // as shipments are created, they figure out for themselves what ID to take on
        SHIPMENT_COUNT++;
    }
    
    /**
     * Returns size of this shipment
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns id of this shipment
     */
    public int id() {
        return id;
    }
    
    /**
     * Returns the size difference between this and another shipment
     */
    public int compareTo(Shipment s)
    {
        return size - s.size();
    }
    
    public String toString()
    {
        return "[i" + id + ".s" + size + "]";
    }
}
