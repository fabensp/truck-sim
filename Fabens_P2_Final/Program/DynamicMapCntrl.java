import javax.swing.JFrame;
public class DynamicMapCntrl {
    JFrame appFrame;
    DynamicMap map;
    public DynamicMapCntrl() {
        System.out.println("base constructor");
    }
    public void refresh() {
        map.repaint();
    }
    
    /**
     * Add a single item to the map
     */
    public void add(Render item)
    {
        map.add(item);
    }
    
    /**
     * Add a fleet of trucks to the map
     */
    public void add_fl(LinkedList<Truck> list) {
        for (int cnt = 0; cnt < list.size(); cnt++) 
        {
            map.add(list.get(cnt));
        }
    }
    
    /**
     * Add a network of warehouses to the map
     */
    public void add_nw(LinkedList<Warehouse> list) {
        for (int cnt = 0; cnt < list.size(); cnt++) 
        {
            map.add(list.get(cnt));
        }
    }
    
    public DynamicMapCntrl(String str)
    {
        appFrame = new JFrame();
        map = new DynamicMap();
        appFrame.setSize(500, 500);
        appFrame.setTitle("Sayonara™ Package Fulfillment Solutions, Inc.");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the HistogramComponent object to the frame
        appFrame.add(map);
        // Set the frame and its contents visible
        appFrame.setVisible(true);
    }
}