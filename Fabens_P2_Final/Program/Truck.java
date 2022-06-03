import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.util.Random;

/**
 * Transport truck class.
 *
 * @author Peter Fabens
 * @version 4/5/2022
 */

public class Truck implements Render, Schedule, Comparable<Truck> {
    private static int MNFST_SIZE = 3;
    
    private int xpos, ypos, size, speed;
    private int cargo_weight;
    
    private Heap<Delivery> manifest;
    private Stack<Delivery> cargo;
    private LinkedList<Warehouse> map;
    
    // Data tracking 
    private int waits; // hours made to wait by warehouse
    private int travels; // hours spent traveling
    private int dpicks; // double-pickups  (times when a pickup was followed immediately by another pickup
    private int ddrops; // double-dropoffs 
    private char last_depot; // last action performed at a warehouse, whether pickup or dropoff
    
    private static Random truck_rand = new Random(8675309); // for generating repeatable but pseudorandom results
    
    /**
     * Default Constructor, picks a random speed
     */
    public Truck(LinkedList<Warehouse> l) {
        size = truck_rand.nextInt(5) + 1; // generates a random num between 1-5
        speed = 6 - size;
        xpos = truck_rand.nextInt(400) + 50;
        ypos = truck_rand.nextInt(400) + 50;
        map = l;
        cargo = new Stack<Delivery>();
        cargo_weight = 0;
        manifest = create_manifest(l);
        waits = 0;
        travels = 0;
        dpicks = 0;
        ddrops = 0;
    }
    
    /**
     * Set config vars
     */
    public static void config(int size) {MNFST_SIZE = size;}
    
    /**
     * Randomly generates a trip manifest based on a given list of possible destinations
     * 
     * @param locations possible destinations for the truck to pick from
     */
    public Heap<Delivery> create_manifest(LinkedList<Warehouse> locations)
    {
        Heap<Delivery> out = new Heap<Delivery>();
        for (int cnt = 0; cnt < MNFST_SIZE; cnt++)
        {
            out.add(new Delivery(this, map));
        }
        return out;
    }
    
    /**
     * Returns position in a 1x2 array
     */
    public int[] pos()
    {
        return new int[]{xpos, ypos};
    }
    
    /**
     * Move coordinate-wise
     * 
     * @param x pixels to move in x dir
     * @param y pixels to move in y dir
     */
    public void move(int x, int y) {
        xpos += x;
        ypos += y;
    }
    
    /**
     * Move destination-wise. Moves towards specified warehouse location
     * 
     * @param destination warehouse to shoot for
     */
    public void move(Warehouse destination)
    {
        int[] dest = destination.pos();
        int distance = wh_dist(destination);
        if(wh_dist(destination) >= 10)
            move((dest[0] - xpos) * speed * 2 / distance, (dest[1] - ypos) * speed * 2 / distance);
        else
            move((dest[0] - xpos), (dest[1] - ypos));
    }
    
    public int wh_dist(Warehouse w)
    {
        int[] dest = w.pos();
        return (int)Math.hypot(dest[0] - xpos, dest[1] - ypos);
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
        if(manifest.empty() && cargo.size() == 0) return;
        else if (cargo.size() > 0 && wh_dist(cargo.peek().dropoff()) <= 10) // arrived at drop with pkg
        {
            if(cargo.peek().dropoff().park()) // ping warehouse for cargo bay availability
            {
                cargo_weight -= cargo.peek().item().size();
                cargo.peek().dropoff().unload(cargo.pull().item()); // send item to depot
                if (last_depot == 'd') ddrops++;
                last_depot = 'd';
            } else
                waits++;
        }else if (!manifest.empty() && wh_dist(manifest.peek().pickup()) <= 10 && cargo_weight + manifest.peek().item().size() <= size) // arrived at pickup
        {
            if(manifest.peek().pickup().park()) // ping warehouse for cargo bay availability
            {
                manifest.peek().pickup().load(manifest.peek().item()); // take pkg from depot
                cargo.push(manifest.pull()); // load pkg to cargo bay
                manifest.rebuild();
                cargo_weight += cargo.peek().item().size();
                if (last_depot == 'p') ddrops++;
                last_depot = 'p';
            } else
                waits++;
        }
        else // traveling
        {
            travels++;
            if(!manifest.empty() && cargo_weight + manifest.peek().item().size() <= size)   // if no cargo or haven't gotten pkg
                move(manifest.peek().pickup());                 // go to pickup
            else                                                // otherwise 
                move(cargo.peek().dropoff());                   // go to dropoff
        }
    }
    
    /**
     * Draw to the renderer
     * 
     * @param graphics object to draw to
     */
    public void draw(Graphics g) {
        Graphics2D graphicsObj = (Graphics2D) g;
        Rectangle rect = new Rectangle(xpos, ypos, 10, 10);
        Color binColor1 = new Color(20, 128, 0);
        graphicsObj.setColor(binColor1);
        graphicsObj.fill(rect);
    }
    
    /**
     * Returns whether this truck has items left in its manifest.
     */
    public boolean done()
    {
        return manifest.empty() && cargo.size() == 0;
    }
    
    /**
     * Returns a report on all the data this truck collected over the course of the work day.
     */
    public String report()
    {
        return " , " + size + ", " + travels + ", " + waits + ", " + ddrops + ", " + dpicks;
    }
    
    /**
     * Returns size of this truck
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the size difference between this and another truck
     */
    public int compareTo(Truck t)
    {
        return size - t.size();
    }
}
