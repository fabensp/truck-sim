import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.util.Random;

/**
 * Building that holds shipments
 *
 * @author Peter Fabens
 * @version 4/8/2022
 */
public class Warehouse implements Render, Schedule, Comparable<Warehouse>
{
    private int xpos, ypos, bays;
    LinkedList<Shipment> storage;
    private int current_empty;
    private static Random ware_rand = new Random(671429863); // for generating repeatable but pseudorandom results
    
    /**
     * Default Constructor
     */
    public Warehouse()
    {
        bays = ware_rand.nextInt(3) + 1; // generates a random num between 1-3
        xpos = ware_rand.nextInt(400) + 50;
        ypos = ware_rand.nextInt(400) + 50;
        current_empty = 0;
        storage = new LinkedList<Shipment>();
    }
    
    /**
     * Tells a truck that it can park and unload, and registers that the truck has parked
     * 
     * @returns the go-ahead for the truck to unload
     */
    public boolean park()
    {
        if (current_empty > 0)
        {
            current_empty--;
            return true;
        }
        return false;
    }
    
    /**
     * Receives a shipment from a truck delivering it
     */
    public void unload(Shipment s)
    {
        storage.add(s);
    }
    
    /**
     * Loads a shipment in to a truck
     */
    public void load(Shipment s)
    {
        int id = s.id();
        for (int cnt = 0; cnt < storage.size(); cnt++)
        {
            if (storage.get(cnt).equals(s))
            {
                storage.remove(cnt);
                return;
            }
        }
        throw new NullPointerException("Shipment " + s + " requested does not exist here");
    }
    
    /**
     * Creates a new shipment out of thin air in the warehouse. For use when setting up sim, NOT for transferring shipments
     * 
     * @param size size of the shipment to create
     * 
     * @returns the shipment created
     */
    public Shipment create_shipment(int size)
    {
        Shipment new_item = new Shipment(size);
        storage.add(new_item);
        return new_item;
    }
    
    /**
     * Send info to log file
     */
    public int log_status()
    {
        return 0;
    }
    
    /**
     * Decide what to do with the hour
     */
    public void action()
    {
        current_empty = bays; // at the end of each hour, all trucks leave warehouse so new ones can arrive
    }
    
    /**
     * Returns position in a 1x2 array
     */
    public int[] pos()
    {
        return new int[]{xpos, ypos};
    }
    
    /**
     * Returns number of loading bays in this warehouse
     */
    public int size() {
        return bays;
    }
    
    /**
     * Returns the size difference between this and another warehouse
     */
    public int compareTo(Warehouse w)
    {
        return bays - w.size();
    }
    
    /**
     * Draw to the renderer
     * 
     * @param graphics object to draw to
     */
    public void draw(Graphics g) {
        Graphics2D graphicsObj = (Graphics2D) g;
        Rectangle rect = new Rectangle(xpos, ypos, 10, 10);
        Color binColor1 = new Color(128, 20, 0);
        graphicsObj.setColor(binColor1);
        graphicsObj.fill(rect);
    }
}
