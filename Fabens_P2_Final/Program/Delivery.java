import java.util.Random;

/**
 * Like a node but for manifests. Stores info about a single trip.
 *
 * @author Peter Fabens
 * @version 
 */
public class Delivery implements Comparable<Delivery>
{
    private Warehouse pickup; // where the shipment associated with this delivery is starting from
    private Shipment item; // shipment to be delivered
    private Warehouse dropoff; // destination for shipment
    private Truck carrier; // truck that will be transporting this package
    private static Random del_rand = new Random(636323);
    
    /**
     * Default Constructor
     */
    public Delivery(Truck c, LinkedList<Warehouse> map)
    {
        carrier = c;
        pickup = map.get(del_rand.nextInt(map.size()));
        dropoff = null;
        while (dropoff == null || dropoff.equals(pickup))
            dropoff = map.get(del_rand.nextInt(map.size()));
        item = pickup.create_shipment(del_rand.nextInt(c.size()) + 1); // only make shipments truck is big enough for, send them to depot
    }
    
    /**
     * Returns pickup location
     */
    public Warehouse pickup()
    {
        return pickup;
    }
    
    /**
     * Returns dropoff location
     */
    public Warehouse dropoff()
    {
        return dropoff;
    }
    
    /**
     * Returns carrier
     */
    public Truck carrier()
    {
        return carrier;
    }
    
    public Shipment item() {
        return item;
    }
    
    /**
     * Compare two deliveries, using the item's id and the carrier's position relative to pickup
     */
    public int compareTo(Delivery d)
    {
        int[] pickup_pos = pickup.pos(); // positions of this delivery
        int[] truck_pos = carrier.pos();
        int[] d_pickup_pos = d.pickup().pos(); // positions of d
        int[] d_truck_pos = d.carrier().pos();
        int distance = (int)Math.hypot((truck_pos[0] - pickup_pos[0]), (truck_pos[1] - pickup_pos[1]));
        int d_distance = (int)Math.hypot((d_truck_pos[0] - d_pickup_pos[0]), (d_truck_pos[1] - d_pickup_pos[1]));
        if (distance == d_distance) // if delivery distances are equal compare by age
            return item.id() - d.item().id(); // if this id is larger than d id, this one is newer -> more important -> higher value
        return d_distance - distance; // if d_distance is larger than this distance, this one is more important -> higher value
    }
    
    public String toString()
    {
        return "" + pickup.size() + "." + item.id() + "." + dropoff.size();
    }
}
