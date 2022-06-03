
/**
 * Runs one single instance of the sim
 *
 * @author Peter Fabens
 * @version 4/16/2022
 */
public class ModelRunner
{
    private static int FLEET_SIZE = 1;
    private static int NTWRK_SIZE = 2;
    
    public static void sim(int c_num) {
        LinkedList<Truck> fleet;
        LinkedList<Warehouse> network;
        DynamicMapCntrl dmc = new DynamicMapCntrl("Dynamic Map");
        
        Configurator.config("config" + c_num + ".txt");
        
        network = gen_ntwrk(NTWRK_SIZE);
        fleet = gen_fleet(FLEET_SIZE, network);
        
        dmc.add_fl(fleet);
        dmc.add_nw(network);
        
        boolean work_to_do = true;
        while(work_to_do) {
            for (int truck = 0; truck < fleet.size(); truck++) // all trucks do their thing at the top of the hour
            {
                fleet.get(truck).action();
            }
            work_to_do = fleet_check(fleet);
            if (!work_to_do)
                for (int truck = 0; truck < fleet.size(); truck++) // all trucks do their thing at the top of the hour
                {
                    Logger.log(fleet.get(truck).report());
                }
            dmc.refresh();
            for (int ware = 0; ware < network.size(); ware++) // just makes all trucks leave at the end of each hour
            {
                network.get(ware).action();
            }
        }
    }
    
    /**
     * Set config vars
     */
    public static void config(int fl, int nw)
    {
        FLEET_SIZE = fl;
        NTWRK_SIZE = nw;
    }
    
    /**
     * Generates a random list of trucks
     */
    public static LinkedList<Truck> gen_fleet (int length, LinkedList<Warehouse> ntwrk)
    {
        LinkedList<Truck> list = new LinkedList<Truck>();
        for (int cnt = 0; cnt < length; cnt++)
        {
            list.add(new Truck(ntwrk));
        }
        return list;
    }
    
    /**
     * Generates a random list of warehouses
     */
    public static LinkedList<Warehouse> gen_ntwrk (int length)
    {
        LinkedList<Warehouse> list = new LinkedList<Warehouse>();
        for (int cnt = 0; cnt < length; cnt++)
        {
            list.add(new Warehouse());
        }
        return list;
    }
    
    /**
     * Checks each truck in the fleet for whether they still have unfulfilled deliveries
     */
    public static boolean fleet_check(LinkedList<Truck> fleet)
    {
        boolean deliveries = false;
        for (int cnt = 0; cnt < fleet.size(); cnt++)
        {
            deliveries = !fleet.get(cnt).done() || deliveries;
        }
        return deliveries;
    }
}
